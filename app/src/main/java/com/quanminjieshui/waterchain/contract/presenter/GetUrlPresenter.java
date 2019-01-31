package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.GetUrlResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.GetUrlModel;
import com.quanminjieshui.waterchain.contract.view.GetUrlViewImpl;

public class GetUrlPresenter extends BasePresenter<GetUrlViewImpl> {
    private GetUrlModel getUrlModel;

    public GetUrlPresenter(GetUrlModel getUrlModel) {
        this.getUrlModel = getUrlModel;
    }

    public void getUrl(BaseActivity activity, String type){
        if(getUrlModel==null){
            getUrlModel=new GetUrlModel();
        }
        getUrlModel.getUrl(activity, type, new GetUrlModel.GetUrlCallback() {
            @Override
            public void onGetUrlSucc(GetUrlResponseBean getUrlResponseBean) {
                if(mView!=null) mView.onGetUrlSucc(getUrlResponseBean);
            }

            @Override
            public void onGetUrlFail(String msg) {
                if(mView!=null)mView.onGetUrlFail(msg);
            }
        });
    }
}
