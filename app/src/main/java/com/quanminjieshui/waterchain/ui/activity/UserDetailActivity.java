package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UserDetailResponseBean;
import com.quanminjieshui.waterchain.contract.model.UserDetailModel;
import com.quanminjieshui.waterchain.contract.presenter.UserDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.UserDetailViewImpl;
import com.quanminjieshui.waterchain.event.PersonalSelectedEvent;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.ui.widget.WarningFragment;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class UserDetailActivity extends BaseActivity implements UserDetailViewImpl, WarningFragment.OnWarningDialogClickedListener {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_user_login)
    TextView tvUserLogin;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.relative_user_detail)
    RelativeLayout relativeUserDetail;
    @BindView(R.id.tv_user_login_tel)
    TextView tvUserLoginTel;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    @BindView(R.id.tv_change_pass)
    TextView tvUserPass;
    @BindView(R.id.tv_auth_status)
    TextView tvAuthStatus;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    private UserDetailPresenter userDetailPresenter;

    @OnClick({R.id.left_ll, R.id.tv_change_pass, R.id.tv_auth_status, R.id.btn_logout})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.tv_change_pass:
                jump(ChangePassActivity.class);
                break;

            case R.id.tv_auth_status:
                jump(AuthActivity.class);
                break;
            case R.id.btn_logout:
                WarningFragment fragment = new WarningFragment("提示消息", "确认退出当前账号", "确定", "取消", "logout", this);
                fragment.show(getSupportFragmentManager(), "warning_fragment");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();

        userDetailPresenter = new UserDetailPresenter(new UserDetailModel());
        userDetailPresenter.attachView(this);
        userDetailPresenter.getUserDetail(this);
    }

    private void initView() {
        tvTitleCenter.setText("账户信息");
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(UserDetailActivity.this, cls));
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }


    @Override
    public void onPositiveClicked(String tag) {
        if (tag.equals("logout")) {
            SPUtil.insert(UserDetailActivity.this, SPUtil.IS_LOGIN, false);//更改用户登录状态为未登录
            SPUtil.delete(this, SPUtil.USER_LOGIN);
            SPUtil.delete(this, SPUtil.UID);
            SPUtil.delete(this, SPUtil.TOKEN);

            SPUtil.delete(this, SPUtil.ID);
            SPUtil.delete(this, SPUtil.IS_BLOCKED);
            SPUtil.delete(this, SPUtil.USER_LOGIN);
            SPUtil.delete(this, SPUtil.USER_NICKNAME);
            SPUtil.delete(this, SPUtil.TOKEN);
//            SPUtil.delete(this,SPUtil.IS_LOGIN);

            jump(MainActivity.class);
            EventBus.getDefault().post(new SelectFragmentEvent("我的"));//显示personalFragment
            EventBus.getDefault().post(new PersonalSelectedEvent("user_login"));//头像右侧用户名（手机号）显示
            finish();
        }
    }

    @Override
    public void onNegativeClicked(String tag) {

    }

    @Override
    public void onUserDetailSuccess(UserDetailResponseBean userDetailResponseBean) {
        if (userDetailResponseBean != null) {
            tvUserLogin.setText(userDetailResponseBean.getUser_login());
            tvCreateTime.setText(userDetailResponseBean.getCreate_time());
            tvUserLoginTel.setText(userDetailResponseBean.getUser_login());
            tvUserType.setText(userDetailResponseBean.getUser_type());
            int user_status = userDetailResponseBean.getUser_status();
            if (user_status == 0) {
                tvAuthStatus.setText("去认证");
                tvAuthStatus.setTextColor(getResources().getColor(R.color.primary_blue));
                tvAuthStatus.setEnabled(true);
            } else if (user_status == 1) {
                tvAuthStatus.setText("已认证");
                tvAuthStatus.setTextColor(getResources().getColor(R.color.text_black));
                tvAuthStatus.setEnabled(false);
            }
        }
    }

    @Override
    public void onUserDetailFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDetailPresenter.detachView();
    }
}
