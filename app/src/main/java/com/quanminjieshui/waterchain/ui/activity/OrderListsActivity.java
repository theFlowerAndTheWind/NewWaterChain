package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;
import com.quanminjieshui.waterchain.contract.model.OrderListsModel;
import com.quanminjieshui.waterchain.contract.presenter.OrderListsPresenter;
import com.quanminjieshui.waterchain.contract.view.OrderListsViewImpl;
import com.quanminjieshui.waterchain.event.OrderListsTabScrollEvent;
import com.quanminjieshui.waterchain.ui.adapter.OrderListsViewpagerAdapter;
import com.quanminjieshui.waterchain.ui.fragment.OrderListsTabFragment;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class OrderListsActivity extends BaseActivity implements OrderListsViewImpl, TabLayout.OnTabSelectedListener {

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

    private OrderListsPresenter orderListsPresenter;


    public String[] titles = new String[]{"全部", "待付款", "取件中", "洗涤中", "已完成", "已取消"};
    private ArrayList<OrderListsTabFragment> fragments = new ArrayList<>();
    private OrderListsViewpagerAdapter adapter;
    private List<OrderListsResponseBean.OrderListEntity> orders;
    private Map<String, List<OrderListsResponseBean.OrderListEntity>> categoryOrders;
    private List<OrderListsResponseBean.OrderListEntity> unpaid;//未付款
    private List<OrderListsResponseBean.OrderListEntity> transporting;//取件中
    private List<OrderListsResponseBean.OrderListEntity> washing;//洗涤中
    private List<OrderListsResponseBean.OrderListEntity> done;//已完成
    private List<OrderListsResponseBean.OrderListEntity> cancled;//已取消


    @OnClick({R.id.img_title_left})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
        orderListsPresenter.attachView(this);
        orderListsPresenter.getOrderList(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("洗涤订单");
        tabLayout.addOnTabSelectedListener(this);

        for (int i = 0; i < titles.length; i++) {
            OrderListsTabFragment fragment = new OrderListsTabFragment();
            fragment.setTitle(titles[i]);
            fragments.add(fragment);
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewpager, false);

        adapter = new OrderListsViewpagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onOrderListSuccess(OrderListsResponseBean orderListBean) {
        if (orderListBean != null) {
            if (orderListBean.getLists() != null) {
                orders = orderListBean.getLists();
                category();
            } else {
                orders = new ArrayList<>();
            }
            EventBus.getDefault().post(new OrderListsTabScrollEvent("全部",orders));
        }

    }

    private void category() {
        categoryOrders = new HashMap<String, List<OrderListsResponseBean.OrderListEntity>>();
        unpaid = new ArrayList<>();
        transporting = new ArrayList<>();
        washing = new ArrayList<>();
        done = new ArrayList<>();
        cancled = new ArrayList<>();
//        categoryOrders.put(titles[0],orders);
//        categoryOrders.put(titles[1], unpaid);
//        categoryOrders.put(titles[2], transporting);
//        categoryOrders.put(titles[3], washing);
//        categoryOrders.put(titles[4], done);
//        categoryOrders.put(titles[5], cancled);

        for (OrderListsResponseBean.OrderListEntity entity : orders) {
            String status = entity.getStatus();
            if (!TextUtils.isEmpty(status)) {
                switch (status) {
                    case "待付款":
                        unpaid.add(entity);
                        break;
                    case "取件中":
                        transporting.add(entity);
                        break;
                    case "洗涤中":
                        washing.add(entity);
                        break;
                    case "已完成":
                        done.add(entity);
                        break;
                    case "已取消":
                        cancled.add(entity);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    @Override
    public void onOrderListFailed(String msg) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                EventBus.getDefault().post(new OrderListsTabScrollEvent("全部",orders));
                break;
            case 1:
                EventBus.getDefault().post(new OrderListsTabScrollEvent("待付款",unpaid));
                break;
            case 2:
                EventBus.getDefault().post(new OrderListsTabScrollEvent(titles[2],transporting));
                break;
            case 3:
                EventBus.getDefault().post(new OrderListsTabScrollEvent(titles[3],washing));
                break;
            case 4:
                EventBus.getDefault().post(new OrderListsTabScrollEvent(titles[4],done));
                break;
            case 5:
                EventBus.getDefault().post(new OrderListsTabScrollEvent(titles[5],cancled));
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderListsPresenter.detachView();
    }
}
