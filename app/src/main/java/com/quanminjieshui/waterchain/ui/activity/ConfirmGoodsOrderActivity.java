package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;

public class ConfirmGoodsOrderActivity extends BaseActivity {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    TextView titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentExtra();
        StatusBarUtil.setImmersionStatus(this,titleBar);
        initView();
    }

    private void getIntentExtra() {

    }

    private void initView() {
        tvTitleCenter.setText("确认订单");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_goods_order);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
