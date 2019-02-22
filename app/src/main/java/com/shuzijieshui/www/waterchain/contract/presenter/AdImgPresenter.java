package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.AdImgModel;
import com.shuzijieshui.www.waterchain.contract.view.AdImgViewImpl;

public class AdImgPresenter extends BasePresenter<AdImgViewImpl> {
    private AdImgModel adImgModel;

    public AdImgPresenter(AdImgModel adImgModel) {
        this.adImgModel = adImgModel;
    }

    public void getAdImg(BaseActivity activity,String option_name){
        if(adImgModel==null){
            adImgModel=new AdImgModel();
        }

        adImgModel.getAdImg(activity, option_name, new AdImgModel.AdImgCallback() {
            @Override
            public void onGetAdImgSuccess(AdImgResponseBean bean) {
                if(mView!=null){
                    mView.onGetAdImgSuccess(bean);
                }
            }

            @Override
            public void onGetAdImgFailed(String msg) {
                if(mView!=null){
                    mView.onGetAdImgFailed(msg);
                }
            }
        });
    }

}
