package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.ServiceDetailModel;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;

public class ServiceDetailPresenter extends BasePresenter<CommonViewImpl> {
    private ServiceDetailModel serviceDetailModel;

    public ServiceDetailPresenter(ServiceDetailModel serviceDetailModel) {
        this.serviceDetailModel = serviceDetailModel;
    }
    public void getServiceDetail(BaseActivity activity,int fsid){
        serviceDetailModel.getServiceDetail(activity, fsid, new CommonCallback() {
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
