package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.CheckUserPayResponseBean;
import com.quanminjieshui.waterchain.beans.request.CheckUserPayReqBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.CheckUserPayModel;
import com.quanminjieshui.waterchain.contract.view.CheckUserPayViewImpl;

import java.util.List;

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
            public void success(List<CheckUserPayResponseBean> beans) {
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