package com.shuzijieshui.www.waterchain.contract.view;


import com.shuzijieshui.www.waterchain.beans.UploadFileResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

public interface UploadFileViewImpl extends IBaseViewImpl {
    void onUploadFileSuccess(UploadFileResponseBean fileResponseBean);
    void onUploadFileFailed(String msg);
}
