package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.InfoListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.InfoListModel;
import com.shuzijieshui.www.waterchain.contract.view.InfoListViewImpl;

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
            public void success(InfoListsResponseBean infoListsResponseBean) {
                if(mView!=null){
                    mView.infoListSuccess(infoListsResponseBean);
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
