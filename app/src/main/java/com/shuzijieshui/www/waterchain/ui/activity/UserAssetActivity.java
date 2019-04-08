//package com.shuzijieshui.www.waterchain.ui.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.TextView;
//
//import com.shuzijieshui.www.waterchain.R;
//import com.shuzijieshui.www.waterchain.base.BaseActivity;
//import com.shuzijieshui.www.waterchain.beans.AccountDetailResponseBean;
//import com.shuzijieshui.www.waterchain.contract.model.AccountDetailModel;
//import com.shuzijieshui.www.waterchain.contract.presenter.AccountDetailPresenter;
//import com.shuzijieshui.www.waterchain.contract.view.AccountDetailViewImpl;
//import com.shuzijieshui.www.waterchain.event.SelectFragmentEvent;
//import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import de.greenrobot.event.EventBus;
//
///**
// * @Author: fushizhe
// */
//public class UserAssetActivity extends BaseActivity implements AccountDetailViewImpl {
//    @BindView(R.id.tv_title_center)
//    TextView tv_title_center;
//    @BindView(R.id.title_bar)
//    View title_bar;
//    @BindView(R.id.tv_jsl)
//    TextView tvJsl;
//    @BindView(R.id.tv_jsl_freeze)
//    TextView tvJslFreeze;
//    @BindView(R.id.tv_jsl_lock_view)
//    TextView tvJslLockView;
//    @BindView(R.id.tv_jsl_gyj)
//    TextView tvJslGyj;
//    @BindView(R.id.tv_ds)
//    TextView tvDs;
//    @BindView(R.id.tv_ds_freeze)
//    TextView tvDsFreeze;
//
//    private float jsl = 0;
//    public static final String EXTRA_JSL="extra_jsl";
//
//    private AccountDetailPresenter accountDetailPresenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        accountDetailPresenter = new AccountDetailPresenter(new AccountDetailModel());
//        accountDetailPresenter.attachView(this);
//        accountDetailPresenter.getAccountDetail(this);
//
//        StatusBarUtil.setImmersionStatus(this, title_bar);
//        initView();
//    }
//
//    private void initView() {
//        tv_title_center.setText("我的资产");
//    }
//
//    @Override
//    public void initContentView() {
//        setContentView(R.layout.activity_user_asset);
//    }
//
//    @Override
//    public void onReNetRefreshData(int viewId) {
//        if (accountDetailPresenter != null) {
//            accountDetailPresenter.getAccountDetail(this);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (accountDetailPresenter != null) {
//            accountDetailPresenter.getAccountDetail(this);
//        }
//    }
//
//    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.btn_jsl_jy, R.id.btn_jsl_zz, R.id.btn_ds_jy})
//    public void onClick(View view) {
//        int id = view.getId();
//
//        switch (id) {
//            case R.id.img_title_left:
//                goBack(view);
//
//                break;
//            case R.id.left_ll:
//                goBack(view);
//                break;
//
//            case R.id.btn_jsl_jy://跳转交易 TODO 切换了 但是没有显示页面
////                EventBus.getDefault().post(new SelectFragmentEvent("交易"));
////                finish();
////                break;
//            case R.id.btn_ds_jy://跳转交易
//                startActivity(new Intent(this,MainActivity.class));
//                EventBus.getDefault().post(new SelectFragmentEvent("交易中心"));
//                finish();
//                break;
//            case R.id.btn_jsl_zz://弹窗
//                Intent intent=new Intent(UserAssetActivity.this,MvJslActivity.class);
//                intent.putExtra(EXTRA_JSL,jsl);
//                startActivity(intent);
//                overridePendingTransition(R.anim.actionsheet_dialog_in, 0);
//                break;
//
//
//
//            default:
//                break;
//        }
//
//    }
//
//
//    @Override
//    public void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean) {
//        String jslStr = accountDetailResponseBean.getJsl();
//        try {
//            if (!TextUtils.isEmpty(jslStr)) {
//                jsl = Float.valueOf(jslStr);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsl=0;
//        }
//
//        tvJsl.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getJsl()).toString());
//        tvJslFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getJsl_freeze()).toString());
//        tvJslGyj.setText(new StringBuilder("公益金：").append(accountDetailResponseBean.getJsl_gyj()).toString());
//        tvJslLockView.setText(new StringBuilder("锁定：").append(accountDetailResponseBean.getJsl_lock_view()).toString());
//
//        tvDs.setText(new StringBuilder("可用：").append(accountDetailResponseBean.getDs()).append(" T").toString());
//        tvDsFreeze.setText(new StringBuilder("冻结：").append(accountDetailResponseBean.getDs_freeze()).append(" T").toString());
//    }
//
//    @Override
//    public void onAccountDetailFailed(String msg) {
//
//    }
//}
