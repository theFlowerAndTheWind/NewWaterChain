/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GoodsListsActivity
 * Author: sxt
 * Date: 2019/1/2 9:18 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BannerListResponseBean;
import com.quanminjieshui.waterchain.beans.GoodsListsResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.BannerListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.GoodsListsPresenter;
import com.quanminjieshui.waterchain.contract.view.BannerListViewImpl;
import com.quanminjieshui.waterchain.contract.view.GoodsListViewImpl;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.ui.adapter.GoodsListsAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import de.greenrobot.event.EventBus;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: GoodsListsActivity
 * @Description: 兑换商城
 * @Author: sxt
 * @CreateDate: 2019/1/2 9:18 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 9:18 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsListsActivity extends BaseActivity implements BannerListViewImpl,GoodsListViewImpl{
    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.goodsLists_activity)
    XRecyclerView goodsListsXRActivity;
    @BindView(R.id.goodsLists_commodity)
    XRecyclerView goodsListsXRCommodity;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;
    private GoodsListsPresenter goodsListsPresenter;
    private BannerListPresenter bannerListPresenter;
    private GoodsListsAdapter adapterActivity;
    private GoodsListsAdapter adapterCommodity;
    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> imgUrlList = new ArrayList<>();
    private List<GoodsListsResponseBean> goodsListActivity = new ArrayList<>();
    private List<GoodsListsResponseBean> goodsListCommodity = new ArrayList<>();
    private boolean isRefresh = false;//是否刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        goodsListsPresenter = new GoodsListsPresenter();
        goodsListsPresenter.attachView(this);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("兑换商城");
        relativeHint.setVisibility(View.VISIBLE);
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlidImageManager.getInstance().loadImageView(GoodsListsActivity.this, model, itemView, R.drawable.holder);
            }
        });

        //-----------------------------------------------热门活动-----------------------------------//
        adapterActivity = new GoodsListsAdapter(this, goodsListActivity, new GoodsListsAdapter.GoodsListItemListener() {
            @Override
            public void onItemClickListener(int id) {
                jumpActivity(id);
            }
        });
        goodsListsXRActivity.setArrowImageView(R.drawable.iconfont_downgrey);
        goodsListsXRActivity.setLayoutManager(new LinearLayoutManager(this));
        goodsListsXRActivity.setPullRefreshEnabled(false);
        goodsListsXRActivity.setLoadingMoreEnabled(false);
        goodsListsXRActivity.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                doGoodsListRequest(2);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                doGoodsListRequest(2);
            }
        });
        goodsListsXRActivity.setAdapter(adapterActivity);

        //-----------------------------------------------热门商品-----------------------------------//
        adapterCommodity = new GoodsListsAdapter(this, goodsListCommodity, new GoodsListsAdapter.GoodsListItemListener() {
            @Override
            public void onItemClickListener(int id) {
                jumpActivity(id);
            }
        });
        goodsListsXRCommodity.setArrowImageView(R.drawable.iconfont_downgrey);
        goodsListsXRCommodity.setLayoutManager(new LinearLayoutManager(this));
        goodsListsXRCommodity.setLoadingMoreEnabled(false);
        goodsListsXRCommodity.setPullRefreshEnabled(false);
        goodsListsXRCommodity.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                doGoodsListRequest(1);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                doGoodsListRequest(1);
            }
        });
        goodsListsXRCommodity.setAdapter(adapterCommodity);

        tvDetail.setText("加载数据");
        tvDetail.setTextColor(getResources().getColor(R.color.primary_blue));
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods_lists);
    }

    public void jumpActivity(int id){
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(GoodsListsActivity.this, GoodsDetailActivity.class);
        startActivity(intent);
    }
    @Override
    public void onReNetRefreshData(int viewId) {
        doBannerRequest();
        doGoodsListRequest(1);
        doGoodsListRequest(2);
    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                EventBus.getDefault().post(new SelectFragmentEvent("发现"));
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     *兑换商品请求
     */
    public void doGoodsListRequest(int cateId){
        if (goodsListsPresenter!=null){
            goodsListsPresenter.getGoodsLists(this,cateId,1,"1");
//            showLoadingDialog();
        }
    }

    /**
     * 兑换轮播请求
     */
    public void doBannerRequest(){
        if (bannerListPresenter != null) {
            bannerListPresenter.getBannerList(this, 3, 1);//TODO position=2时无数据，1临时使用
        }
    }

    @Override
    public void onGetGoodsListonRefresh(List<GoodsListsResponseBean> goodsListsResponseBean,int cate_id) {
        if (cate_id == 2){
            if(goodsListsResponseBean.size()>0 && !Util.isEmpty(goodsListsResponseBean)){
                relativeHint.setVisibility(View.GONE);
                goodsListsXRActivity.setVisibility(View.VISIBLE);
            }else{
                relativeHint.setVisibility(View.VISIBLE);
                goodsListsXRActivity.setVisibility(View.GONE);
            }
            goodsListActivity.clear();
            goodsListActivity.addAll(goodsListsResponseBean);
            adapterActivity.notifyDataSetChanged();
            goodsListsXRActivity.refreshComplete();
        }else {
            if(goodsListsResponseBean.size()>0 && !Util.isEmpty(goodsListsResponseBean)){
                relativeHint.setVisibility(View.GONE);
                goodsListsXRCommodity.setVisibility(View.VISIBLE);
            }else{
                relativeHint.setVisibility(View.VISIBLE);
                goodsListsXRCommodity.setVisibility(View.GONE);
            }
            goodsListCommodity.clear();
            goodsListCommodity.addAll(goodsListsResponseBean);
            adapterCommodity.notifyDataSetChanged();
            goodsListsXRCommodity.refreshComplete();
        }

        dismissLoadingDialog();
    }

    @Override
    public void onGetGoodsListonloadMore(List<GoodsListsResponseBean> goodsListsResponseBean,int cate_id) {
        if (cate_id == 2){
            if(goodsListsResponseBean.size()>0 && !Util.isEmpty(goodsListsResponseBean)){
                relativeHint.setVisibility(View.GONE);
                goodsListsXRActivity.setVisibility(View.VISIBLE);
            }else{
                relativeHint.setVisibility(View.VISIBLE);
                goodsListsXRActivity.setVisibility(View.GONE);
            }
            goodsListActivity.addAll(goodsListsResponseBean);
            adapterActivity.notifyDataSetChanged();
            goodsListsXRActivity.loadMoreComplete();
        }else {
            if(goodsListsResponseBean.size()>0 && !Util.isEmpty(goodsListsResponseBean)){
                relativeHint.setVisibility(View.GONE);
                goodsListsXRCommodity.setVisibility(View.VISIBLE);
            }else{
                relativeHint.setVisibility(View.VISIBLE);
                goodsListsXRCommodity.setVisibility(View.GONE);
            }
            goodsListCommodity.addAll(goodsListsResponseBean);
            adapterCommodity.notifyDataSetChanged();
            goodsListsXRCommodity.loadMoreComplete();
        }

        dismissLoadingDialog();
    }

    @Override
    public void onGetGoodsListFailed(boolean isRefresh, String msg,int cate_id) {
        if (cate_id == 2){
            if (isRefresh) {
                goodsListsXRActivity.refreshComplete();
            } else {
                goodsListsXRActivity.loadMoreComplete();
            }
        }else{
            if (isRefresh) {
                goodsListsXRCommodity.refreshComplete();
            } else {
                goodsListsXRCommodity.loadMoreComplete();
            }
        }

        dismissLoadingDialog();
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
    protected void onResume() {
        super.onResume();
        doBannerRequest();
        doGoodsListRequest(1);
        doGoodsListRequest(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (goodsListsPresenter!=null){
            goodsListsPresenter.detachView();
        }
        if (bannerListPresenter!=null){
            bannerListPresenter.detachView();
        }
    }
}