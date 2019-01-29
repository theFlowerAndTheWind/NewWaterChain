package com.quanminjieshui.waterchain.contract.presenter;


import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UploadFileResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.UploadFileModel;
import com.quanminjieshui.waterchain.contract.view.UploadFileViewImpl;

import java.io.File;

public class UploadFilePresenter extends BasePresenter<UploadFileViewImpl> {

    private UploadFileModel uploadFileModel;

    public UploadFilePresenter(){}

    public UploadFilePresenter(UploadFileModel uploadFileModel) {
        this.uploadFileModel = uploadFileModel;
    }

    public void uploadFile(BaseActivity activity, String token, File file){
        if(uploadFileModel==null){
            uploadFileModel=new UploadFileModel();
        }
        uploadFileModel.uploadFile(activity, token,file,new UploadFileModel.UploadFileCallback() {

            @Override
            public void success(UploadFileResponseBean fileResponseBean) {
                if(mView!=null){
                    mView.onUploadFileSuccess(fileResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onUploadFileFailed(msg);
                }
            }
        });
    }

}
