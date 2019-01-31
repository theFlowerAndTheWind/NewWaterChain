/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InfoDetailActivity
 * Author: sxt
 * Date: 2019/1/2 11:38 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.InfoDetailRespoonseBean;
import com.quanminjieshui.waterchain.contract.presenter.InfoDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.InfoDetailViewImpl;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: InfoDetailActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/2 11:38 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 11:38 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class InfoDetailActivity extends BaseActivity implements InfoDetailViewImpl{
    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_detail)
    TextView tvDetadil;
    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;
    @BindView(R.id.container)
    LinearLayout infoDeail;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_publishtime)
    TextView tvPublishtime;
    @BindView(R.id.divider)
    View divider;
    private InfoDetailPresenter infoDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        infoDetailPresenter = new InfoDetailPresenter();
        infoDetailPresenter.attachView(this);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("咨讯详情");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_info_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        doInfoDetailRequest();
    }

    public void doInfoDetailRequest(){
        if (infoDetailPresenter!=null && getIntent()!=null){
            infoDetailPresenter.getInfoDetail(this,getIntent().getIntExtra("id",-1));
            showLoadingDialog();
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        doInfoDetailRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void infoDetailSuccess(InfoDetailRespoonseBean infoDetailRespoonseBean) {
        if(!Util.isEmpty(infoDetailRespoonseBean)){
            rlHint.setVisibility(View.GONE);
            infoDeail.setVisibility(View.VISIBLE);
        }else{
            rlHint.setVisibility(View.VISIBLE);
            infoDeail.setVisibility(View.GONE);
            tvDetadil.setText("暂无咨询！");
        }
        GlidImageManager.getInstance().loadImageView(this, infoDetailRespoonseBean.getImg(), img, R.mipmap.default_img);
        tvTitle.setText(infoDetailRespoonseBean.getTitle());

        CharSequence charSequence;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(infoDetailRespoonseBean.getContent(),Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(infoDetailRespoonseBean.getContent());
        }
        tvContent.setText(charSequence);
        tvPublishtime.setText(infoDetailRespoonseBean.getPublishtime());
        divider.setVisibility(View.INVISIBLE);
        dismissLoadingDialog();
    }

    @Override
    public void infoDetailFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }
}