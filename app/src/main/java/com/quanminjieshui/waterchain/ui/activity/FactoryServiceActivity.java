package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
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

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public class FactoryServiceActivity extends BaseActivity {


    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.factory_service_tablayout)
    TabLayout factoryTabLayout;
    @BindView(R.id.factory_service_viewpager)
    ViewPager factoryViewpager;

    private String[] titles=new String[]{"价格体系","流程介绍","常见问题"};
    private ArrayList<OrderListsTabFragment>fragments=new ArrayList<>();
    private OrderListsViewpagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar);
        initView();

    }

    private void initView() {
        tvTitleCenter.setText("宾馆酒店系列");
        for(int i=0;i<titles.length;i++){
            OrderListsTabFragment fragment=new OrderListsTabFragment();
            fragment.setTxt(titles[i]);
            fragments.add(fragment);
            factoryTabLayout.addTab(factoryTabLayout.newTab());
        }
        factoryTabLayout.setupWithViewPager(factoryViewpager,false);
        adapter = new OrderListsViewpagerAdapter(getSupportFragmentManager(),fragments,titles);
        factoryViewpager.setAdapter(adapter);

        for(int i=0;i<titles.length;i++){
            factoryTabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    @OnClick({R.id.img_title_left})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_factory_service);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
