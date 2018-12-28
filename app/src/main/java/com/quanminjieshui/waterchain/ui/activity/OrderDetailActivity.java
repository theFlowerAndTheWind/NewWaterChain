package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.quanminjieshui.waterchain.base.BaseActivity;

public class OrderDetailActivity extends BaseActivity {
    @Override
    public void initContentView() {
        TextView tv = new TextView(this);
        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("fid"));
        setContentView(tv);
        showToast(intent.getStringExtra("fid"));
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
