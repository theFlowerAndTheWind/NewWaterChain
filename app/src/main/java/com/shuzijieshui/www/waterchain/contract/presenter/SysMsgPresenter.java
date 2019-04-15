package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.SysMsgModel;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;

public class SysMsgPresenter extends BasePresenter<CommonViewImpl> {
    private SysMsgModel sysMsgModel;

    public SysMsgPresenter(SysMsgModel sysMsgModel) {
        this.sysMsgModel = sysMsgModel;
    }

    public void getSysMsg(BaseActivity activity, int count) {
        if (sysMsgModel == null) {
            sysMsgModel = new SysMsgModel();
        }
        sysMsgModel.getAdImg(activity, count, new CommonCallback() {
            @Override
            public void onRequestSucc(Object o) {
                if (mView != null) {
                    mView.onRequestSucc(o);
                }
            }

            @Override
            public void onRequestFail(String msg) {
                if (mView != null) {
                    mView.onRequestFail(msg);
                }
            }
        });
    }
}
