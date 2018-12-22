package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.FactoryServiceModel;
import com.quanminjieshui.waterchain.contract.view.FactoryServiceViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public class FactoryServiceParsenter extends BasePresenter<FactoryServiceViewImpl> {

    private FactoryServiceModel factoryServiceModel;

    public FactoryServiceParsenter(){}

    public FactoryServiceParsenter(FactoryServiceModel factoryServiceModel){
        this.factoryServiceModel = factoryServiceModel;
    }

    public void getFactoryService(BaseActivity activity,int fsid){
        if(factoryServiceModel == null){
            factoryServiceModel = new FactoryServiceModel();
        }
        factoryServiceModel.getFactoryService(activity, fsid, new FactoryServiceModel.FactoryServiceCallBack() {
            @Override
            public void success(FactoryServiceResponseBean factoryServiceResponseBean) {
                if(mView!=null){
                    mView.onFactoryServiceSuceess(factoryServiceResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onFactoryServiceFailed(msg);
                }

            }
        });

    }
}
