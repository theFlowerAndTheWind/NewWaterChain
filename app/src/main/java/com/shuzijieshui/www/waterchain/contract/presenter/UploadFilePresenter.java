package com.shuzijieshui.www.waterchain.contract.presenter;


import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.UploadFileResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.UploadFileModel;
import com.shuzijieshui.www.waterchain.contract.view.UploadFileViewImpl;

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
