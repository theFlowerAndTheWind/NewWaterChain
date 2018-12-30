package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.event.PersonalSelectedEvent;
import com.quanminjieshui.waterchain.ui.activity.AboutListActivity;
import com.quanminjieshui.waterchain.ui.activity.ChangePassActivity;
import com.quanminjieshui.waterchain.ui.activity.GoodsActivity;
import com.quanminjieshui.waterchain.ui.activity.LoginActivity;
import com.quanminjieshui.waterchain.ui.activity.OrderListsActivity;
import com.quanminjieshui.waterchain.ui.activity.UserAssetActivity;
import com.quanminjieshui.waterchain.ui.activity.UserConfirmActivity;
import com.quanminjieshui.waterchain.ui.activity.UserDetailActivity;
import com.quanminjieshui.waterchain.ui.activity.UserMessageActivity;
import com.quanminjieshui.waterchain.ui.widget.WarningFragment;
import com.quanminjieshui.waterchain.utils.SPUtil;

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
    @BindView(R.id.tv_user_login)
    TextView tvUserLogin;
    //    @BindView(R.id.img_go_user_detail)
//    ImageView imgGoUserDetail;
//    @BindView(R.id.relative_user_detail)
//    RelativeLayout relativeUserDetail;//
//    @BindView(R.id.img1)
//    ImageView img1;
//    @BindView(R.id.tv_account_detail)
//    TextView tvAccountDetail;
//    @BindView(R.id.img_go_account_detail)
//    ImageView imgGoAccountDetail;
//    @BindView(R.id.relative_account_detail)
//    RelativeLayout relativeAccountDetail;//
//    @BindView(R.id.img2)
//    ImageView img2;
//    @BindView(R.id.tv_auth_detail)
//    TextView tvAuthDetail;
//    @BindView(R.id.img_go_auth_detail)
//    ImageView imgGoAuthDetail;
//    @BindView(R.id.relative_auth_detail)
//    RelativeLayout relativeAuthDetail;//
//    @BindView(R.id.img3)
//    ImageView img3;
//    @BindView(R.id.tv_trade_lists)
//    TextView tvTradeLists;
//    @BindView(R.id.img_go_trade_lists)
//    ImageView imgGoTradeLists;
//    @BindView(R.id.relative_trade_lists)
//    RelativeLayout relativeTradeLists;//
//    @BindView(R.id.img4)
//    ImageView img4;
//    @BindView(R.id.tv_order_lists)
//    TextView tvOrderLists;
//    @BindView(R.id.img_go_order_lists)
//    ImageView imgGoOrderLists;
//    @BindView(R.id.relative_order_lists)
//    RelativeLayout relativeOrderLists;//
//    @BindView(R.id.relative_goods)
//    RelativeLayout relative_goods;
//    @BindView(R.id.img6)
//    ImageView img6;
//    @BindView(R.id.tv_sys_msg)
//    TextView tvSysMsg;
//    @BindView(R.id.img_go_sys_msg)
//    ImageView imgGoSysMsg;
//    @BindView(R.id.relative_sys_msg)
//    RelativeLayout relativeSysMsg;//
//    @BindView(R.id.img7)
//    ImageView img7;
//    @BindView(R.id.tv_change_pass)
//    TextView tvChangPass;
//    @BindView(R.id.img_go_change_pass)
//    ImageView imgGoChanggePass;
//    @BindView(R.id.relative_change_pass)
//    RelativeLayout relativeChangePass;//
//    @BindView(R.id.img8)
//    ImageView img8;
//    @BindView(R.id.tv_about_us)
//    TextView tvAboutUs;
//    @BindView(R.id.img_go_about_us)
//    ImageView imgGoAboutUs;
//    @BindView(R.id.relative_about_us)
//    RelativeLayout relativeAboutUs;//
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private Unbinder unbinder;

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
                    showToast("nothing!");
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
                if (checkLoginStatus())
                    jump(AboutListActivity.class);
                break;
            default:
                break;
        }

    }

    private boolean checkLoginStatus() {
        boolean isLogin = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
        if (!isLogin) {
            WarningFragment fragment = new WarningFragment("提示消息", "您当前未登录，请登录", "登录", "取消", "checkLoginStatus", this);
            fragment.show(getActivity().getSupportFragmentManager(), "warning_fragment");
        }
        return isLogin;
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(getBaseActivity(), cls));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initView();
        return rootView;
    }

    private void initView() {
        tvUserLogin.setText(SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "*** **** ****") + "");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPositiveClicked(String tag) {
        if (tag.equals("checkLoginStatus")) {
            jump(LoginActivity.class);
        }
    }

    @Override
    public void onNegativeClicked(String tag) {

    }

    public void onEventMainThread(PersonalSelectedEvent event) {
        if (event != null && event.getUser_login().equals("user_login")) {
                tvUserLogin.setText(SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "*** **** ****") + "");
        }
    }
}
