package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.FactoryDetailResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.FactoryDetailModel;
import com.quanminjieshui.waterchain.contract.view.FactoryDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:
 */

public class FactoryDetailPresenter extends BasePresenter<FactoryDetailViewImpl> {

    private FactoryDetailModel factoryDetailModel;

    public FactoryDetailPresenter(){}

    public FactoryDetailPresenter(FactoryDetailModel factoryDetailModel){
        this.factoryDetailModel = factoryDetailModel;
    }

    public void getFactoryDetail(BaseActivity activity,int id){
        if(factoryDetailModel == null){
            factoryDetailModel = new FactoryDetailModel();
        }
        factoryDetailModel.getFactoryDetail(activity, id, new FactoryDetailModel.FactoryDetailCallBack() {
            @Override
            public void success(FactoryDetailResponseBean factoryDetailResponseBean) {
                if (mView!=null){
                    mView.onFactoryDetailSuccess(factoryDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onFactoryDetailFailed(msg);
                }

            }
        });
    }
}
