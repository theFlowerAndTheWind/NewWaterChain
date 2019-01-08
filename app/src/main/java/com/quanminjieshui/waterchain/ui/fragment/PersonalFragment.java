package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterchain.ui.activity.AboutListActivity;
import com.quanminjieshui.waterchain.ui.activity.ChangePassActivity;
import com.quanminjieshui.waterchain.ui.activity.GoodsActivity;
import com.quanminjieshui.waterchain.ui.activity.LoginActivity;
import com.quanminjieshui.waterchain.ui.activity.OrderListsActivity;
import com.quanminjieshui.waterchain.ui.activity.TradeListsActivity;
import com.quanminjieshui.waterchain.ui.activity.UserAssetActivity;
import com.quanminjieshui.waterchain.ui.activity.UserConfirmActivity;
import com.quanminjieshui.waterchain.ui.activity.UserDetailActivity;
import com.quanminjieshui.waterchain.ui.activity.UserMessageActivity;
import com.quanminjieshui.waterchain.ui.widget.WarningFragment;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:我的个人中心
 */

public class PersonalFragment extends BaseFragment implements WarningFragment.OnWarningDialogClickedListener {
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private Unbinder unbinder;
    /**
     * 是否登录
     * 记录当前是否登录，fargment切换后有登录、退出登录操作后，下次再显示时用该变量与本地SP所存结果比对，不一致时做刷新操作
     */
    private boolean isLogin=false;
    private String user_login;//用户登录手机号，作用同isLogin

    @OnClick({R.id.relative_user_detail, R.id.relative_account_detail, R.id.relative_auth_detail,
            R.id.relative_trade_lists, R.id.relative_order_lists, R.id.relative_goods,
            R.id.relative_sys_msg, R.id.relative_change_pass, R.id.relative_about_us})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.relative_user_detail:
                if (checkLoginStatus())
                    jump(UserDetailActivity.class);
                break;
            case R.id.relative_account_detail:
                if (checkLoginStatus())
                    jump(UserAssetActivity.class);
                break;
            case R.id.relative_auth_detail:
                if (checkLoginStatus())
                    jump(UserConfirmActivity.class);
                break;
            case R.id.relative_trade_lists:
                if (checkLoginStatus())
                    jump(TradeListsActivity.class);
                break;
            case R.id.relative_order_lists:
                if (checkLoginStatus())
                    jump(OrderListsActivity.class);
                break;
            case R.id.relative_goods:
                if (checkLoginStatus())
                    jump(GoodsActivity.class);
                break;
            case R.id.relative_sys_msg:
                if (checkLoginStatus())
                    jump(UserMessageActivity.class);
                break;
            case R.id.relative_change_pass:
                if (checkLoginStatus())
                    jump(ChangePassActivity.class);
                break;
            case R.id.relative_about_us:
                    jump(AboutListActivity.class);
                break;
            default:
                break;
        }

    }

    private boolean checkLoginStatus() {
       boolean isLogin_ = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
        if (!isLogin_) {
            WarningFragment fragment = new WarningFragment("提示消息", "您当前未登录，请登录", "登录", "取消", "checkLoginStatus", this);
            fragment.show(getActivity().getSupportFragmentManager(), "warning_fragment");
        }
        return isLogin_;
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(getBaseActivity(), cls));
    }

    private void jump(Class<?>cls,Intent intent){
        intent.setClass(getActivity(),cls);
        startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initView();
        isLogin= (boolean) SPUtil.get(getActivity(),SPUtil.IS_LOGIN,false);
        user_login=(String)SPUtil.get(getActivity(),SPUtil.USER_LOGIN,"user_login");
        return rootView;
    }

    private void initView() {
        String avatarUrl= (String) SPUtil.get(getActivity(),SPUtil.AVATAR,"");
        GlidImageManager.getInstance().loadCircleImg(getActivity(),avatarUrl,imgAvatar,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round);
        String user_nickname=(String)SPUtil.get(getActivity(), SPUtil.USER_NICKNAME, "********");
        if(TextUtils.isEmpty(user_nickname)){
            user_nickname="********";
        }
        tvNickname.setText(user_nickname);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
    }



    @Override
    public void onPositiveClicked(String tag) {
        if (tag.equals("checkLoginStatus")) {
            Intent intent=new Intent();
            intent.putExtra("target","main_personal");
            jump(LoginActivity.class,intent);
        }else if(tag.equals("sth.")){

        }
    }

    @Override
    public void onNegativeClicked(String tag) {

    }
//
//    public void onEventMainThread(LogoutEvent event){
//        if (event != null && event.getMsg().equals("logout_main_personal_refresh_nickname")) {
//            tvUserLogin.setText(SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "*** **** ****") + "");
//        }
//    }
//
//    public void onEventMainThread(LoginEvent event) {
//        if (event != null && event.getMsg().equals("login_success_main_personal_refresh_nickname")) {
//                tvUserLogin.setText(SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "*** **** ****") + "");
//
//        }
//    }

    public void onEventMainThread(LoginStatusChangedEvent event){
        if(event!=null&&event.getMsg().equals("login_status_changed_main_personal_refresh_nickname")){
            isLogin= (boolean) SPUtil.get(getActivity(),SPUtil.IS_LOGIN,false);
            user_login=(String)SPUtil.get(getActivity(),SPUtil.USER_LOGIN,"user_login");
            String user_nickname=(String)SPUtil.get(getActivity(), SPUtil.USER_NICKNAME, "********");
            if(TextUtils.isEmpty(user_nickname)){
                user_nickname="********";
            }
            tvNickname.setText(user_nickname);
        }
    }


    public String getIsLogin(){
        return new StringBuilder().append(isLogin).append(user_login).toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
