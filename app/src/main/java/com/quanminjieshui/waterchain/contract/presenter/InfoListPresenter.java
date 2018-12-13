package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.InfoListResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.InfoListModel;
import com.quanminjieshui.waterchain.contract.view.InfoListViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public class InfoListPresenter extends BasePresenter<InfoListViewImpl> {

    private InfoListModel infoListModel;

    public InfoListPresenter(){}

    public InfoListPresenter(InfoListModel infoListModel){
        this.infoListModel = infoListModel;
    }

    public void getInfoList(BaseActivity activity, int count){
        if(infoListModel == null){
            infoListModel = new InfoListModel();
        }
        infoListModel.getInfoList(activity, count, new InfoListModel.InfoListCallBack() {
            @Override
            public void success(InfoListResponseBean infoListResponseBean) {
                if(mView!=null){
                    mView.infoListSuccess(infoListResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.infoListFailed(msg);
                }

            }
        });

    }
}
