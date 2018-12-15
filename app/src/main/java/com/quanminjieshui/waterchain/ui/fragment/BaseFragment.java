package com.quanminjieshui.waterchain.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.event.NetworkStateEvent;
import com.quanminjieshui.waterchain.utils.NetworkUtils;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public abstract class BaseFragment extends BasePermissionFragment {
    private BaseActivity baseActivity;
    private boolean haveShowNetView = false;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) {
            baseActivity = (BaseActivity) activity;
        }
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("");
        return textView;
    }

    /**
     * 手动点击切换fragment时 加载无网UI
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            //初始化无网络ui 并且传入需要替换的view id
            // TODO: 2018/12/3 添加需要替换的无网络时对应的ui布局id
//            initNoNetView();
        }
        super.onHiddenChanged(hidden);
    }

    /**
     * 网络状态改变时即调用此接口--初始化替换无网UI
     * @param event
     */
    public void onEventMainThread(NetworkStateEvent event){
        //初始化无网络ui 并且传入需要替换的view id
        // TODO: 2018/12/3 添加需要替换的无网络时对应的ui布局id
//        initNoNetView();
    }

    public void showToast(String tips) {
        baseActivity.showToast(tips);
    }


    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        baseActivity.hideShowKeyboard();
    }

    private void initNoNetView(int ...ids) {
        if (!NetworkUtils.isConnected() && !haveShowNetView) {
            final List<View> viewList = new ArrayList<>();
            //通过id获取view
            for (int id : ids) {
                viewList.add(getBaseActivity().findViewById(id));
            }
            //实现自己的无网络页面
            final View noNetView = View.inflate(getBaseActivity(), R.layout.layout_no_net, null);
            noNetView.findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetworkUtils.isConnected()) {
                        ToastUtils.showCustomToast("没有获取到网络，请重试...");
                        return;
                    }
                    //切换到有网络页面
                    showHaveNetView(viewList,noNetView);
                }
            });
            //切换到无网络页面
            showNoNetView(viewList,noNetView);
        }
    }

    private void showHaveNetView(List<View> viewList, View noNetView) {
        for (View view : viewList) {
            if (view==null){
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
            if (view==null){
                continue;
            }
            haveShowNetView = true;
            transView(view, noNetView);
            break;
        }
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
