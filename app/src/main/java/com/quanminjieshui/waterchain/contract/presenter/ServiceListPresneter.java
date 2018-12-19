package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.ServiceListResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.ServiceListModel;
import com.quanminjieshui.waterchain.contract.view.ServiceListViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListPresneter extends BasePresenter<ServiceListViewImpl> {

    private ServiceListModel serviceListModel;

    public ServiceListPresneter(){}

    public ServiceListPresneter(ServiceListModel serviceListModel){
        this.serviceListModel = serviceListModel;
    }

    public void getServiceList(BaseActivity activity){
        if(serviceListModel == null){
            serviceListModel = new ServiceListModel();
        }
        serviceListModel.getSrviceList(activity, new ServiceListModel.ServiceListCallBack() {
            @Override
            public void success(List<ServiceListResponseBean.serviceListEntity> serviceListEntity) {
                if(mView!=null){
                    mView.onServiceListSuccess(serviceListEntity);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onServiceListFailed(msg);
                }

            }
        });
    }
}
