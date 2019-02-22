package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CheckUserPayReqBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.CheckUserPayModel;
import com.shuzijieshui.www.waterchain.contract.view.CheckUserPayViewImpl;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public class CheckUserPayPresenter extends BasePresenter<CheckUserPayViewImpl> {

    private CheckUserPayModel checkUserPayModel;

    public CheckUserPayPresenter(){}

    public CheckUserPayPresenter(CheckUserPayModel checkUserPayModel){
        this.checkUserPayModel = checkUserPayModel;
    }

    public void checkUserPay(BaseActivity activity, CheckUserPayReqBean bean){
        if (checkUserPayModel == null){
            checkUserPayModel = new CheckUserPayModel();
        }
        checkUserPayModel.checkUserPay(activity, bean, new CheckUserPayModel.CheckUserPayCallBack() {
            @Override
            public void success(CheckUserPayResponseBean beans) {
                if (mView!=null){
                    mView.onCheckUserPaySuccess(beans);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onCheckUserPayFailed(msg);
                }

            }
        });

    }
}
