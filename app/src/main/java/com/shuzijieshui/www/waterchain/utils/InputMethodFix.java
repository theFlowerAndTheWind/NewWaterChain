package com.shuzijieshui.www.waterchain.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by WanghongHe on 2017/6/22.
 */

public class InputMethodFix {
    /**
     * Fix for https://code.google.com/p/android/issues/detail?id=171190 .
     * <p>
     * When a view that has focus gets detached, we wait for the main thread to be idle and then
     * check if the InputMethodManager is leaking a view. If yes, we tell it that the decor view got
     * focus, which is what happens if you btn_press_bg home and come back from recent apps. This replaces
     * the reference to the detached view with a reference to the decor view.
     * <p>
     * Should be called from {@link Activity#onCreate(Bundle)} )}.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void fixFocusedViewLeak(Application application) {

        // Don't know about other versions yet.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 || Build.VERSION.SDK_INT > 23) {
            return;
        }

        final InputMethodManager inputMethodManager =
                (InputMethodManager) application.getSystemService(Context.INPUT_METHOD_SERVICE);

        final Field mServedViewField;
        final Field mHField;
        final Method finishInputLockedMethod;
        final Method focusInMethod;
        try {
            mServedViewField = InputMethodManager.class.getDeclaredField("mServedView");
            mServedViewField.setAccessible(true);
            mHField = InputMethodManager.class.getDeclaredField("mServedView");
            mHField.setAccessible(true);
            finishInputLockedMethod = InputMethodManager.class.getDeclaredMethod("finishInputLocked");
            finishInputLockedMethod.setAccessible(true);
            focusInMethod = InputMethodManager.class.getDeclaredMethod("focusIn", View.class);
            focusInMethod.setAccessible(true);
        } catch (NoSuchMethodException | NoSuchFieldException unexpected) {
            LogUtils.e("IMMLeaks", "Unexpected reflection exception", unexpected);
            return;
        }

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityDestroyed(Activity activity) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ReferenceCleaner cleaner = new ReferenceCleaner(inputMethodManager, mHField, mServedViewField,
                        finishInputLockedMethod);
                View rootView = activity.getWindow().getDecorView().getRootView();
                ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
                viewTreeObserver.addOnGlobalFocusChangeListener(cleaner);
            }
        });
    }

    static class ReferenceCleaner
            implements MessageQueue.IdleHandler, View.OnAttachStateChangeListener,
            ViewTreeObserver.OnGlobalFocusChangeListener {

        private final InputMethodManager inputMethodManager;
        private final Field mHField;
        private final Field mServedViewField;
        private final Method finishInputLockedMethod;

        ReferenceCleaner(InputMethodManager inputMethodManager, Field mHField, Field mServedViewField,
                         Method finishInputLockedMethod) {
            this.inputMethodManager = inputMethodManager;
            this.mHField = mHField;
            this.mServedViewField = mServedViewField;
            this.finishInputLockedMethod = finishInputLockedMethod;
        }

        @Override
        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
            if (newFocus == null) {
                return;
            }
            if (oldFocus != null) {
                oldFocus.removeOnAttachStateChangeListener(this);
            }
            Looper.myQueue().removeIdleHandler(this);
            newFocus.addOnAttachStateChangeListener(this);
        }

        @Override
        public void onViewAttachedToWindow(View v) {
        }

        @Override
        public void onViewDetachedFromWindow(View v) {
            v.removeOnAttachStateChangeListener(this);
            Looper.myQueue().removeIdleHandler(this);
            Looper.myQueue().addIdleHandler(this);
        }

        @Override
        public boolean queueIdle() {
            clearInputMethodManagerLeak();
            return false;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        private void clearInputMethodManagerLeak() {
            try {
                Object lock = mHField.get(inputMethodManager);
                // This is highly dependent on the InputMethodManager implementation.
                synchronized (lock) {
                    View servedView = (View) mServedViewField.get(inputMethodManager);
                    if (servedView != null) {

                        boolean servedViewAttached = servedView.getWindowVisibility() != View.GONE;

                        if (servedViewAttached) {
                            // The view held by the IMM was replaced without a global focus change. Let's make
                            // sure we get notified when that view detaches.

                            // Avoid double registration.
                            servedView.removeOnAttachStateChangeListener(this);
                            servedView.addOnAttachStateChangeListener(this);
                        } else {
                            // servedView is not attached. InputMethodManager is being stupid!
                            Activity activity = extractActivity(servedView.getContext());
                            if (activity == null || activity.getWindow() == null) {
                                // Unlikely case. Let's finish the input anyways.
                                finishInputLockedMethod.invoke(inputMethodManager);
                            } else {
                                View decorView = activity.getWindow().peekDecorView();
                                boolean windowAttached = decorView.getWindowVisibility() != View.GONE;
                                if (!windowAttached) {
                                    finishInputLockedMethod.invoke(inputMethodManager);
                                } else {
                                    decorView.requestFocusFromTouch();
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException unexpected) {
                LogUtils.e("IMMLeaks", "Unexpected reflection exception", unexpected);
            }
        }

        private Activity extractActivity(Context context) {
            while (true) {
                if (context instanceof Application) {
                    return null;
                } else if (context instanceof Activity) {
                    return (Activity) context;
                } else if (context instanceof ContextWrapper) {
                    Context baseContext = ((ContextWrapper) context).getBaseContext();
                    // Prevent Stack Overflow.
                    if (baseContext == context) {
                        return null;
                    }
                    context = baseContext;
                } else {
                    return null;
                }
            }
        }
    }

    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
//                        LogUtils.d( "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        LogUtils.d("不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了");

                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

