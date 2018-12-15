package com.quanminjieshui.waterchain.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.contract.presenter.BannerListPresenter;
import com.quanminjieshui.waterchain.contract.view.BannerListViewImpl;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by WanghongHe on 2018/12/3 10:54.
 * 首页
 */

public class HomeFragment extends BaseFragment implements BannerListViewImpl {


    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;



    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private BannerListPresenter bannerListPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(){
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initList();
    }

    private void initList() {
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(getBaseActivity())
                        .load(model)
                        .placeholder(R.drawable.holder)
                        .error(R.drawable.holder)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

        mContentBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(bannerListPresenter!=null){
            bannerListPresenter.getBannerList(getBaseActivity(),3,1);
        }
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                ToastUtils.showCustomToast("点击了" + position);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bannerListPresenter!=null){
            bannerListPresenter.detachView();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if(bannerListPresenter!=null){
            bannerListPresenter.getBannerList(getBaseActivity(),3,1);
        }
    }

    @Override
    public void onBannerListSuccess(List<Object> list) {
        LogUtils.d(list.toArray());

    }

    @Override
    public void onBannerListFailed(String msg) {

    }
}
