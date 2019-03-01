package com.shuzijieshui.www.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
import com.shuzijieshui.www.waterchain.beans.InfoListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.AdImgModel;
import com.shuzijieshui.www.waterchain.contract.model.BannerListModel;
import com.shuzijieshui.www.waterchain.contract.model.InfoListModel;
import com.shuzijieshui.www.waterchain.contract.presenter.AdImgPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.BannerListPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.InfoListPresenter;
import com.shuzijieshui.www.waterchain.contract.view.AdImgViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.BannerListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.InfoListViewImpl;
import com.shuzijieshui.www.waterchain.ui.activity.GoodsListsActivity;
import com.shuzijieshui.www.waterchain.ui.activity.InfoDetailActivity;
import com.shuzijieshui.www.waterchain.ui.adapter.InfoListsAdapter;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
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

public class FindFragment extends BaseFragment implements
        InfoListViewImpl,
        BannerListViewImpl,
        AdImgViewImpl,
        XRecyclerView.LoadingListener,
        InfoListsAdapter.OnItemClickListener {


    Unbinder unbinder;
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.img_goods)
    ImageView imgGoods;
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
    private BannerListPresenter bannerListPresenter;
    private AdImgPresenter adImgPresenter;
    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> imgUrlList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoListPresenter = new InfoListPresenter(new InfoListModel());
        infoListPresenter.attachView(this);
        bannerListPresenter = new BannerListPresenter(new BannerListModel());
        bannerListPresenter.attachView(this);
        adImgPresenter = new AdImgPresenter(new AdImgModel());
        adImgPresenter.attachView(this);
        showLoadingDialog();
        requestNetwork();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        },800);
    }

    public void requestNetwork() {
        if (infoListPresenter != null) {
            infoListPresenter.getInfoList(getBaseActivity(), dataCounter);
        }
        if (bannerListPresenter != null) {
            bannerListPresenter.getBannerList(getBaseActivity(), 3, 1);//TODO position=2时无数据，1临时使用
        }
        if (adImgPresenter != null) {
            adImgPresenter.getAdImg(getBaseActivity(), "ad_goods");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initView();
        return rootView;
    }

    private void initView() {
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlidImageManager.getInstance().loadImageView(getBaseActivity(), model, itemView, R.drawable.holder);
            }
        });

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
        //产品要求不跳转
//        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//                Intent intent = new Intent();
//                intent.setClass(getBaseActivity(), WebViewActivity.class);
//                intent.putExtra("URL", imgUrlList.get(position));
//                intent.putExtra("title", nameList.get(position));
//                startActivity(intent);
//            }
//        });
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
        ToastUtils.showCustomToast("加载失败",0);
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

    private void jump(Class<?> cls, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }


    @OnClick({R.id.tv_detail, R.id.img_goods})
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
                jump(GoodsListsActivity.class, null);
            default:
                break;
        }
    }


    @Override
    public void onItemClickListener(int id) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        jump(InfoDetailActivity.class, intent);

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        infoListPresenter.detachView();
        bannerListPresenter.detachView();
        adImgPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list) {
        imgList.clear();
        nameList.clear();
        imgUrlList.clear();
        for (BannerListResponseBean.BannerListEntity listEntity : list) {
            imgList.add(listEntity.getImg());
            nameList.add(listEntity.getName());
            imgUrlList.add(listEntity.getUrl());
            mContentBanner.setData(imgList, null);
        }
    }

    @Override
    public void onBannerListFailed(String msg) {

    }

    @Override
    public void onGetAdImgSuccess(AdImgResponseBean bean) {
        if (bean != null) {

            GlidImageManager.getInstance().loadImageView(getActivity(), bean.getImg(), imgGoods, R.mipmap.img_goods);
        }
    }

    @Override
    public void onGetAdImgFailed(String msg) {
        ToastUtils.showCustomToast(msg,0);
    }
}