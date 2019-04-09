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

    public static String format(String str) {
        // 创建一个空的StringBuilder对象
        StringBuilder sb = new StringBuilder();
        // 追加字符串
        sb.append(str);
        // 从后往前每隔三位插入逗号
        int last = sb.length();
        for (int i = last - 3; i > 0; i -= 3) {
            sb.insert(i, ',');
        }
        // 将StringBuilder对象转换为String对象并输出
        return sb.toString();
    }


    @Override
    public void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean) {
        if (accountDetailResponseBean != null) {
            try {
                String investor = accountDetailResponseBean.getIs_investor();
                if (investor.equals("1")) {//用户为投资人
                    fStock = Float.valueOf(accountDetailResponseBean.getStock());
                    String stock = format(accountDetailResponseBean.getStock());
                    String stockFreeze = format(accountDetailResponseBean.getStock_freeze());
                    String stockLockView = format(accountDetailResponseBean.getStock_lock_view());
                    tvStock.setText("可用：" + stock);
                    tvStockFreeze.setText("冻结：" + stockFreeze);
                    tvStockLockView.setText("锁定：" + stockLockView);
                    llStock.setVisibility(View.VISIBLE);
                } else if (investor.equals("0")) {
                    llStock.setVisibility(View.GONE);
                }
                String jslAll = String.format("%.5f", Float.valueOf(accountDetailResponseBean.getJsl_all()));
                String jslFreeze = String.format("%.5f", Float.valueOf(accountDetailResponseBean.getJsl_freeze()));
                tvJslAll.setText("可用：" + jslAll);
                tvJslFreeze.setText("冻结：" + jslFreeze);
                final String balance_v = accountDetailResponseBean.getBalance_v();
                final String expire_time = accountDetailResponseBean.getExpire_time();
                if (!TextUtils.isEmpty(balance_v) && !TextUtils.isEmpty(expire_time)) {
                    String balanceV = String.format("%.5f", Float.valueOf(balance_v));
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
