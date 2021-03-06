package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.MvJslModel;
import com.shuzijieshui.www.waterchain.contract.view.MvJslViewImpl;

public class MvJslPresenter extends BasePresenter<MvJslViewImpl> {
    private MvJslModel mvJslModel;

    public MvJslPresenter(MvJslModel mvJslModel) {
        this.mvJslModel = mvJslModel;
    }

    public void mvJsl(BaseActivity activity, String user_login, String user_name, String total, String reason) {
        if (mvJslModel == null) {
            mvJslModel = new MvJslModel();
        }
        mvJslModel.mvJsl(activity, user_login, user_name, total, reason, new MvJslModel.MvJslCallback() {
            @Override
            public void success() {
                if (mView != null) {
                    mView.success();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView != null) {
                    mView.failed(msg);
                }
            }
        });
    }

}
