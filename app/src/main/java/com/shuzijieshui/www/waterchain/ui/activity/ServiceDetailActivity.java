package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.ServiceDetail;
import com.shuzijieshui.www.waterchain.contract.model.ServiceDetailModel;
import com.shuzijieshui.www.waterchain.contract.presenter.ServiceDetailPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.OnClick;

public class ServiceDetailActivity extends BaseActivity implements CommonViewImpl {

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.include_title_bar)
    View titleBar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private RichText richText;

    private int fsid;
    private ServiceDetail serviceDetail;
    private ServiceDetailPresenter serviceDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RichText.initCacheDir(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        getIntentExtra();
        initView();
        serviceDetailPresenter = new ServiceDetailPresenter(new ServiceDetailModel());
        serviceDetailPresenter.attachView(this);
        showLoadingDialog();
        getServiceDetail();
    }

    private void getIntentExtra() {
        fsid = getIntent().getIntExtra("fsid", 0);
    }

    private void initView() {

    }

    private void getServiceDetail() {
        if (serviceDetailPresenter == null) {
            serviceDetailPresenter = new ServiceDetailPresenter(new ServiceDetailModel());
            serviceDetailPresenter.attachView(this);
        }
        serviceDetailPresenter.getServiceDetail(this, fsid);
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_service_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        getServiceDetail();
    }


    @OnClick({R.id.left_ll, R.id.btn_add_order})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;

            case R.id.btn_add_order:
                Intent intent = new Intent(this, ConfirmOrderActivity1.class);
                intent.putExtra("ServiceDetail", serviceDetail);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestSucc(Object o) {
        if (o != null) {
            serviceDetail = (ServiceDetail) o;
            tvTitleCenter.setText(serviceDetail.getS_name());
            GlidImageManager.getInstance().loadImageView(this, serviceDetail.getImg(), img, R.mipmap.default_img);
            richText.from(serviceDetail.getIntro()).into(tvIntro);
            richText.from(serviceDetail.getContent()).into(tvContent);
        }

        dismissLoadingDialog();
    }

    @Override
    public void onRequestFail(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (richText != null) richText.clear();
        richText = null;
        if (serviceDetailPresenter != null) serviceDetailPresenter.detachView();
    }
}
