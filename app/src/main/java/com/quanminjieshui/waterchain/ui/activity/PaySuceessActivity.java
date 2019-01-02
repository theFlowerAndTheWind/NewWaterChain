package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/1.
 * Class Note:支付成功
 */

public class PaySuceessActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    private void initView() {
        tv_title_center.setText("支付成功");
    }

    @OnClick({R.id.left_ll,R.id.tv_gohome,R.id.btn_viewOrder})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_gohome:
                startActivity(new Intent(PaySuceessActivity.this,MainActivity.class));
                break;
            case R.id.btn_viewOrder:
                startActivity(new Intent(PaySuceessActivity.this,OrderListsActivity.class));
                break;
            case R.id.left_ll:
                goBack(view);
                finish();
                break;
                default:break;
        }
    }
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_pay_success);

    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
