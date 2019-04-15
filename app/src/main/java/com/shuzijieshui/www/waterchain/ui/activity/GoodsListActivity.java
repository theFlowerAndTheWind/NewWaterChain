package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.BannerListModel;
import com.shuzijieshui.www.waterchain.contract.model.GoodsListModel;
import com.shuzijieshui.www.waterchain.contract.presenter.BannerListPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.GoodsListPresenter;
import com.shuzijieshui.www.waterchain.contract.view.BannerListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.GoodsListViewImpl;
import com.shuzijieshui.www.waterchain.event.SelectFragmentEvent;
import com.shuzijieshui.www.waterchain.ui.adapter.GoodsListsAdapter;
import com.shuzijieshui.www.waterchain.utils.Constants;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import de.greenrobot.event.EventBus;

public class GoodsListActivity extends BaseActivity implements BannerListViewImpl, GoodsListViewImpl {

    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
//    @BindView(R.id.sv)
//    ScrollView sv;
//    @BindView(R.id.banner_guide_content)
//    BGABanner banner;
//    @BindView(R.id.ll_activity)
//    LinearLayout llActivity;
//    @BindView(R.id.xrv_activity)
//    XRecyclerView xrvActivity;
//    @BindView(R.id.ll_goods)
//    LinearLayout llGoods;
//    @BindView(R.id.xrv_goods)
//    XRecyclerView xrvGoods;

    @BindView(R.id.xrv_goods)
    XRecyclerView xrvGoods;

    LinearLayout llHeader;
    BGABanner banner;
    LinearLayout llActivity;
    XRecyclerView xrvActivity;
    LinearLayout llGoods;

    private List<BannerListResponseBean.BannerListEntity> bannerImgs = new ArrayList<>();
    private List<GoodsListsResponseBean> activityList = new ArrayList<>();
    private List<GoodsListsResponseBean> goodsList = new ArrayList<>();
    private GoodsListsAdapter activityAdapter;
    private GoodsListsAdapter goodsAdapter;
    private BannerListPresenter bannerListPresenter;
    private GoodsListPresenter goodsListPresenter;

    /**
     * 热门活动数量有限，仅请求一次。
     */
    private boolean activityDataBack = false;
    private int count = 0;//热门商品数据量；
    //    private boolean dataComplete=false;
    private String target = Constants.TAB_TITLE[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        bannerListPresenter = new BannerListPresenter(new BannerListModel());
        bannerListPresenter.attachView(this);
        goodsListPresenter = new GoodsListPresenter(new GoodsListModel());
        goodsListPresenter.attachView(this);

        initView();
        getIntentData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBannerList();
        getGoodsList(2, 0);//先请求活动内容
    }

    private void getIntentData() {

        String extra = getIntent().getStringExtra("target");
        if (!TextUtils.isEmpty(extra)) {
            target = extra;//有需求：点击banner跳转至该页面
        }
    }


    private void initView() {
        llHeader = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_goods_list_header, null);
        banner = llHeader.findViewById(R.id.banner);
        llActivity = llHeader.findViewById(R.id.ll_activity);
        xrvActivity = llHeader.findViewById(R.id.xrv_activity);
        llGoods=llHeader.findViewById(R.id.ll_goods);
        xrvGoods.addHeaderView(llHeader);

        tvTitleCenter.setText("兑换商城");
        banner.setAdapter(new BGABanner.Adapter<ImageView, BannerListResponseBean.BannerListEntity>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, BannerListResponseBean.BannerListEntity model, int position) {
                GlidImageManager.getInstance().loadImageView(GoodsListActivity.this, model.getImg(), itemView, R.drawable.holder);
            }
        });

        activityAdapter = new GoodsListsAdapter(this, activityList, new GoodsListsAdapter.GoodsListItemListener() {
            @Override
            public void onItemClickListener(int id) {
                jump(id);
            }
        });
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        xrvActivity.setLayoutManager(manager1);
        xrvActivity.setArrowImageView(R.drawable.iconfont_downgrey);
        xrvActivity.setAdapter(activityAdapter);
        xrvActivity.setPullRefreshEnabled(false);
        xrvActivity.setLoadingMoreEnabled(false);
        xrvActivity.setNestedScrollingEnabled(false);


        goodsAdapter = new GoodsListsAdapter(this, goodsList, new GoodsListsAdapter.GoodsListItemListener() {
            @Override
            public void onItemClickListener(int id) {
                jump(id);
            }
        });
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        xrvGoods.setLayoutManager(manager2);
        xrvGoods.setArrowImageView(R.drawable.iconfont_downgrey);
        xrvGoods.setAdapter(goodsAdapter);
        xrvGoods.setPullRefreshEnabled(false);
        xrvGoods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //do nothing
            }

            @Override
            public void onLoadMore() {
                getGoodsList(1, count);
            }
        });
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods_list);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        getBannerList();
        getGoodsList(2, 0);//先请求活动内容
    }

    @Override
    public void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list) {
        bannerImgs.clear();
        bannerImgs.addAll(list);
        banner.setData(bannerImgs, null);
    }

    @Override
    public void onBannerListFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onGoodsListSucc(List<GoodsListsResponseBean> beans, int cate_id) {
        if (cate_id == 1) {//实物商品
            if (beans != null) {
                goodsList.addAll(beans);
                count = goodsList.size();
                if (count <= 0) {
                    llGoods.setVisibility(View.GONE);
                    xrvGoods.setVisibility(View.GONE);
                    return;
                } else {
                    llGoods.setVisibility(View.VISIBLE);
                    xrvGoods.setVisibility(View.VISIBLE);
                    xrvGoods.loadMoreComplete();
                }
                goodsAdapter.notifyDataSetChanged();

            } else {
                llGoods.setVisibility(View.GONE);
                xrvGoods.setVisibility(View.GONE);
                return;
            }
        } else if (cate_id == 2) {//活动
            activityDataBack = true;
            getGoodsList(1, count);
            if (beans != null) {
                activityList.clear();
                activityList.addAll(beans);
                if (activityList.size() <= 0) {
                    llActivity.setVisibility(View.GONE);
                    return;
                } else {
                    llActivity.setVisibility(View.VISIBLE);
                }
                activityAdapter.notifyDataSetChanged();
            } else {
                llActivity.setVisibility(View.GONE);
                return;
            }
        }
    }

    @Override
    public void onGoodsListFail(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    private void getBannerList() {
        if (bannerListPresenter == null) {
            bannerListPresenter = new BannerListPresenter(new BannerListModel());
            bannerListPresenter.attachView(this);
        }
        bannerListPresenter.getBannerList(this, 3, 1);
    }

    private void getGoodsList(int cate_id, int count) {
        if (goodsListPresenter == null) {
            goodsListPresenter = new GoodsListPresenter(new GoodsListModel());
            goodsListPresenter.attachView(this);
        }
        goodsListPresenter.goodsList(this, cate_id, count);
    }

    private void jump(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(GoodsListActivity.this, GoodsDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new SelectFragmentEvent(target));//通知mainactivity切换哪个fragment
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                EventBus.getDefault().post(new SelectFragmentEvent(target));//通知mainactivity切换哪个fragment
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (goodsListPresenter != null) {
            goodsListPresenter.detachView();
        }
        if (bannerListPresenter != null) {
            bannerListPresenter.detachView();
        }
    }
}
