package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.AccountDetailResponseBean;
import com.quanminjieshui.waterchain.contract.model.AccountDetailModel;
import com.quanminjieshui.waterchain.contract.presenter.AccountDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.AccountDetailViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class UserAssetActivity extends BaseActivity implements AccountDetailViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.tv_jsl)
    TextView tvJsl;
    @BindView(R.id.tv_jsl_freeze)
    TextView tvJslFreeze;
    @BindView(R.id.tv_jsl_lock_view)
    TextView tvJslLockView;
    @BindView(R.id.tv_jsl_gyj)
    TextView tvJslGyj;
    @BindView(R.id.tv_ds)
    TextView tvDs;
    @BindView(R.id.tv_ds_freeze)
    TextView tvDsFreeze;

    private AccountDetailPresenter accountDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountDetailPresenter = new AccountDetailPresenter(new AccountDetailModel());
        accountDetailPresenter.attachView(this);
        accountDetailPresenter.getAccountDetail(this);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("我的资产");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_asset);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if (accountDetailPresenter != null) {
            accountDetailPresenter.getAccountDetail(this);
        }
    }

    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.btn_jsl_jy, R.id.btn_jsl_zz, R.id.btn_ds_jy})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.img_title_left:
                goBack(view);

                break;
            case R.id.left_ll:
                goBack(view);
                break;

            case R.id.btn_jsl_jy:
            case R.id.btn_jsl_zz:
            case R.id.btn_ds_jy:
                showToast("该功能暂未开启，请稍后使用！");
                break;

            default:
                break;
        }

    }


    @Override
    public void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean) {
        tvJsl.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getJsl()).toString());
        tvJslFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getJsl_freeze()).toString());
        tvJslGyj.setText(new StringBuilder("公益金：").append(accountDetailResponseBean.getJsl_gyj()).toString());
        tvJslLockView.setText(new StringBuilder("锁定：").append(accountDetailResponseBean.getJsl_lock_view()).toString());

        tvDs.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getDs()).append(" T").toString());
        tvDsFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getDs_freeze()).append(" T").toString());
    }

    @Override
    public void onAccountDetailFailed(String msg) {

    }
}
