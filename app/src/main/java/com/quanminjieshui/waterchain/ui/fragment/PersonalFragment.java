package com.quanminjieshui.waterchain.ui.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;
import com.quanminjieshui.waterchain.beans.UploadFileResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.ChangeAvatarPresenter;
import com.quanminjieshui.waterchain.contract.presenter.PicturePresenter;
import com.quanminjieshui.waterchain.contract.presenter.UploadFilePresenter;
import com.quanminjieshui.waterchain.contract.view.ChangeAvatarViewImpl;
import com.quanminjieshui.waterchain.contract.view.PictureViewImpl;
import com.quanminjieshui.waterchain.contract.view.UploadFileViewImpl;
import com.quanminjieshui.waterchain.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterchain.ui.activity.AboutListActivity;
import com.quanminjieshui.waterchain.ui.activity.ChangePassActivity;
import com.quanminjieshui.waterchain.ui.activity.GoodsActivity;
import com.quanminjieshui.waterchain.ui.activity.LoginActivity;
import com.quanminjieshui.waterchain.ui.activity.OrderListsActivity;
import com.quanminjieshui.waterchain.ui.activity.PictureSettingActivity;
import com.quanminjieshui.waterchain.ui.activity.TradeListsActivity;
import com.quanminjieshui.waterchain.ui.activity.UserAssetActivity;
import com.quanminjieshui.waterchain.ui.activity.UserConfirmActivity;
import com.quanminjieshui.waterchain.ui.activity.UserDetailActivity;
import com.quanminjieshui.waterchain.ui.activity.UserMessageActivity;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.popup.PicturePopupWindow;
import com.quanminjieshui.waterchain.utils.PictureFileUtil;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by songxiaotao on 2018/12/5.
 * Class Note:我的个人中心
 */

