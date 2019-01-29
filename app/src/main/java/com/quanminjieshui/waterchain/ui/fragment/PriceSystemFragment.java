package com.quanminjieshui.waterchain.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.ui.activity.FactoryServiceActivity;
import com.quanminjieshui.waterchain.ui.adapter.PriceSysAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.twowaytable.ContentAdapter;
import com.quanminjieshui.waterchain.ui.widget.twowaytable.LeftAdapter;
import com.quanminjieshui.waterchain.ui.widget.twowaytable.TopAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/25.
 * Class Note:价格体系
 */

public class PriceSystemFragment extends BaseFragment {

    //    @BindView(R.id.topRecyclerView)
//    RecyclerView topRecyclerView;
//    @BindView(R.id.leftRecyclerView)
//    RecyclerView leftRecyclerView;
//    @BindView(R.id.contentRecyclerView)
//    RecyclerView contentRecyclerView;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private FactoryServiceActivity factoryServiceActivity;
    //    private List<FactoryServiceResponseBean.WashFatoryCageGory> list = new ArrayList<>();
//    private RecyclerView.Adapter topAdapter, leftAdapter, contentAdapter;
//    private String[] typesTitles = {"单位", "规格", "单价（元）"};
    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private String c_name, unit_price, piece, standard;
    private int fscid;

    private LinkedList<FactoryServiceResponseBean.WashFatoryCageGory> data = new LinkedList<>();
    private PriceSysAdapter priceSysAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_price_system, container, false);
        ButterKnife.bind(this, rootView);
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initView();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {
        priceSysAdapter=new PriceSysAdapter(getActivity(),data);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setLoadingMoreEnabled(false);
        xrv.setPullRefreshEnabled(false);
        xrv.setAdapter(priceSysAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        factoryServiceActivity = (FactoryServiceActivity) getActivity();
        data.clear();
        List<FactoryServiceResponseBean.WashFatoryCageGory> list = factoryServiceActivity.getList();
        data.addAll(list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.factoryServiceActivity = (FactoryServiceActivity) context;
        if (context instanceof FactoryServiceActivity) {
            data.clear();
            data.addAll(((FactoryServiceActivity) context).getList());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.factoryServiceActivity = (FactoryServiceActivity) activity;
        if (activity instanceof FactoryServiceActivity) {
            data.clear();
            data.addAll(((FactoryServiceActivity) activity).getList());
        }

    }

//    private class SelfRemovingOnScrollListener extends RecyclerView.OnScrollListener {
//        @Override
//        public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                recyclerView.removeOnScrollListener(this);
//            }
//        }
//    }
//
//    private void initView() {
//        //表格title
//        LinearLayoutManager topManager = new LinearLayoutManager(getBaseActivity());
//        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        topRecyclerView.setLayoutManager(topManager);
//        topAdapter = new TopAdapter(typesTitles, getBaseActivity());
//        topRecyclerView.setAdapter(topAdapter);
//
//        //表格左侧
//        LinearLayoutManager leftManager = new LinearLayoutManager(getBaseActivity());
//        leftManager.setOrientation(LinearLayoutManager.VERTICAL);
//        leftAdapter = new LeftAdapter(list, getBaseActivity());
//        leftRecyclerView.setLayoutManager(leftManager);
//        leftRecyclerView.setAdapter(leftAdapter);
//
//        final RecyclerView.OnScrollListener leftListener = new SelfRemovingOnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
//                    contentRecyclerView.scrollBy(dx, dy);
//                }
//            }
//        };
//        leftRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            private int mLastY;
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                if (contentRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
//                    return true;
//                }
//                Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
//                if (!ret) {
//                    onTouchEvent(rv, e);
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                final int action;
//                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && contentRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
//                    mLastY = rv.getScrollY();
//                    rv.removeOnScrollListener(leftListener);
//                    rv.addOnScrollListener(leftListener);
//                } else {
//                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
//                        rv.removeOnScrollListener(leftListener);
//                    }
//                }
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//
//        LinearLayoutManager contentManager = new LinearLayoutManager(getBaseActivity());
//        contentManager.setOrientation(LinearLayoutManager.VERTICAL);
//        contentAdapter = new ContentAdapter(list, getBaseActivity());
//        contentRecyclerView.setLayoutManager(contentManager);
//        contentRecyclerView.setAdapter(contentAdapter);
//        final RecyclerView.OnScrollListener contentListener = new SelfRemovingOnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
//                    leftRecyclerView.scrollBy(dx, dy);
//                }
//            }
//        };
//        contentRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            private int mLastY;
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                if (leftRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
//                    return true;
//                }
//                Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
//                if (!ret) {
//                    onTouchEvent(rv, e);
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                final int action;
//                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && leftRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
//                    mLastY = rv.getScrollY();
//                    rv.removeOnScrollListener(contentListener);
//                    rv.addOnScrollListener(contentListener);
//                } else {
//                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
//                        rv.removeOnScrollListener(contentListener);
//                    }
//                }
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
