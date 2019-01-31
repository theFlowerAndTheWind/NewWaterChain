package com.quanminjieshui.waterchain.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.AdImgResponseBean;
import com.quanminjieshui.waterchain.beans.Factory;
import com.quanminjieshui.waterchain.beans.FactoryListResponse;
import com.quanminjieshui.waterchain.contract.model.AdImgModel;
import com.quanminjieshui.waterchain.contract.presenter.AdImgPresenter;
import com.quanminjieshui.waterchain.contract.presenter.FactoryListPresenter;
import com.quanminjieshui.waterchain.contract.view.AdImgViewImpl;
import com.quanminjieshui.waterchain.contract.view.FactoryListViewImpl;
import com.quanminjieshui.waterchain.ui.activity.EnterpriseActivity;
import com.quanminjieshui.waterchain.ui.adapter.WashShopAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:洗涤
 */

public class WashFragment extends BaseFragment implements FactoryListViewImpl, AdImgViewImpl {

    @BindView(R.id.img_ad)
    ImageView imgAd;
    @BindView(R.id.factoryList)
    XRecyclerView factoryList;
    @BindView(R.id.tv_intr_detail)
    TextView tvIntrDetail;

    private FactoryListPresenter factoryListPresenter;
    private AdImgPresenter adImgPresenter;
    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private WashShopAdapter washShopAdapter;
    private List<Factory> listEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factoryListPresenter = new FactoryListPresenter();
        factoryListPresenter.attachView(this);
        adImgPresenter = new AdImgPresenter(new AdImgModel());
        adImgPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_washshop, container, false);
            ButterKnife.bind(this, rootView);
        }
        alertChainDialog = new AlertChainDialog(getBaseActivity());

        initList();
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingDialog();
        if (factoryListPresenter != null) {
            factoryListPresenter.getFactoryList(getBaseActivity(), 0);
        }
        if (adImgPresenter != null) {
            adImgPresenter.getAdImg(getBaseActivity(), "ad_fac");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        }, 800);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (factoryListPresenter != null) {
                factoryListPresenter.getFactoryList(getBaseActivity(), 0);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (factoryListPresenter != null) {
            factoryListPresenter.getFactoryList(getBaseActivity(), 0);
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if (factoryListPresenter != null) {
            factoryListPresenter.getFactoryList(getBaseActivity(), 0);
        }
    }

    private void initList() {
        washShopAdapter = new WashShopAdapter(getBaseActivity(), listEntities);
        factoryList.setArrowImageView(R.drawable.iconfont_downgrey);
        factoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        factoryList.setAdapter(washShopAdapter);

        factoryList.setLoadingMoreEnabled(false);
        factoryList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (factoryListPresenter != null) {
                    factoryListPresenter.getFactoryList(getBaseActivity(), 0);
                }
            }

            @Override
            public void onLoadMore() {
                if (factoryListPresenter != null) {
                    factoryListPresenter.getFactoryList(getBaseActivity(), 0);
                }
            }
        });
        washShopAdapter.setOnItemClickListener(new WashShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("enterpriseId", listEntities.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getBaseActivity(), EnterpriseActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onFactoryListSuccess(FactoryListResponse factoryListResponse) {
        if (factoryListResponse != null) {
            List<Factory> factoryListEntities = factoryListResponse.getLists();
            String tech_desc = factoryListResponse.getTech_desc();
            listEntities.clear();
            listEntities.addAll(factoryListEntities);
            washShopAdapter.notifyDataSetChanged();
            CharSequence charSequence;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                charSequence = Html.fromHtml(tech_desc, Html.FROM_HTML_MODE_LEGACY);
            } else {
                charSequence = Html.fromHtml(tech_desc);
            }
            tvIntrDetail.setText(charSequence);
        }
        factoryList.refreshComplete();
    }

    @Override
    public void onFactoryListFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (factoryListPresenter != null) {
            factoryListPresenter.detachView();
        }
        if (adImgPresenter != null) {
            adImgPresenter.detachView();
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
}
