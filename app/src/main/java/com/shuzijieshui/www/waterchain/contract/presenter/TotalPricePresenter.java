package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.TotalPriceModel;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:
 */

public class TotalPricePresenter extends BasePresenter<CommonViewImpl> {

    private TotalPriceModel totalPriceModel;


    public TotalPricePresenter(TotalPriceModel totalPriceModel){
        this.totalPriceModel = totalPriceModel;
    }

//    public void getTotalPrice(BaseActivity activity, int pay_cate,  String trade_detail){
//        if (totalPriceModel == null){
//            totalPriceModel = new TotalPriceModel();
//        }
//        totalPriceModel.getTotalPrice(activity, pay_cate,  trade_detail, new TotalPriceModel.TotalPriceCallBack() {
//            @Override
//            public void success(TotalPriceResponseBean totalPriceResponseBean) {
//                if (mView!=null){
//                    mView.onTotalPriceSuccess(totalPriceResponseBean);
//                }
//            }
//
//            @Override
//            public void failed(String msg) {
//                if (mView!=null){
//                    mView.onTotalPriceFailed(msg);
//                }
//
//            }
//        });
//    }

    public void getTotalPrice(BaseActivity activity, float count,  long id){
        if (totalPriceModel == null){
            totalPriceModel = new TotalPriceModel();
        }
        totalPriceModel.getTotalPrice(activity, count,  id, new CommonCallback() {
            @Override
            public void onRequestSucc(Object o) {
                if(mView!=null){
                    mView.onRequestSucc(o);
                }
            }

            @Override
            public void onRequestFail(String msg) {
                if(mView!=null){
                    mView.onRequestFail(msg);
                }
            }
        });
    }

}
