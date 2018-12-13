package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeDetailResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.TradeDetailModel;
import com.quanminjieshui.waterchain.contract.view.TradeDetailViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:27.
 */

public class TradeDetailPresenter extends BasePresenter<TradeDetailViewImpl> {

    private TradeDetailModel tradeDetailModel;

    public TradeDetailPresenter(){}

    public TradeDetailPresenter(TradeDetailModel tradeDetailModel){
        this.tradeDetailModel = tradeDetailModel;
    }

    public void getTradeDetail(BaseActivity activity,int id){
        if(tradeDetailModel == null){
            tradeDetailModel = new TradeDetailModel();
        }
        tradeDetailModel.getTradeDetail(activity, id, new TradeDetailModel.TradeDetailCallBack() {
            @Override
            public void success(TradeDetailResponseBean tradeDetailResponseBean) {
                if(mView!=null){
                    mView.onTradeDetailSuccess(tradeDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onTradeDetailFailed(msg);
                }
            }
        });
    }
}
