package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.TradeCenterModel;
import com.quanminjieshui.waterchain.contract.view.TradeCenterViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:43.
 */

public class TradeCenterPresenter extends BasePresenter<TradeCenterViewImpl> {

    private TradeCenterModel tradeCenterModel;

    public TradeCenterPresenter(){}

    public TradeCenterPresenter(TradeCenterModel tradeCenterModel){
        this.tradeCenterModel = tradeCenterModel;
    }

    public void getTradeCenter(BaseActivity activity){
        if(tradeCenterModel == null){
            tradeCenterModel = new TradeCenterModel();
        }
        tradeCenterModel.getTradeDetail(activity, new TradeCenterModel.TradeCenterCallBack() {
            @Override
            public void success(TradeCenterResponseBean tradeCenterResponseBean) {
                if(mView!=null){
                    mView.onTradeCenterSuccess(tradeCenterResponseBean);
                }

            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onTradeCenterFailed(msg);
                }
            }
        });
    }
}
