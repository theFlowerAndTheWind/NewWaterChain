package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.TotalPriceModel;
import com.quanminjieshui.waterchain.contract.view.TotalPriceViewImpl;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:
 */

public class TotalPricePresenter extends BasePresenter<TotalPriceViewImpl> {

    private TotalPriceModel totalPriceModel;

    public TotalPricePresenter(){}

    public TotalPricePresenter(TotalPriceModel totalPriceModel){
        this.totalPriceModel = totalPriceModel;
    }

    public void getTotalPrice(BaseActivity activity, int pay_cate, int uid, String[] trade_detail){
        if (totalPriceModel == null){
            totalPriceModel = new TotalPriceModel();
        }
        totalPriceModel.getTotalPrice(activity, pay_cate, uid, trade_detail, new TotalPriceModel.TotalPriceCallBack() {
            @Override
            public void success(TotalPriceResponseBean totalPriceResponseBean) {
                if (mView!=null){
                    mView.onTotalPriceSuccess(totalPriceResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onTotalPriceFailed(msg);
                }

            }
        });
    }

}
