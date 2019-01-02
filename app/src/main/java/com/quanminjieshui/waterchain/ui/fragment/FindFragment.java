package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.InfoListsResponseBean;
import com.quanminjieshui.waterchain.contract.model.InfoListModel;
import com.quanminjieshui.waterchain.contract.presenter.InfoListPresenter;
import com.quanminjieshui.waterchain.contract.view.InfoListViewImpl;
import com.quanminjieshui.waterchain.ui.activity.GoodsActivity;
import com.quanminjieshui.waterchain.ui.activity.GoodsListsActivity;
import com.quanminjieshui.waterchain.ui.activity.InfoDetailActivity;
import com.quanminjieshui.waterchain.ui.adapter.InfoListsAdapter;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:
 */

public class FindFragment extends BaseFragment implements InfoListViewImpl, XRecyclerView.LoadingListener, InfoListsAdapter.OnItemClickListener {


    Unbinder unbinder;
    @BindView(R.id.banner_guide_content)
    BGABanner bannerGuideContent;
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;
    private int dataCounter = 0;
    private LinkedList<InfoListsResponseBean.InfoEntity> list = new LinkedList<>();
    private InfoListsAdapter infoListsAdapter;
    private InfoListPresenter infoListPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        infoListPresenter = new InfoListPresenter(new InfoListModel());
        infoListPresenter.attachView(this);
        infoListPresenter.getInfoList(getBaseActivity(), dataCounter);
        initView();
        return rootView;
    }

    private void initView() {
        infoListsAdapter = new InfoListsAdapter(getActivity(), list);
        infoListsAdapter.setOnItemClickListener(this);
//        xrv.setNestedScrollingEnabled(false);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setLoadingMoreEnabled(true);
        xrv.setLoadingListener(this);
        xrv.setAdapter(infoListsAdapter);
        tvDetail.setText("加载数据");
        tvDetail.setTextColor(getResources().getColor(R.color.primary_blue));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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
    public void infoListSuccess(InfoListsResponseBean infoListResponseBean) {
        if (infoListResponseBean != null && infoListResponseBean.getLists() != null) {
            List<InfoListsResponseBean.InfoEntity> tempList = infoListResponseBean.getLists();
            if (tempList.size() > 0) {
                list.addAll(tempList);
                dataCounter = list.size();
                infoListsAdapter.notifyDataSetChanged();

                xrv.setVisibility(View.VISIBLE);
                relativeHint.setVisibility(View.GONE);
            }
            if (dataCounter <= 0) {
                xrv.setVisibility(View.GONE);
                relativeHint.setVisibility(View.VISIBLE);
                list.clear();
                infoListsAdapter.notifyDataSetChanged();
                dataCounter = 0;
            }
            xrv.loadMoreComplete();
            xrv.refreshComplete();
        }
    }

    @Override
    public void infoListFailed(String msg) {
        ToastUtils.showCustomToast("加载失败");
    }

    @Override
    public void onRefresh() {
        list.clear();
        infoListsAdapter.notifyDataSetChanged();
        dataCounter = 0;
        getData(0);
    }

    @Override
    public void onLoadMore() {
        getData(dataCounter);
    }

    private void getData(int count) {
        if (infoListPresenter != null) {
            infoListPresenter.getInfoList(getBaseActivity(), count);
        }
    }

    private void jump(Class<?> cls,Intent intent){
        if(intent==null){
            intent=new Intent();
        }
        intent.setClass(getActivity(),cls);
        startActivity(intent);
    }


    @OnClick({R.id.tv_detail,R.id.img_goods})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_detail:
                list.clear();
                infoListsAdapter.notifyDataSetChanged();
                dataCounter = 0;
                getData(dataCounter);
                break;

            case R.id.img_goods:
               jump(GoodsListsActivity.class,null);
            default:
                break;
        }
    }


    @Override
    public void onItemClickListener(int id) {
        Intent intent=new Intent();
        intent.putExtra("id",id);
        jump(InfoDetailActivity.class,intent);

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        infoListPresenter.detachView();
        super.onDestroy();
    }
}
