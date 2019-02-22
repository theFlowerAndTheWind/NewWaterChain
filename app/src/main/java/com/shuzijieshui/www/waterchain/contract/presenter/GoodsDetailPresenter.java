package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GoodsDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GoodsDetailModel;
import com.shuzijieshui.www.waterchain.contract.view.GoodsDetailViewImpl;

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
