package com.shuzijieshui.www.waterchain.contract.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

import java.io.File;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.view
 * @ClassName: PictureViewImpl
 * @Description: 涉及图片选择相关控件操作的内容（头像，身份证照片等）
 * @Author: sxt
 * @CreateDate: 2018/12/24 3:57 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/24 3:57 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface PictureViewImpl extends IBaseViewImpl {

    void onViewClicked();


    /**
     * 初始化Popupwindow
     */
    void initPopupView();

    /**
     * 显示图片
     */
    void showView(Bitmap bitmap);

    /**
     * 显示popup
     */
    void showPopupView();

    /**
     * 隐藏popup
     */
    void dismissPopupView();

    /**
     * @return popup是否显示
     */
    boolean popupIsShowing();

    /**
     * 跳转至图线裁剪页面
     *
     * @param uri 图像Uri
     */
    void go2PicSettingActivity(Uri uri);

    /**
     * 前往系统图库界面
     *
     * @param requestCode requestCode
     */
    void go2SystemPhoto(int requestCode);

    /**
     * 前往系统相机界面
     *
     * @param tempFile    相片存储文件
     * @param requestCode requestCode
     */
    void go2SystemCamera(File tempFile, int requestCode);

    Activity getCtx();

}
