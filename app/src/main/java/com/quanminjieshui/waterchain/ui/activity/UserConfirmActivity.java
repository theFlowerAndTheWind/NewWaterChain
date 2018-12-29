package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.AuthDetailResponseBean;
import com.quanminjieshui.waterchain.contract.model.AuthDetailModel;
import com.quanminjieshui.waterchain.contract.presenter.AuthDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.AuthDetailViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class UserConfirmActivity extends BaseActivity implements AuthDetailViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;

    @BindView(R.id.ll_did_not)
    LinearLayout llDidNot;
    @BindView(R.id.ll_ing)
    LinearLayout llIng;
    @BindView(R.id.ll_done)
    LinearLayout llDone;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_id_no)
    TextView tvIdNo;

    AuthDetailPresenter authDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authDetailPresenter = new AuthDetailPresenter(new AuthDetailModel());
        authDetailPresenter.attachView(this);
        authDetailPresenter.getAuthDetail(this);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("身份认证");
        //默认显示未认证页面
        llDidNot.setVisibility(View.VISIBLE);
        llIng.setVisibility(View.GONE);
        llDone.setVisibility(View.GONE);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_confirm);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.tv_do_auth})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.img_title_left:
            case R.id.left_ll:
                goBack(view);
                finish();
                break;
            case R.id.tv_do_auth:
                startActivity(new Intent(UserConfirmActivity.this, AuthActivity.class));
                break;

            default:
                break;
        }

    }

    private void selectLayout(AuthDetailResponseBean authDetailResponseBean) {
        if (authDetailResponseBean != null) {
            String user_status = authDetailResponseBean.getUser_status().trim();
            if (user_status.equals("待认证") || user_status.equals("已驳回")) {
                llDidNot.setVisibility(View.VISIBLE);
                llIng.setVisibility(View.GONE);
                llDone.setVisibility(View.GONE);
            } else if (user_status.equals("待审核")) {
                llDidNot.setVisibility(View.GONE);
                llIng.setVisibility(View.VISIBLE);
                llDone.setVisibility(View.GONE);
            } else if (user_status.equals("已通过")) {
                llDidNot.setVisibility(View.GONE);
                llIng.setVisibility(View.GONE);
                llDone.setVisibility(View.VISIBLE);
                int user_type = authDetailResponseBean.getUser_type();
                if (user_type == 1) {//个人
                    tvUserName.setText(new StringBuilder().append("姓名：").append(authDetailResponseBean.getUser_name()).toString());
                    String idNo=authDetailResponseBean.getId_no();
                    String str1=idNo.substring(0,3);
                    String str2="*************";
                    String str3=idNo.substring(idNo.length()-2,idNo.length());
                    tvIdNo.setText(new StringBuilder().append("证件号码：").append(str1).append(str2).append(str3).toString());
                } else if (user_type == 2) { //企业
                    tvUserName.setText(new StringBuilder().append("营业执照登记名称：").append(authDetailResponseBean.getUser_name()).toString());
                    String idNo=authDetailResponseBean.getId_no();
                    String str1=idNo.substring(0,3);
                    String str2="**********";
                    String str3=idNo.substring(idNo.length()-2,idNo.length());
                    tvIdNo.setText(new StringBuilder().append("营业执照注册号").append(str1).append(str2).append(str3).toString());
                }
            }
        }
    }


    @Override
    public void authDetailSuccess(AuthDetailResponseBean authDetailResponseBean) {
        selectLayout(authDetailResponseBean);
    }

    @Override
    public void authDetailFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        authDetailPresenter.detachView();
    }
}
