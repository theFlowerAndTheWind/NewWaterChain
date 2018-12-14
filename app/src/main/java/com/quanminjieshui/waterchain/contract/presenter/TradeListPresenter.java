package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeListResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.TradeListModel;
import com.quanminjieshui.waterchain.contract.view.TradeListViewImpl;

/**
 * Created by WanghongHe on 2018/12/13 14:18.
 */

public class TradeListPresenter extends BasePresenter<TradeListViewImpl> {

    private TradeListModel tradeListModel;

    public TradeListPresenter(){}

    public TradeListPresenter(TradeListModel tradeListModel){
        this.tradeListModel = tradeListModel;
    }

    public void getTradeList(BaseActivity activity){
        if(tradeListModel == null){
            tradeListModel.getTradeList(activity, new TradeListModel.TradeListCallBack() {
                @Override
                public void success(TradeListResponseBean tradeListResponseBean) {
                    if(mView!=null){
                        mView.onTradeListSuccess(tradeListResponseBean);
                    }
                }

                @Override
                public void failed(String msg) {
                    if (mView!=null){
                        mView.onTradeListFailed(msg);
                    }
                }
            });
        }
    }
}
