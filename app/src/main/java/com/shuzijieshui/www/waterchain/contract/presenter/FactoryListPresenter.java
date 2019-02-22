package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.FactoryListResponse;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.FactoryListModel;
import com.shuzijieshui.www.waterchain.contract.view.FactoryListViewImpl;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public class FactoryListPresenter extends BasePresenter<FactoryListViewImpl> {

    private FactoryListModel factoryListModel;

    public FactoryListPresenter(){}

    public FactoryListPresenter(FactoryListModel factoryListModel){
        this.factoryListModel = factoryListModel;
    }

    /**
     * placeholder=0 默认值 必填
     * @param activity
     * @param count
     */
    public void getFactoryList(BaseActivity activity,int count){
        if(factoryListModel == null){
            factoryListModel = new FactoryListModel();
        }
        factoryListModel.getFactoryList(activity, count, new FactoryListModel.FactoryListCallBack() {
            @Override
            public void success(FactoryListResponse factoryListResponse) {
                if(mView!=null){
                    mView.onFactoryListSuccess(factoryListResponse);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onFactoryListFailed(msg);
                }

            }
        });
    }
}