public class PersonalFragment extends BaseFragment
        implements UploadFileViewImpl, PictureViewImpl, ChangeAvatarViewImpl {
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    private Unbinder unbinder;
    private PicturePopupWindow popupWindow;
    private Uri avatarUri;
    private String avatarUrl;
    /**
     * 是否登录
     * 记录当前是否登录，fargment切换后有登录、退出登录操作后，下次再显示时用该变量与本地SP所存结果比对，不一致时做刷新操作
     */
    private boolean isLogin = false;
    private String user_login;//用户登录手机号，作用同isLogin
    private AlertChainDialog dialog;

    private UploadFilePresenter uploadFilePresenter;
    private PicturePresenter picturePresenter;
    private ChangeAvatarPresenter changeAvatarPresenter;

    @OnClick({R.id.relative_user_detail, R.id.relative_account_detail, R.id.relative_auth_detail,
            R.id.relative_trade_lists, R.id.relative_order_lists, R.id.relative_goods,
            R.id.relative_sys_msg, R.id.relative_change_pass, R.id.relative_about_us,
            R.id.img_avatar})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.relative_user_detail:
                if (checkLoginStatus())
                    jump(UserDetailActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_account_detail:
                if (checkLoginStatus())
                    jump(UserAssetActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_auth_detail:
                if (checkLoginStatus())
                    jump(UserConfirmActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_trade_lists:
                if (checkLoginStatus())
                    jump(TradeListsActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_order_lists:
                if (checkLoginStatus())
                    jump(OrderListsActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_goods:
                if (checkLoginStatus())
                    jump(GoodsActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_sys_msg:
                if (checkLoginStatus())
                    jump(UserMessageActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_change_pass:
                if (checkLoginStatus())
                    jump(ChangePassActivity.class);
                else
                    showAlertChainDialog();
                break;
            case R.id.relative_about_us:
                jump(AboutListActivity.class);
                break;
            case R.id.img_avatar:
                if (checkLoginStatus()) {
                    onViewClicked();
                    showPopupView();
                }
                break;
            default:
                break;
        }

    }

    private boolean checkLoginStatus() {
        boolean isLogin_ = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
        return isLogin_;
    }

    private void showAlertChainDialog() {
        if (dialog == null) {
            dialog = new AlertChainDialog(getActivity());
        }
        dialog.builder().setCancelable(true);
        dialog.setTitle("提示消息")
                .setMsg("您当前未登录，请登录后继续操作")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("target", "main_personal");
                        jump(LoginActivity.class, intent);
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(getBaseActivity(), cls));
    }

    private void jump(Class<?> cls, Intent intent) {
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        uploadFilePresenter = new UploadFilePresenter();
        uploadFilePresenter.attachView(this);
        picturePresenter = PicturePresenter.getInstance();
        picturePresenter.attachView(this);
        changeAvatarPresenter = new ChangeAvatarPresenter();
        changeAvatarPresenter.attachView(this);
        initView();
        isLogin = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
        user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "user_login");
        return rootView;
    }

    private void initView() {
        String avatarUrl = (String) SPUtil.get(getActivity(), SPUtil.AVATAR, "");
        GlidImageManager.getInstance().loadCircleImg(getActivity(), avatarUrl, imgAvatar, R.mipmap.head, R.mipmap.head);
        String user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "********");
        if (TextUtils.isEmpty(user_login)) {
            user_login = "********";
        }
        tvNickname.setText(Util.hide4Phone(user_login));
        tvVersion.setText(new StringBuilder("版本号：").append(Util.getVersionName()).toString());
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initView();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        initView();
    }


    public void onEventMainThread(LoginStatusChangedEvent event) {
        if (event != null && event.getMsg().equals("login_status_changed_main_personal_refresh_nickname")) {
            isLogin = (boolean) SPUtil.get(getActivity(), SPUtil.IS_LOGIN, false);
            user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "user_login");
            String user_login = (String) SPUtil.get(getActivity(), SPUtil.USER_LOGIN, "********");
            if (TextUtils.isEmpty(user_login)) {
                user_login = "********";
            }
            tvNickname.setText(Util.hide4Phone(user_login));
            String avatarUrl = (String) SPUtil.get(getActivity(), SPUtil.AVATAR, "");
            GlidImageManager.getInstance().loadCircleImg(getActivity(), avatarUrl, imgAvatar, R.mipmap.head, R.mipmap.head);
        }
    }


    public String getIsLogin() {
        return new StringBuilder().append(isLogin).append(user_login).toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void doUploadFileRuest() {
        File file = PictureFileUtil.Uri2File(avatarUri, getActivity());
        uploadFilePresenter.uploadFile(getBaseActivity(), (String) SPUtil.get(getBaseActivity(), SPUtil.TOKEN, "token"), file);
    }

    public void doChangeAvatarRuest(String avatartUrl) {
        changeAvatarPresenter.changeAvatar(getBaseActivity(), avatarUrl);
        showLoadingDialog();
    }

    @Override
    public void onViewClicked() {
        initPopupView();
    }

    @Override
    public void initPopupView() {
        if (popupWindow == null) {
            popupWindow = new PicturePopupWindow(getActivity());
            popupWindow.setOnItemClickListener(new PicturePopupWindow.OnItemClickListener() {
                @Override
                public void onCaremaClicked() {
                    if (picturePresenter != null) {
                        picturePresenter.onCameraClicked();
                    }
                }

                @Override
                public void onPhotoClicked() {
                    if (picturePresenter != null) {
                        picturePresenter.onPhotoClicked();
                    }
                }

                @Override
                public void onCancelClicked() {
                    if (picturePresenter != null) {
                        picturePresenter.onCancleClicked();
                    }
                }
            });
        }
    }

    @Override
    public void showView(Bitmap bitmap) {
//        final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//        btnImg.setBackground(bitmapDrawable);
        GlidImageManager.getInstance().loadCircleImg(getActivity(),
                PictureFileUtil.Uri2File(avatarUri, getActivity()),
                imgAvatar,
                R.mipmap.head,
                R.mipmap.head);
        doUploadFileRuest();
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personal, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    @Override
    public void dismissPopupView() {
        popupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return popupWindow.isShowing();
    }

    @Override
    public void go2PicSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), PictureSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void go2SystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void go2SystemCamera(File tempFile, int requestCode) {
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml，下面2种方式都可以
            //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig
            // .APPLICATION_ID + "" +
            //                    ".fileProvider", tempFile);
            //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile
                    .getAbsolutePath());
            Uri uri = getActivity().getContentResolver().insert(MediaStore.Images
                    .Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            avatarUri = uri;
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            avatarUri = Uri.fromFile(tempFile);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public Activity getCtx() {
        return getActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        picturePresenter.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {

            case PicturePresenter.REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    avatarUri = intent.getData();
                }
                break;
        }

        if (avatarUri == null) {
            return;
        }
        String cropImagePath = PictureFileUtil.getRealFilePathFromUri(getActivity(), avatarUri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null) {
            showView(bitMap);
        }
    }

    @Override
    public void changeAvatarSucc() {
        dismissLoadingDialog();
        ToastUtils.showCustomToast("头像更新成功", 1);
    }

    @Override
    public void changeAvatarfail(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onUploadFileSuccess(UploadFileResponseBean fileResponseBean) {
        if (fileResponseBean != null) {
            avatarUrl = fileResponseBean.getUrl();
            doChangeAvatarRuest(avatarUrl);//使用上传图片成功以后的绝对路径
        }
    }

    @Override
    public void onUploadFileFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }
}
