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
import com.quanminjieshui.waterchain.contract.view.ChangePassViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDetailActivity extends BaseActivity {

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
    @OnClick({R.id.img_title_left,R.id.tv_change_pass,R.id.tv_auth_status})
    public void onClick(View v){
        int id=v.getId();
        switch (id){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
            case R.id.tv_change_pass:
                jump(ChangePassActivity.class);
                break;

            case R.id.tv_auth_status:

                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar );
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("账户信息");
    }

    private void jump(Class<?>cls){
        startActivity(new Intent(UserDetailActivity.this,cls));
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }


}
