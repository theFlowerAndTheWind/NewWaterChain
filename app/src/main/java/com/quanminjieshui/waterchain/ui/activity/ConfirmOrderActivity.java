package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterchain.beans.request.CreateOrderReqParams;
import com.quanminjieshui.waterchain.contract.presenter.CreateOrderPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TotalPricePresenter;
import com.quanminjieshui.waterchain.contract.view.CreateOrderViewImpl;
import com.quanminjieshui.waterchain.contract.view.TotalPriceViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:确认订单
 */

public class ConfirmOrderActivity extends BaseActivity implements TotalPriceViewImpl,CreateOrderViewImpl{

    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.img)
    ImageView service_img;
    @BindView(R.id.tv_service_title)
    TextView tv_service_title;
    @BindView(R.id.tv_services_desc)
    TextView tv_serivces_desc;
    @BindView(R.id.wash_detail_tv)
    TextView wash_detail_tv;
    @BindView(R.id.wash_detail_count)
    TextView wash_detail_count;
    private String [] trade_detail = {};
    private TotalPricePresenter totalPricePresenter;
    private CreateOrderPresenter createOrderPresenter;
    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> washFatoryCageGory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar);

        totalPricePresenter = new TotalPricePresenter();
        totalPricePresenter.attachView(this);
        createOrderPresenter = new CreateOrderPresenter();
        createOrderPresenter.attachView(this);

        initView();
    }

    private void initView() {
        tvTitleCenter.setText("确认下单");
        getData();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_order);
    }

    @OnClick({R.id.img_title_left,R.id.order_detail,R.id.create_order,R.id.wash_delivery_rl,R.id.wash_demand_rl})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
            case R.id.order_detail:
                break;
            case R.id.create_order:
                CreateOrderReqParams bean = new CreateOrderReqParams();
                bean.setCity("北京");
                bean.setAddress("海淀区");
                bean.setContact_name("何望红");
                bean.setContact_tel("13718478437");
//                bean.setExpress();
                createOrderPresenter.createOrder(ConfirmOrderActivity.this,bean);
                break;
            case R.id.wash_delivery_rl://配送信息
                break;
            case R.id.wash_demand_rl://洗涤需求
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("washFatoryCageGory",washFatoryCageGory);
                intent.putExtras(bundle);
                intent.setClass(ConfirmOrderActivity.this,WashDemandActivity.class);
                startActivity(intent);
                break;

            default:break;
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    public void getData(){

        if(getIntent()==null){
            return;
        }
        switch (getIntent().getIntExtra("class",-1)){
            case 1://FactoryServiceActivity
                FactoryServiceResponseBean.WashFatoryDetail washFatoryDetail = getIntent().getParcelableExtra("WashFatoryDetail");
                if(washFatoryDetail==null)return;
                GlidImageManager.getInstance().loadImageView(this,washFatoryDetail.getImg(),service_img,R.drawable.ic_default_image);

                washFatoryCageGory = getIntent().getParcelableArrayListExtra("WashFatoryCageGory");

                tv_service_title.setText(washFatoryDetail.getS_name());
                tv_serivces_desc.setText(washFatoryDetail.getDescription());

                break;
            case 2://WashDemandActivity
                trade_detail = getIntent().getStringArrayExtra("trade_detail");
                if(!Util.isEmpty(trade_detail)){
                    String type = "",trade_detail_tv = "";
                    int pieceCount = 0,pieceItemCout = 0;
                    String [] tradeData;
                    for (String aTrade_detail : trade_detail) {
                        tradeData = aTrade_detail.split("_");
                        type = tradeData[0];
                        pieceItemCout = Integer.parseInt(tradeData[1]);
                        pieceCount += Integer.parseInt(tradeData[1]);
                        trade_detail_tv += type + "*" + pieceItemCout + "  ";
                    }
                    wash_detail_tv.setText(trade_detail_tv);
                    wash_detail_count.setText("共"+pieceCount+"件");
                }
                break;
            case 3://WashAddressActivity
                break;
            default:break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (totalPricePresenter!=null){
            totalPricePresenter.detachView();
        }
        if (createOrderPresenter!=null){
            createOrderPresenter.detachView();
        }
    }

    @Override
    public void onTotalPriceSuccess(TotalPriceResponseBean totalPriceResponseBean) {

    }

    @Override
    public void onTotalPriceFailed(String msg) {

    }

    @Override
    public void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean) {

    }

    @Override
    public void onCreateOrderFailed(String msg) {

    }
}