package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.TotalPriceResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.TotalPriceModel;
import com.shuzijieshui.www.waterchain.contract.view.TotalPriceViewImpl;

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

    public void getTotalPrice(BaseActivity activity, int pay_cate,  String trade_detail){
        if (totalPriceModel == null){
            totalPriceModel = new TotalPriceModel();
        }
        totalPriceModel.getTotalPrice(activity, pay_cate,  trade_detail, new TotalPriceModel.TotalPriceCallBack() {
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
