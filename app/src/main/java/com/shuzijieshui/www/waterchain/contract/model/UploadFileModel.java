package com.shuzijieshui.www.waterchain.contract.model;


import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.UploadFileResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadFileModel {

    public void uploadFile(BaseActivity activity, String token, File file, final UploadFileCallback callback){

        RequestBody t = RequestBody.create(null, token);

        RequestBody d_t = RequestBody.create(null, "android");


        RequestBody fileBody = null;
        String fileName = file.getName();
        if(fileName.contains(".png")||fileName.contains(".PNG")){
            fileBody = RequestBody.create(MediaType.parse("image/png"), file);
//            fileName += ".png";
        }else if (fileName.contains(".jpg")||fileName.contains(".JPG")){
            fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
//            fileName += ".jpg";
        }else if(fileName.contains(".jpeg")||fileName.contains(".JPEG")){
            fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
//            fileName += ".jpeg";
        }
        /**
         *file部分，最终拼接成以下部分（注意“file”是后台定义好的，后台会用它作为key去查询你传的图片信息）：
         *Content-Disposition: form-data; name="file"; filename=*****.jpg
         *Content-Type: image/jpeg
         *Content-Length: 52251(图片流字节数组的长度，底层的Okhttp帮我们计算了)
         *...(文件流)
         */
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", fileName, fileBody);
        RetrofitFactory.getInstance().createService()
                .uploadFile(t,d_t,filePart)
                .compose(activity.<BaseEntity<UploadFileResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<UploadFileResponseBean>>io())
                .subscribe(new BaseObserver<UploadFileResponseBean>() {
                    @Override
                    protected void onSuccess(UploadFileResponseBean uploadFileResponseBean) throws Exception {
                        callback.success(uploadFileResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.failed(e.getMessage());
                            }
                        } else {
                            callback.failed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.failed(msg);
                    }
                });
    }

    public interface UploadFileCallback{
        void success(UploadFileResponseBean fileResponseBean);
        void failed(String msg);
    }
}
