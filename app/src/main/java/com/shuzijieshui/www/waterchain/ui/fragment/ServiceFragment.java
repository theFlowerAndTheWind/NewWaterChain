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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.beans.ServiceEntity;
import com.shuzijieshui.www.waterchain.contract.model.AdImgModel;
import com.shuzijieshui.www.waterchain.contract.model.ServiceListModel;
import com.shuzijieshui.www.waterchain.contract.presenter.AdImgPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.ServiceListPresneter;
import com.shuzijieshui.www.waterchain.contract.view.AdImgViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.ServiceListViewImpl;
import com.shuzijieshui.www.waterchain.ui.activity.ServiceDetailActivity;
import com.shuzijieshui.www.waterchain.ui.adapter.ServiceListAdapter;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:洗涤
 * <p>
 * update by sxt 2019/4/1
 * 产品服务
 * 原业务为洗涤，现更改为产品和服务业务。
 */

public class ServiceFragment extends BaseFragment implements ServiceListViewImpl, AdImgViewImpl {
    RelativeLayout llHeader;
    ImageView imgAd;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    private ServiceListPresneter serviceListPresneter;
    private AdImgPresenter adImgPresenter;
    private Unbinder unbinder;
    private View rootView;
    private ServiceListAdapter serviceListAdapter;
    private List<ServiceEntity> listEntities = new ArrayList<>();
    private int count = 0;
    private List<Integer>ids=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceListPresneter = new ServiceListPresneter(new ServiceListModel());
        serviceListPresneter.attachView(this);
        adImgPresenter = new AdImgPresenter(new AdImgModel());
        adImgPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_service, container, false);
            ButterKnife.bind(this, rootView);
        }
        initList();
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        llHeader = (RelativeLayout) LayoutInflater.from(getBaseActivity()).inflate(R.layout.layout_service_fragment_header, null);
        imgAd = llHeader.findViewById(R.id.img_ad);
        xrv.addHeaderView(llHeader);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingDialog();
        getServiceList();
        if (adImgPresenter != null) {
            adImgPresenter.getAdImg(getBaseActivity(), "ad_fac");
        }
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        getServiceList();
//    }

    @Override
    public void onReNetRefreshData(int viewId) {
        showLoadingDialog();
        getServiceList();
    }

    private void initList() {
        serviceListAdapter = new ServiceListAdapter(getBaseActivity(), listEntities);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv.setAdapter(serviceListAdapter);
        xrv.setNestedScrollingEnabled(false);//禁止滑动
        xrv.setPullRefreshEnabled(false);
        xrv.setLoadingMoreEnabled(true);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //do nothing
            }

            @Override
            public void onLoadMore() {
                getServiceList();
            }
        });
        serviceListAdapter.setOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("fsid", listEntities.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getBaseActivity(), ServiceDetailActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getServiceList() {
        if (serviceListPresneter != null) {
            serviceListPresneter.getServiceList(getBaseActivity(), count);
        }
    }


    @Override
    public void onGetAdImgSuccess(AdImgResponseBean bean) {
        if (bean != null) {
            GlidImageManager.getInstance().loadImageView(getActivity(), bean.getImg(), imgAd, R.mipmap.default_img);
        }
    }

    @Override
    public void onGetAdImgFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onServiceListSuccess(List<ServiceEntity> serviceListEntities) {
        if (serviceListEntities != null) {
            xrv.loadMoreComplete();
            if (listEntities != null && listEntities.size() == 0){
                listEntities.clear();
                ids.clear();
            }
            for(int i=0;i<serviceListEntities.size();i++){
                if(ids.contains(serviceListEntities.get(i).getId())){
                    return;
                }else{
                    ids.add(serviceListEntities.get(i).getId());
                }
            }
            listEntities.addAll(serviceListEntities);
            count = listEntities.size();
            serviceListAdapter.notifyDataSetChanged();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        }, 500);
    }

    @Override
    public void onServiceListFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (serviceListPresneter != null) {
            serviceListPresneter.detachView();
        }
        if (adImgPresenter != null) {
            adImgPresenter.detachView();
        }
        unbinder.unbind();
    }

}
