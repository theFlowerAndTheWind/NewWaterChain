package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.ServiceListResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.ServiceListModel;
import com.shuzijieshui.www.waterchain.contract.view.ServiceListViewImpl;

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
            public void success(List<ServiceListResponseBean.ServiceListEntity> serviceListEntity) {
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
