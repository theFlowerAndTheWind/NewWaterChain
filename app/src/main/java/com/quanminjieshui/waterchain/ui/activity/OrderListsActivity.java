package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.ui.adapter.OrderListsViewpagerAdapter;
import com.quanminjieshui.waterchain.ui.fragment.OrderListsTabFragment;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderListsActivity extends BaseActivity {

    @BindView(R.id.img_title_left)
    ImageView imgTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    private String[] titles=new String[]{"全部","待付款","取件中","洗涤中","已完成","已取消"};
    private ArrayList<OrderListsTabFragment>fragments=new ArrayList<>();
    private OrderListsViewpagerAdapter adapter;

    @OnClick({R.id.img_title_left})
    public void onClick(View v){
        int id=v.getId();
        switch (id){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar );
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("洗涤订单");

        for(int i=0;i<titles.length;i++){
            OrderListsTabFragment fragment=new OrderListsTabFragment();
            fragment.setTxt(titles[i]);
            fragments.add(fragment);
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewpager,false);

        adapter = new OrderListsViewpagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewpager.setAdapter(adapter);

        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}
