package com.shuzijieshui.www.waterchain.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.event.OrderListsTabScrollEvent;
import com.shuzijieshui.www.waterchain.ui.adapter.OrderListsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderListsTabFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private String title;
    private OrderListsAdapter orderListsAdapter;
    private List<OrderListsResponseBean.OrderListEntity> datas;

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_lists_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        datas = new ArrayList<>();

        orderListsAdapter = new OrderListsAdapter(getActivity(), datas);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setAdapter(orderListsAdapter);
        xrv.setLoadingMoreEnabled(false);
        xrv.setPullRefreshEnabled(false);
    }


    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onEventMainThread(OrderListsTabScrollEvent event) {
        final String status = event.getStatus();
        final List<OrderListsResponseBean.OrderListEntity> list = event.getList();
        if (title.equals(status)) {
            this.datas.clear();
            this.datas.addAll(list);
            orderListsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
