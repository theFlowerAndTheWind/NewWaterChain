package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/1.
 * Class Note:支付成功
 */

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.order_type_str)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.btn_viewOrder)
    Button btn;
    @BindView(R.id.tv_gohome)
    TextView tv4;

    private String from = "";
    private String goods = "";
    private String oid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        getIntentExtra();
        initView();
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        if (intent != null) {
            from = intent.getStringExtra("from");
            goods = intent.getStringExtra("goodsName");
            if (!TextUtils.isEmpty(from) && from.equals("ConfirmOrderActivity1")) {
                oid = intent.getStringExtra("oid");
            }
        }
    }

    private void initView() {
        tv_title_center.setText("支付成功");
        if (!TextUtils.isEmpty(from)) {
            if (from.equals(ConfirmGoodsOrderActivity.class.getSimpleName())) {
                tv_title_center.setText("兑换商城");
                tv1.setText("你已成功兑换：");
                tv2.setText(goods);
                tv3.setVisibility(View.GONE);
                btn.setText("兑换记录");
                tv4.setText("返回商城");
            } else if (from.equals("ConfirmOrderActivity1")) {
                tv1.setText("已成功下单，向企业打款后可获取服务");
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
            }

        }
    }

    @OnClick({R.id.left_ll, R.id.tv_gohome, R.id.btn_viewOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_gohome:
                if (from.equals("ConfirmGoodsOrderActivity")) {
                    jump(GoodsListActivity.class);
                } else {
                    jump(MainActivity.class);
                }
                finish();
                break;
            case R.id.btn_viewOrder:
                if (from.equals(ConfirmGoodsOrderActivity.class.getSimpleName())) {
                    jump(GoodsActivity.class);
                } else if (from.equals("ConfirmOrderActivity")) {
                    jump(OrderListsActivity.class);
                } else if (from.equals("ConfirmOrderActivity1")) {
                    Intent intent = new Intent(this, OrderDetailActivity1.class);
                    intent.putExtra("id", oid);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.left_ll:
                goBack(view);
                finish();
                break;
            default:
                break;
        }
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_pay_success);

    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
