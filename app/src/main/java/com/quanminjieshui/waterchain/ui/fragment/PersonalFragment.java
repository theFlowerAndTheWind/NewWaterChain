package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.ui.activity.AuthActivity;
import com.quanminjieshui.waterchain.ui.activity.ChangePassActivity;
import com.quanminjieshui.waterchain.ui.activity.OrderListsActivity;
import com.quanminjieshui.waterchain.ui.activity.UserDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:我的个人中心
 */

public class PersonalFragment extends BaseFragment {
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_user_login)
    TextView tvUserLogin;
    @BindView(R.id.img_go_user_detail)
    ImageView imgGoUserDetail;
    @BindView(R.id.relative_user_detail)
    RelativeLayout relativeUserDetail;//
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.tv_account_detail)
    TextView tvAccountDetail;
    @BindView(R.id.img_go_account_detail)
    ImageView imgGoAccountDetail;
    @BindView(R.id.relative_account_detail)
    RelativeLayout relativeAccountDetail;//
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.tv_auth_detail)
    TextView tvAuthDetail;
    @BindView(R.id.img_go_auth_detail)
    ImageView imgGoAuthDetail;
    @BindView(R.id.relative_auth_detail)
    RelativeLayout relativeAuthDetail;//
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.tv_trade_lists)
    TextView tvTradeLists;
    @BindView(R.id.img_go_trade_lists)
    ImageView imgGoTradeLists;
    @BindView(R.id.relative_trade_lists)
    RelativeLayout relativeTradeLists;//
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.tv_order_lists)
    TextView tvOrderLists;
    @BindView(R.id.img_go_order_lists)
    ImageView imgGoOrderLists;
    @BindView(R.id.relative_order_lists)
    RelativeLayout relativeOrderLists;//
    @BindView(R.id.img6)
    ImageView img6;
    @BindView(R.id.tv_sys_msg)
    TextView tvSysMsg;
    @BindView(R.id.img_go_sys_msg)
    ImageView imgGoSysMsg;
    @BindView(R.id.relative_sys_msg)
    RelativeLayout relativeSysMsg;//
    @BindView(R.id.img7)
    ImageView img7;
    @BindView(R.id.tv_change_pass)
    TextView tvChangPass;
    @BindView(R.id.img_go_change_pass)
    ImageView imgGoChanggePass;
    @BindView(R.id.relative_change_pass)
    RelativeLayout relativeChangePass;//
    @BindView(R.id.img8)
    ImageView img8;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.img_go_about_us)
    ImageView imgGoAboutUs;
    @BindView(R.id.relative_about_us)
    RelativeLayout relativeAboutUs;//
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private Unbinder unbinder;

    @OnClick({R.id.relative_user_detail, R.id.relative_account_detail, R.id.relative_auth_detail,
            R.id.relative_trade_lists, R.id.relative_order_lists, R.id.relative_sys_msg, R.id.relative_change_pass, R.id.relative_about_us})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative_user_detail:
                jump(UserDetailActivity.class);
                break;
            case R.id.relative_account_detail:
showToast("nothing!");
                break;
            case R.id.relative_auth_detail:
                jump(AuthActivity.class);
                break;
            case R.id.relative_trade_lists:
                showToast("nothing!");
                break;
            case R.id.relative_order_lists:
                jump(OrderListsActivity.class);
                break;
            case R.id.relative_sys_msg:
                showToast("nothing!");
                break;
            case R.id.relative_change_pass:
                jump(ChangePassActivity.class);
                break;
            case R.id.relative_about_us:
                showToast("nothing!");
                break;
            default:
                break;
        }

    }

    private void jump(Class<?> cls){
        startActivity(new Intent(getBaseActivity(),cls));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_personal,container,false);
        unbinder=ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){

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
}
