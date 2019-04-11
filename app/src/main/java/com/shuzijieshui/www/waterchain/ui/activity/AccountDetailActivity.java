package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AccountDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.AccountDetailModel;
import com.shuzijieshui.www.waterchain.contract.presenter.AccountDetailPresenter;
import com.shuzijieshui.www.waterchain.contract.view.AccountDetailViewImpl;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountDetailActivity extends BaseActivity implements AccountDetailViewImpl {
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ll_phantom_stock)
    LinearLayout llStock;
    @BindView(R.id.tv_stock)
    TextView tvStock;
    @BindView(R.id.tv_stock_freeze)
    TextView tvStockFreeze;
    @BindView(R.id.tv_stock_lock_view)
    TextView tvStockLockView;
    @BindView(R.id.tv_jsl_all)
    TextView tvJslAll;
    @BindView(R.id.tv_jsl_freeze)
    TextView tvJslFreeze;
    @BindView(R.id.tv_tip)
    TextView tvTip;

    private AccountDetailPresenter accountDetailPresenter;
    private float fStock = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();

        accountDetailPresenter = new AccountDetailPresenter(new AccountDetailModel());
        accountDetailPresenter.attachView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAccountDetail();
    }

    private void initView() {
        tvTitleCenter.setText("我的资产");
    }

    private void getAccountDetail() {
        if (accountDetailPresenter == null) {
            accountDetailPresenter = new AccountDetailPresenter(new AccountDetailModel());
            accountDetailPresenter.attachView(this);
        }
        showLoadingDialog();
        accountDetailPresenter.getAccountDetail(this);
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_account_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        getAccountDetail();
    }

    @OnClick({R.id.left_ll, R.id.btn_stock_2_jsl, R.id.btn_buy_back, R.id.btn_unlock})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_stock_2_jsl:
                jump(1);
                break;
            case R.id.btn_buy_back:
                jump(0);
                break;
            default:break;
        }
    }

    private void jump(int style) {
        Intent intent = new Intent(this, StockActivity.class);
        intent.putExtra(StockActivity.STYLE, style);
        intent.putExtra(StockActivity.STOCK, fStock);
        startActivity(intent);
        overridePendingTransition(R.anim.actionsheet_dialog_in, 0);
    }




    @Override
    public void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean) {
        if (accountDetailResponseBean != null) {
            try {
                String investor = accountDetailResponseBean.getIs_investor();
                if (investor.equals("1")) {//用户为投资人
                    String stock = accountDetailResponseBean.getStock();
                    String stockFreeze = accountDetailResponseBean.getStock_freeze();
                    String stockLockView = accountDetailResponseBean.getStock_lock_view();
                    tvStock.setText("可用：" + stock);
                    tvStockFreeze.setText("冻结：" + stockFreeze);
                    tvStockLockView.setText("锁定：" + stockLockView);
                    fStock = Util.str2Flt(Util.deleteComma(stock));
                    llStock.setVisibility(View.VISIBLE);
                } else if (investor.equals("0")) {
                    llStock.setVisibility(View.GONE);
                }

                String jslAll = accountDetailResponseBean.getJsl_all();
                String jslFreeze=accountDetailResponseBean.getJsl_freeze();

                String jslAllStr=new StringBuilder("可用：").append(String.format("%.5f",Util.str2Flt(Util.deleteComma(jslAll)))).toString();
                String jslFreezeStr=new StringBuilder("可用：").append(String.format("%.5f",Util.str2Flt(Util.deleteComma(jslFreeze)))).toString();
                tvJslAll.setText(jslAllStr);
                tvJslFreeze.setText(jslFreezeStr);
                final String balance_v = accountDetailResponseBean.getBalance_v();
                final String expire_time = accountDetailResponseBean.getExpire_time();
                if (!TextUtils.isEmpty(balance_v) && !TextUtils.isEmpty(expire_time)) {
                    String balanceV = String.format("%.5f", Util.str2Flt(Util.deleteComma(balance_v)));
                    tvTip.setText("提示：其中 " + balanceV + " 水方即将在 " + expire_time + " 到期");
                }else{
                    tvTip.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dismissLoadingDialog();
    }

    @Override
    public void onAccountDetailFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 1);
    }
}
