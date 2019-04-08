package com.shuzijieshui.www.waterchain.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.ui.adapter.OrderListsViewpagerAdapter;
import com.shuzijieshui.www.waterchain.ui.fragment.OrderListsTabFragment;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderListsActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public String[] titles = new String[]{"全部", "待付款", "服务中",  "已完成", "已取消"};
    private ArrayList<OrderListsTabFragment> fragments = new ArrayList<>();
    private OrderListsViewpagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar);
        init();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void init() {
        tvTitleCenter.setText("我的订单");
        for (int i = 0; i < titles.length; i++) {
            OrderListsTabFragment fragment = new OrderListsTabFragment();
            fragment.setTitle(titles[i],this);
            fragments.add(fragment);
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager, false);
        adapter = new OrderListsViewpagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
    }

    @OnClick({})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
