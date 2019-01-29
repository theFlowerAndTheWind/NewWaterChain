package com.quanminjieshui.waterchain.contract.view;


import com.quanminjieshui.waterchain.beans.UploadFileResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

public interface UploadFileViewImpl extends IBaseViewImpl {
    void onUploadFileSuccess(UploadFileResponseBean fileResponseBean);
    void onUploadFileFailed(String msg);
}
