package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.GoodsDetailResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.GoodsDetailModel;
import com.quanminjieshui.waterchain.contract.view.GoodsDetailViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailViewImpl> {
    private GoodsDetailModel goodsDetailModel;

    public GoodsDetailPresenter(){}

    public GoodsDetailPresenter(GoodsDetailModel goodsDetailModel){
        this.goodsDetailModel = goodsDetailModel;
    }

    public void getGoodsDetail(BaseActivity activity,int id){
        if (goodsDetailModel==null){
            goodsDetailModel = new GoodsDetailModel();
        }
        goodsDetailModel.getGoodsDetail(activity, id, new GoodsDetailModel.GoodsDetailCallBack() {
            @Override
            public void success(GoodsDetailResponseBean beans) {
                if (mView!=null){
                    mView.onGoodsDetailSuccess(beans);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onGoodsDetailFailed(msg);
                }
            }
        });
    }
}
