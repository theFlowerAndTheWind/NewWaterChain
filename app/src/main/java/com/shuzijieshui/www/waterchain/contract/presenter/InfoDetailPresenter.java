package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.InfoDetailRespoonseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.InfoDetailModel;
import com.shuzijieshui.www.waterchain.contract.view.InfoDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public class InfoDetailPresenter extends BasePresenter<InfoDetailViewImpl> {

    private InfoDetailModel infoDetailModel;

    public InfoDetailPresenter(){}

    public InfoDetailPresenter(InfoDetailModel infoDetailModel){
        this.infoDetailModel = infoDetailModel;
    }

    public void getInfoDetail(BaseActivity activity, int id){
        if(infoDetailModel == null){
            infoDetailModel = new InfoDetailModel();
        }
        infoDetailModel.getInfoDetail(activity, id, new InfoDetailModel.InfoDetailCallBack() {
            @Override
            public void success(InfoDetailRespoonseBean infoDetailRespoonseBean) {
                if(mView!=null){
                    mView.infoDetailSuccess(infoDetailRespoonseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.infoDetailFailed(msg);
                }

            }
        });
    }
}
