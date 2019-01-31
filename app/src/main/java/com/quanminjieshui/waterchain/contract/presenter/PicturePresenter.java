/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.presenter
 * @ClassName: PicturePresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.contract.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.PictureModel;
import com.quanminjieshui.waterchain.contract.view.PictureViewImpl;
import com.quanminjieshui.waterchain.utils.PictureFileUtil;

import java.io.File;

/**
 * @ProjectName: NewWaterIndex
 * @Package: com.quanminjieshui.waterindex.contract.presenter
 * @ClassName: PicturePresenter
 * @Description: 涉及图片选择相关控件操作的内容（头像，身份证照片等）
 * @Author: sxt
 * @CreateDate: 2018/12/24 4:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 4:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PicturePresenter extends BasePresenter<PictureViewImpl> {

    //请求相机
    public static final int REQUEST_CAMERA = 100;
    //请求相册
    public static final int REQUEST_PHOTO = 101;
    //请求访问外部存储
    public static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private File tempFile;
    /**
     * 0代表头像
     * 1企业法人身份证正面
     * 2企业法人身份证反面
     * 3个人身份证正面
     * 4个人身份证反面
     */
    public static final int[] VIEW_NO = {0, 1, 2, 3, 4,5};
    private PictureModel pictureModel;
    private static PicturePresenter picturePresenter;

    private PicturePresenter() {
    }

    public static PicturePresenter getInstance() {
        if (picturePresenter == null) {
            picturePresenter=new PicturePresenter();
        }
        return picturePresenter;
    }

    public void setModel(PictureModel model) {
        this.pictureModel = pictureModel;
    }


    public void onViewClicked() {
        if(mView!=null){
            mView.showPopupView();
        }
    }

    public void onCameraClicked() {
        //创建拍照存储的图片文件
        String dirPath = Environment.getExternalStorageDirectory().getPath() + File.separator + PictureFileUtil.PIC_DIR_NAME;
        dirPath = PictureFileUtil.checkDirPath(dirPath);
        String suffix = ".jpg";
        tempFile = new File(dirPath,PictureFileUtil.PIC_NAME_PREFIX+System.currentTimeMillis()+suffix);

        //权限判断
        int writePermission=ContextCompat.checkSelfPermission(mView.getCtx(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission=ContextCompat.checkSelfPermission(mView.getCtx(), Manifest.permission.CAMERA);
        if (writePermission != PackageManager.PERMISSION_GRANTED||cameraPermission!=PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mView.getCtx(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到调用系统相机
            mView.go2SystemCamera(tempFile, REQUEST_CAMERA);
        }

        if (mView.popupIsShowing())
            mView.dismissPopupView();
    }

    public void onPhotoClicked() {
        //权限判断
        if (ContextCompat.checkSelfPermission(mView.getCtx(), Manifest.permission
                .READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请READ_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mView.getCtx(), new String[]{Manifest
                            .permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到相册
            mView.go2SystemPhoto(REQUEST_PHOTO);
        }
        if (mView.popupIsShowing())
            mView.dismissPopupView();
    }

    public void onCancleClicked() {
        if (mView.popupIsShowing()) {
            mView.dismissPopupView();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mView != null)
                    mView.go2SystemCamera(tempFile, REQUEST_CAMERA);
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mView != null)
                    mView.go2SystemPhoto(REQUEST_PHOTO);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    mView.go2PicSettingActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = intent.getData();
                    mView.go2PicSettingActivity(uri);
                }
                break;
        }
    }

}
