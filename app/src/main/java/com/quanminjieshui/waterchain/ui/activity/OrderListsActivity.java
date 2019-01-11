package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class OrderListsActivity extends BaseActivity implements OrderListsViewImpl, TabLayout.OnTabSelectedListener {

//    @BindView(R.id.img_title_left)
//    ImageView imgTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    private OrderListsPresenter orderListsPresenter;
    private String currentFragmen = "全部";
    private List<OrderListsResponseBean.OrderListEntity> currentList = new ArrayList<>();


    public String[] titles = new String[]{"全部", "待付款", "取件中", "洗涤中", "已完成", "已取消"};
    private ArrayList<OrderListsTabFragment> fragments = new ArrayList<>();
    private OrderListsViewpagerAdapter adapter;
    private List<OrderListsResponseBean.OrderListEntity> orders = new ArrayList<>();
    private List<OrderListsResponseBean.OrderListEntity> unpaid;//未付款
    private List<OrderListsResponseBean.OrderListEntity> transporting;//取件中
    private List<OrderListsResponseBean.OrderListEntity> washing;//洗涤中
    private List<OrderListsResponseBean.OrderListEntity> done;//已完成
    private List<OrderListsResponseBean.OrderListEntity> cancled;//已取消
    /**
     * 是否刷新标识  false:加载更多  数据累加    true：刷新  数据清零  累加
     * 请求成功后必须将其置为false!!!
     */
    private boolean isRefresh = false;


    @OnClick({R.id.left_ll})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.left_ll:
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
//        doRequest(false);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        doRequest(true);
        initView();
    }

    FragmentManager supportFragmentManager;

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
    protected void onResume() {
        super.onResume();
        doRequest(true);//用户修改订单状态后，需重新请求数据     放在oncreat中进行，页面跳转后空白问题
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
            final List<OrderListsResponseBean.OrderListEntity> lists = orderListBean.getLists();
            if (lists != null && lists.size() == 0) {
                container.setVisibility(View.GONE);
                rlHint.setVisibility(View.VISIBLE);
                tvDetail.setText("您还没有任何订单，赶快下单吧！");
                return;
            } else {
                container.setVisibility(View.VISIBLE);
                rlHint.setVisibility(View.GONE);

                if (isRefresh) orders.clear();
                orders.addAll(orderListBean.getLists());
                category();
                selectFragemnt();
                isRefresh = false;
            }
        }

    }

    private void category() {
        if (unpaid == null) unpaid = new ArrayList<>();
        if (transporting == null) transporting = new ArrayList<>();
        if (washing == null) washing = new ArrayList<>();
        if (done == null) done = new ArrayList<>();
        if (cancled == null) cancled = new ArrayList<>();
        if (isRefresh) {
            unpaid.clear();
            transporting.clear();
            washing.clear();
            done.clear();
            cancled.clear();
        }
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

    private void selectFragemnt() {
        currentList.clear();
        switch (currentFragmen) {
            case "全部":
                currentList.addAll(orders);
                break;
            case "待付款":
                currentList.addAll(unpaid);
                break;
            case "取件中":
                currentList.addAll(transporting);
                break;
            case "洗涤中":
                currentList.addAll(washing);
                break;
            case "已完成":
                currentList.addAll(done);
                break;
            case "已取消":
                currentList.addAll(cancled);
                break;
            default:
                currentList.addAll(orders);
                break;
        }
        EventBus.getDefault().post(new OrderListsTabScrollEvent(currentFragmen, currentList));
    }


    @Override
    public void onOrderListFailed(String msg) {
        ToastUtils.showCustomToast(msg);
        isRefresh = false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                currentFragmen = "全部";
                break;
            case 1:
                currentFragmen = "待付款";
                break;
            case 2:
                currentFragmen = titles[2];
                break;
            case 3:
                currentFragmen = titles[3];
                break;
            case 4:
                currentFragmen = titles[4];
                break;
            case 5:
                currentFragmen = titles[5];
                break;
        }
        selectFragemnt();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void doRequest(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (orderListsPresenter == null) {
            orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
            orderListsPresenter.attachView(this);
        }
        orderListsPresenter.getOrderList(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orderListsPresenter != null) {
            orderListsPresenter.detachView();
        }
    }
}
