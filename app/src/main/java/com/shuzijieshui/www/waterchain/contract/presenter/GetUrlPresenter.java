package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GetUrlResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GetUrlModel;
import com.shuzijieshui.www.waterchain.contract.view.GetUrlViewImpl;

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
