package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GetPayResModel;
import com.shuzijieshui.www.waterchain.contract.view.GetPayResViewImpl;

public class GetPayResPresenter extends BasePresenter<GetPayResViewImpl> {

    private GetPayResModel getPayResModel;

    public GetPayResPresenter(GetPayResModel getPayResModel) {
        this.getPayResModel = getPayResModel;
    }

    public void getPayRes(BaseActivity activity, int id) {
        if (getPayResModel == null) {
            getPayResModel = new GetPayResModel();
        }
        getPayResModel.getPayRes(activity, id, new GetPayResModel.GetPayResCallback() {
            @Override
            public void onGetPayResSucc() {
                if (mView != null) mView.onGetPayResSucc();
            }

            @Override
            public void onGetPayResFail(String msg) {
                if (mView != null) mView.onGetPayResFail(msg);
            }
        });
    }
}
