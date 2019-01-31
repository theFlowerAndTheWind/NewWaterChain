package com.quanminjieshui.waterchain.contract.presenter;


import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.ChangeAvatarModel;
import com.quanminjieshui.waterchain.contract.view.ChangeAvatarViewImpl;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class ChangeAvatarPresenter extends BasePresenter<ChangeAvatarViewImpl> {

    private ChangeAvatarModel changeAvatarModel;

    public ChangeAvatarPresenter(){}

    public ChangeAvatarPresenter(ChangeAvatarModel changeAvatarModel){
        this.changeAvatarModel = changeAvatarModel;
    }

    public void changeAvatar(BaseActivity activity, String url){
        if (changeAvatarModel == null){
            changeAvatarModel = new ChangeAvatarModel();
        }
        changeAvatarModel.changeAvatar(activity, url, new ChangeAvatarModel.ChangeAvatarCallBack() {
            @Override
            public void changeAvatarSucc() {
                if (mView!=null){
                    mView.changeAvatarSucc();
                }
            }

            @Override
            public void changeAvatarFail(String msg) {
                if (mView!=null){
                    mView.changeAvatarfail(msg);
                }

            }
        });
    }
}
