package com.shuzijieshui.www.waterchain.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.OrderListsModel;
import com.shuzijieshui.www.waterchain.contract.presenter.OrderListsPresenter;
import com.shuzijieshui.www.waterchain.contract.view.OrderListsViewImpl;
import com.shuzijieshui.www.waterchain.ui.adapter.OrderListsAdapter;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderListsTabFragment extends BaseFragment implements OrderListsViewImpl, XRecyclerView.LoadingListener {


    Unbinder unbinder;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    private String title;
    /**
     * 接口文档中
     * 1未付款 2取件中 3洗涤中 4配送中 5已完成 10已取消
     * <p>
     * 产品原型中
     * 全部 待付款 取件中 洗涤中 已完成  已取消
     */
    private String status = "全部";
    private int intStatus = 0;
    private int count = 0;
    private OrderListsAdapter orderListsAdapter;
    private List<OrderListsResponseBean.OrderListEntity> list = new ArrayList<>();

    private boolean isLoadMore = false;//如果执行loadMore进行网络请求，该值设置为true,list累加,请求成功后必须归位至false;
    private boolean isRefresh;//数据是否刷新标志 true:表示数据需要刷新，list数据clear   false：加载更多或初次加载等情况
    private boolean isVisiable = false;
    private int isFirstVisable = 0;//懒加载使用  为1时进行网络请求
    private BaseActivity baseActivity;//懒加载需要需用
    private OrderListsPresenter orderListsPresenter;

    public void setTitle(String title, BaseActivity activity) {
        this.title = title;
        this.baseActivity = activity;
        selectType();
    }

    private void selectType() {
        switch (title) {
            case "全部":
                intStatus = 0;
                break;
            case "未付款":
                intStatus = 1;
                break;
            case "取件中":
                intStatus = 2;
                break;
            case "洗涤中":
                intStatus = 3;
                break;
            case "已完成":
                intStatus = 5;
                break;
            case "已取消":
                intStatus = 10;
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_lists_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        if (orderListsPresenter == null) {
            orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
            orderListsPresenter.attachView(this);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * viewpager滑动时，仅保留当前Fragment及其左右两侧两个Fragment,共三个Fragment
         *                 最左侧Fragment显示时，其右侧一个Fragment也保留，共2个
         *                 最右侧Fragment显示时，其左侧一个Fragment也保留，共2个
         *                 其他已加载过的Fragment走生命周期onResume
         *
         *
         * 故有以下判断：fragment可见  &&  接收到订单详情页Event
         * 正常滑动情况不执行网络请求
         */
        if (isVisiable && isRefresh) {
            doRequest();
        }
    }

    private void init() {
        orderListsAdapter = new OrderListsAdapter(getActivity(), list);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setAdapter(orderListsAdapter);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(this);
    }


    @Override
    public void onReNetRefreshData(int viewId) {

    }

    /**
     * 1、onCreateView创建时请求
     * 2、可见、且收到订单详情页event时请求
     * 3.以防万一，在setUserVisibleHint执行2
     * 4.下拉刷新
     * 5.上垃加载更多
     */
    private void doRequest() {
        if (!isLoadMore) showLoadingDialog();
        if (orderListsPresenter == null) {
            orderListsPresenter = new OrderListsPresenter(new OrderListsModel());
            orderListsPresenter.attachView(this);
        }

        orderListsPresenter.getOrderList(baseActivity, intStatus, count);
    }


    @Override
    public void onOrderListSuccess(OrderListsResponseBean orderListBean) {
        List<OrderListsResponseBean.OrderListEntity> datas = orderListBean.getLists();
        if (datas != null) {
            if (list != null && list.size() == 0 && datas.size() == 0) {//第一次执行dorequest
                xrv.setVisibility(View.GONE);
                rlHint.setVisibility(View.VISIBLE);
                tvDetail.setText("您还没有相关订单，赶快下单吧！");
            } else {
                xrv.setVisibility(View.VISIBLE);
                rlHint.setVisibility(View.GONE);
                if (isRefresh) list.clear();
                list.addAll(datas);

                count = list.size();
                orderListsAdapter.notifyDataSetChanged();
            }
        }
        //如果因为加载更多进行网络请求，请求完毕后isLoadMore归位至false;
        if (isLoadMore) {
            isLoadMore = false;
        }
        isRefresh = false;//每次执行完毕后归位为不刷新
        dismissLoadingDialog();
    }

    @Override
    public void onOrderListFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
        //如果因为加载更多进行网络请求，请求完毕后isLoadMore归位至false;
        if (isLoadMore) {
            isLoadMore = false;
        }
        isRefresh = false;
    }

    @Override
    public void onRefresh() {
        if (isVisiable) {
            isRefresh = true;
            count = 0;
            doRequest();
            xrv.refreshComplete();
        }
    }

    @Override
    public void onLoadMore() {
        if (isVisiable) {
            isLoadMore = true;
            doRequest();
            xrv.loadMoreComplete();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisiable = isVisibleToUser;
        if (isRefresh && count == 0) {
            doRequest();
            return;
        }
        if (isVisiable) {
            isFirstVisable += 1;

            //用户滑动到该fragment显示时，且该fragment接收到刷新event
            if (isRefresh) {
                doRequest();
                return;
            }
            //懒加载
            if (isFirstVisable == 1) {
                doRequest();
            }else if(isFirstVisable>1){
                if (list != null && list.size() == 0) {
                    xrv.setVisibility(View.GONE);
                    rlHint.setVisibility(View.VISIBLE);
                    tvDetail.setText("您还没有相关订单，赶快下单吧！");
                }
            }
        }

    }

    @Override
    public void showLoadingDialog() {
        if (isVisiable) {
//            super.showLoadingDialog();//懒加载时直接使用fragment的showloadingDialog方法报异常。跟生命周期有关
            baseActivity.showLoadingDialog();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (isVisiable) {
            baseActivity.dismissLoadingDialog();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
