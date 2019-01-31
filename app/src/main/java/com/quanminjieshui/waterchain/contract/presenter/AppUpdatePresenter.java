package com.quanminjieshui.waterchain.contract.presenter;


import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.AppUpdateModel;
import com.quanminjieshui.waterchain.contract.view.AppUpdateViewImpl;

/**
 * Created by songxiaotao on 2019/1/22.
 * Class Note:
 */

public class AppUpdatePresenter extends BasePresenter<AppUpdateViewImpl> {

    private AppUpdateModel appUpdateModel;

    public AppUpdatePresenter(){}

    public AppUpdatePresenter(AppUpdateModel appUpdateModel){
        this.appUpdateModel = appUpdateModel;
    }

    public void appVersion(BaseActivity activity, String ver){
        if (appUpdateModel == null){
            appUpdateModel = new AppUpdateModel();
        }
        appUpdateModel.appVersion(activity, ver, new AppUpdateModel.AppVersionCallBack() {
            @Override
            public void success(Object bean) {
                if (mView!=null){
                    mView.onAppUpdateSuccess(bean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onAppUpdateFailed(msg);
                }
            }
        });
    }
}
