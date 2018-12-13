package com.quanminjieshui.waterchain.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.utils.NetworkUtils;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by WanghongHe on 2018/12/3 12:32.
 */

public abstract class BaseActivity  extends BasePermissionActivity implements LifecycleProvider<ActivityEvent> {

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    private boolean haveShowNetView = false;

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    public Context mContext;


    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        ActivityManager.addActivity(this);

        initContentView();
        ButterKnife.bind(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //初始化无网络ui 并且传入需要替换的view id
        initNoNetView(R.layout.activity_main);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }

    private Toast mToast;
    private Handler mHandler = new Handler();

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };


    public void showToast(final String tips) {
        if (Looper.getMainLooper() == Looper.myLooper()) {//当前线程就是主线程
            setShow(tips);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setShow(tips);
                }
            });
        }
    }

    private void setShow(String tips) {
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(tips);
        } else {
            mToast = Toast.makeText(getApplicationContext(), tips, Toast.LENGTH_LONG);
        }
        mHandler.postDelayed(r, 1500);
        mToast.show();
    }

    /**
     * 软键盘显示/隐藏
     */
    public void hideShowKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //得到InputMethodManager的实例
        if (imm.isActive()) {//如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void goBack(View view) {
        finish();
        hideSoftKeyboard(this);
    }

    public void setWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        //隐藏加减号按钮
        webSettings.setDisplayZoomControls(false);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webView.setInitialScale(100);
        webSettings.setAppCacheEnabled(false);
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {
        });
    }

    /**
     * 建议在Activity-onDestroy中的super.OnDestroy前调用
     * 无网情况下 webview本身未被实例化 销毁前需判断其父类布局是否已实例化
     *
     * @param mWebView
     */
    public void cancelWebView(WebView mWebView) {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            if (mWebView.getParent() != null) {
                ((ViewGroup) mWebView.getParent()).removeView(mWebView);
                mWebView.destroy();
            }

        }
    }

    private void initNoNetView(int... ids) {
        if (!NetworkUtils.isConnected() && !haveShowNetView) {
            final List<View> viewList = new ArrayList<>();
            //通过id获取view
            for (int id : ids) {
                viewList.add(findViewById(id));
            }
            //实现自己的无网络页面
            final View noNetView = View.inflate(BaseActivity.this, R.layout.layout_no_net, null);
            noNetView.findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetworkUtils.isConnected()) {
                        ToastUtils.showCustomToast("没有获取到网络，请重试...");
                        return;
                    }
                    //切换到有网络页面
                    showHaveNetView(viewList, noNetView);
                }
            });
            //切换到无网络页面
            showNoNetView(viewList, noNetView);
        }
    }

    private void showHaveNetView(List<View> viewList, View noNetView) {
        for (View view : viewList) {
            if (view == null) {
                continue;
            }
            haveShowNetView = false;
            transView(noNetView, view);
            onReNetRefreshData(view.getId());
            break;
        }
    }

    private void showNoNetView(List<View> viewList, View noNetView) {
        for (View view : viewList) {
            if (view == null) {
                continue;
            }
            haveShowNetView = true;
            transView(view, noNetView);
            break;
        }
    }

    /**
     * 初始化页面Ui
     */
    public abstract void initContentView();


    /**
     * 网络状况改变情况下 重试刷新数据
     */
    public abstract void onReNetRefreshData(int viewId);

    protected void transView(final View defaultView, View replaceView) {
        final int index = ((ViewGroup) defaultView.getParent()).indexOfChild(defaultView);
        ViewGroup.LayoutParams params = defaultView.getLayoutParams();
        ViewGroup parent = (ViewGroup) defaultView.getParent();
        parent.removeView(defaultView);
        parent.addView(replaceView, index, params);
    }
}