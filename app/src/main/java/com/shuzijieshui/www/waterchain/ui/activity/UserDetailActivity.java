package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.UserDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.UserDetailModel;
import com.shuzijieshui.www.waterchain.contract.presenter.UserDetailPresenter;
import com.shuzijieshui.www.waterchain.contract.view.UserDetailViewImpl;
import com.shuzijieshui.www.waterchain.event.SelectFragmentEvent;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.SPUtil;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class UserDetailActivity extends BaseActivity implements UserDetailViewImpl {

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
    private AlertChainDialog dialog;

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
                finish();
                break;

            case R.id.tv_auth_status:
                jump(AuthActivity.class);
                finish();
                break;
            case R.id.btn_logout:

                if (dialog == null) {
                    dialog = new AlertChainDialog(this);
                }
                dialog.builder().setCancelable(true);
                dialog.setTitle("提示消息")
                        .setMsg("确认退出当前账号")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //一起操作
                                //            SPUtil.delete(this,SPUtil.IS_LOGIN);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.TOKEN);
                                SPUtil.insert(UserDetailActivity.this, SPUtil.IS_LOGIN, false);//更改用户登录状态为未登录

                                SPUtil.delete(UserDetailActivity.this, SPUtil.USER_LOGIN);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.UID);

                                SPUtil.delete(UserDetailActivity.this, SPUtil.ID);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.IS_BLOCKED);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.USER_LOGIN);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.USER_NICKNAME);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.TOKEN);
                                SPUtil.delete(UserDetailActivity.this, SPUtil.AVATAR);


                                jump(MainActivity.class);
                                EventBus.getDefault().post(new SelectFragmentEvent("我的"));//显示personalFragment
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
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
    protected void onResume() {
        super.onResume();
        userDetailPresenter.getUserDetail(this);
        showLoadingDialog();
        String avatarUrl = (String) SPUtil.get(this, SPUtil.AVATAR, "");
        GlidImageManager.getInstance().loadCircleImg(this, avatarUrl, imgAvatar, R.mipmap.user_head, R.mipmap.user_head);
    }

    @Override
    public void onUserDetailSuccess(UserDetailResponseBean userDetailResponseBean) {
        if (userDetailResponseBean != null) {
            String user_login = (String) SPUtil.get(this, SPUtil.USER_LOGIN, "********");
            if (TextUtils.isEmpty(user_login)) {
                user_login = "********";
            }
            tvUserLogin.setText(Util.hide4Phone(user_login));
            tvCreateTime.setText(userDetailResponseBean.getCreate_time());
            tvUserLoginTel.setText(Util.hide4Phone(userDetailResponseBean.getUser_login()));
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
        dismissLoadingDialog();
    }

    @Override
    public void onUserDetailFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDetailPresenter.detachView();
    }
}
