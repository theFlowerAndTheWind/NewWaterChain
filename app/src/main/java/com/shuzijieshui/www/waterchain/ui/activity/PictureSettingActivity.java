/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.activity
 * @ClassName: PictureSettingActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 9:23 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 9:23 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.ui.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.event.PictureEvent;
import com.shuzijieshui.www.waterchain.ui.widget.pictureclip.ClipViewLayout;
import com.shuzijieshui.www.waterchain.utils.RxHelper;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.activity
 * @ClassName: PictureSettingActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 9:23 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 9:23 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PictureSettingActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.img_title_left)
    ImageView imgTitlLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.cvl_rect)
    ClipViewLayout cvlRect;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.img_title_left, R.id.tv_cancel, R.id.tv_ok})
    public void onClicke(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_title_left:
                finish();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_ok:
                Observable.create(new ObservableOnSubscribe<Uri>() {
                    @Override
                    public void subscribe(ObservableEmitter<Uri> e) throws
                            Exception {
                        e.onNext(generateUri());
                        e.onComplete();
                    }
                }).compose(RxHelper.<Uri>rxSchedulerHelper())
                        .subscribe(new Consumer<Uri>() {
                            @Override
                            public void accept(Uri uri) throws Exception {
                                PictureEvent event = new PictureEvent(uri);
                                EventBus.getDefault().post(event);
                                finish();
                            }
                        });
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
        //注册EventBus
//        EventBus.getDefault().register(this);
    }

    private void initView() {
        tvTitleCenter.setText("裁剪图片");
        cvlRect.setImageSrc(getIntent().getData());
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_pic_setting);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    /**
     * 生成Uri
     */
    private Uri generateUri() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        zoomedCropBitmap = cvlRect.clip();
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "waterchain_temp" + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mSaveUri;
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
