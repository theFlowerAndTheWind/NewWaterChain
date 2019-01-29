/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AuthActivity
 * Author: sxt
 * Date: 2018/12/9 8:19 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BaseBean;
import com.quanminjieshui.waterchain.beans.UploadFileResponseBean;
import com.quanminjieshui.waterchain.beans.city.CityBean;
import com.quanminjieshui.waterchain.beans.city.ProvinceBean;
import com.quanminjieshui.waterchain.beans.request.CompanyAuthReqParams;
import com.quanminjieshui.waterchain.beans.request.PersonalAuthReqParams;
import com.quanminjieshui.waterchain.contract.model.AuthModel;
import com.quanminjieshui.waterchain.contract.model.UploadFileModel;
import com.quanminjieshui.waterchain.contract.presenter.AuthPresenter;
import com.quanminjieshui.waterchain.contract.presenter.PicturePresenter;
import com.quanminjieshui.waterchain.contract.presenter.UploadFilePresenter;
import com.quanminjieshui.waterchain.contract.view.AuthViewImpl;
import com.quanminjieshui.waterchain.contract.view.PictureViewImpl;
import com.quanminjieshui.waterchain.contract.view.UploadFileViewImpl;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.ui.adapter.SpinnerAdapter;
import com.quanminjieshui.waterchain.ui.widget.popup.PicturePopupWindow;
import com.quanminjieshui.waterchain.utils.GsonUtil;
import com.quanminjieshui.waterchain.utils.PictureFileUtil;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: AuthActivity
 * @Description: 接口测试Activity
 * @Author: sxt
 * @Date: 2018/12/9 8:19 PM
 */
public class AuthActivity extends BaseActivity implements AuthViewImpl, PictureViewImpl, UploadFileViewImpl {
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @BindView(R.id.linear_choice)
    LinearLayout linear_choice;
    @BindView(R.id.btn_company)
    Button btn_company;
    @BindView(R.id.btn_personal)
    Button btn_personal;

    @BindView(R.id.frame_nationality)
    FrameLayout frame_nationality;

    @BindView(R.id.sp_nationality)
    Spinner sp_nationality;

    @BindView(R.id.linear_cities)
    LinearLayout linear_cities;
    @BindView(R.id.sp_province)
    Spinner sp_province;
    @BindView(R.id.sp_city)
    Spinner sp_city;

    @BindView(R.id.edt_company_name)
    EditText edt_company_name;
    @BindView(R.id.edt_company_license_no)
    EditText edt_company_license_no;
//    @BindView(R.id.btn_license_img)
//    Button btn_license_img;
    @BindView(R.id.ll_license_img)
    LinearLayout llLicenseImg;
    @BindView(R.id.img_add_license)
    ImageView imgAddLicense;
    @BindView(R.id.tv_license_txt)
    TextView tvLicenseTxt;
    @BindView(R.id.edt_company_boss_name)
    EditText edt_company_boss_name;
//    @BindView(R.id.relative_boss_id_img)
//    RelativeLayout relative_boss_id_img;
    @BindView(R.id.ll_boss_id_img)
    LinearLayout llBossIdImg;
//    @BindView(R.id.btn_upload_boss_id_img_a)
//    Button btn_upload_boss_id_img_a;
    @BindView(R.id.ll_upload_boss_id_img_a)
    LinearLayout llUploadBossIdImgA;
    @BindView(R.id.img_add_boss_a)
    ImageView imgAddBossA;
    @BindView(R.id.tv_boss_a_txt)
    TextView tvBossATxt;
//    @BindView(R.id.btn_upload_boss_id_img_b)
//    Button btn_upload_boss_id_img_b;
    @BindView(R.id.ll_upload_boss_id_img_b)
    LinearLayout llUploadBossIdImgB;
    @BindView(R.id.img_add_boss_b)
    ImageView imgAddBossB;
    @BindView(R.id.tv_boss_b_txt)
    TextView tvBossBTxt;
    @BindView(R.id.edt_company_boss_tel)
    EditText edt_company_boss_tel;
    @BindView(R.id.edt_company_other_name)
    EditText edt_company_other_name;
    @BindView(R.id.edt_company_other_tel)
    EditText edt_company_other_tel;

    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_id_no)
    EditText edt_id_no;
//    @BindView(R.id.relative_p_id_img)
//    RelativeLayout relative_p_id_img;
    @BindView(R.id.ll_p_id_img)
    LinearLayout llPIdImg;
//    @BindView(R.id.btn_upload_p_id_img_a)
//    Button btn_upload_p_id_img_a;
    @BindView(R.id.ll_upload_p_id_img_a)
    LinearLayout llUploadPIdImgA;
    @BindView(R.id.img_add_p_a)
    ImageView imgAddPA;
    @BindView(R.id.tv_p_a_txt)
    TextView tvPATxt;
//    @BindView(R.id.btn_upload_p_id_img_b)
//    Button btn_upload_p_id_img_b;
    @BindView(R.id.ll_upload_p_id_img_b)
    LinearLayout llUploadPIdImgB;
    @BindView(R.id.img_add_p_b)
    ImageView imgAddPB;
    @BindView(R.id.tv_p_b_txt)
    TextView tvPBTxt;
    @BindView(R.id.tv_standing_off)
    TextView tvStandingOff;

    @BindView(R.id.btn_next)
    Button btn_next;

    private PicturePopupWindow popupWindow;
    private Uri imgUri;
    private File cameraFile;
    private View[] companyViews;
    private View[] personalViews;

    private AuthPresenter authPresenter;
    private PicturePresenter picturePresenter;
    private UploadFilePresenter uploadFilePresenter;

    /**
     * 用户认证类型 true 企业   false 个人
     */
    private boolean user_type = true;
    private int view_no = 1;
    private String bossIdImgAUrl = "";//企业法人身份证正面url
    private String bossIdImgBUrl = "";//企业法人身份证反面
    private String personalIdImgAUrl = "";//个人身份证正面
    private String personalIdImgBUrl = "";//个人身份证反面
    private String licenseImgUrl = "";//营业执照扫描件
    private String nationalityName;
    private String provinceName;
    private String cityName;
    private ArrayAdapter<String> nationalityAdapter;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> nationalityStr;//从array.xml获取而来的国籍list
    private ArrayList<ProvinceBean> cities;//从json文件解析而来的provinceBean list
    private ArrayList<String> provinceStr;
    private HashMap<String, ArrayList<String>> cityMap;
    private AdapterView.OnItemSelectedListener onCityItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cityName = cityMap.get(provinceName).get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCityData();

        StatusBarUtil.setImmersionStatus(this, title_bar);

        authPresenter = new AuthPresenter(new AuthModel());
        authPresenter.attachView(this);
        picturePresenter = PicturePresenter.getInstance();
        picturePresenter.attachView(this);
        uploadFilePresenter=new UploadFilePresenter(new UploadFileModel());
        uploadFilePresenter.attachView(this);
        initViewArr();
        initView();
//        EventBus.getDefault().register(this);

    }

    private void initView() {
        tv_title_center.setText("身份认证");
    }

    private void initAdapter() {
        nationalityAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, nationalityStr);
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉风格
        sp_nationality.setAdapter(nationalityAdapter); // 将adapter 添加到spinner中
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nationalityName = nationalityStr.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        provinceAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, provinceStr);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉风格
        sp_province.setAdapter(provinceAdapter); // 将adapter 添加到spinner中
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinceName = provinceStr.get(position);
                ArrayList<String> cityStr = cityMap.get(provinceName);
                if (cityStr != null) {
                    cityAdapter = new SpinnerAdapter(AuthActivity.this, android.R.layout.simple_spinner_item, cityStr);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉风格
                    sp_city.setAdapter(cityAdapter); // 将adapter 添加到spinner中
                    sp_city.setOnItemSelectedListener(onCityItemSelectedListener);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initCityData() {

        String[] stringArray = getResources().getStringArray(R.array.nationality);
        List<String> strings = Arrays.asList(stringArray);
        nationalityStr = new ArrayList<String>(strings);

        Observable.create(new ObservableOnSubscribe<ArrayList<ProvinceBean>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<ProvinceBean>> e) throws Exception {
                ArrayList<ProvinceBean> cities = GsonUtil.getCities(GsonUtil.readFile("city.json"));
                e.onNext(cities);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<ProvinceBean>>() {
                    @Override
                    public void accept(ArrayList<ProvinceBean> provinceBeans) throws Exception {
                        cities = provinceBeans;
                        provinceStr = new ArrayList<>();
                        cityMap = new HashMap<String, ArrayList<String>>();
                        for (ProvinceBean provinceBean : cities) {
                            String p = provinceBean.getP();
                            ArrayList<CityBean> c = provinceBean.getC();
                            provinceStr.add(p);
                            ArrayList<String> cities = cityMap.get(p);
                            if (cities == null) {
                                cities = new ArrayList<String>();
                                cityMap.put(p, cities);
                            }
                            if (c == null) {
//                                LogUtils.e("tag","c为空"+p);
                                cities.add("国外");
                            } else {
//                                LogUtils.e("tag","c不为空"+c.size());
                                for (CityBean cityBean : provinceBean.getC()) {
                                    cities.add(cityBean.getN());
                                }
                            }

                        }
                        initAdapter();
                    }
                });
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_auth);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_company, R.id.btn_personal, R.id.left_ll, R.id.btn_next, R.id.tv_standing_off,
            R.id.ll_upload_boss_id_img_a, R.id.ll_upload_boss_id_img_b, R.id.ll_upload_p_id_img_a,
            R.id.ll_upload_p_id_img_b, R.id.ll_license_img})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_company:
                user_type = true;
                if (companyViews == null || personalViews == null) {
                    initViewArr();
                }
                setBtnBlueBgShape(btn_company);
                setBtnBlueBorderBgShape(btn_personal);
                setVisiable(companyViews);
                setGone(personalViews);
                break;
            case R.id.btn_personal:
                user_type = false;
                if (companyViews == null || personalViews == null) {
                    initViewArr();
                }
                setBtnBlueBgShape(btn_personal);
                setBtnBlueBorderBgShape(btn_company);
                setVisiable(personalViews);
                setGone(companyViews);
                break;
            case R.id.left_ll:
                goBack(view);
                break;

            case R.id.btn_next:
                BaseBean params;
                if (user_type) {
                    params = new CompanyAuthReqParams();
                    ((CompanyAuthReqParams) params).setProvince(provinceName);
                    ((CompanyAuthReqParams) params).setCity(cityName);
                    ((CompanyAuthReqParams) params).setCompany_name(edt_company_name.getText().toString());
                    ((CompanyAuthReqParams) params).setCompany_license_no(edt_company_license_no.getText().toString());
                    ((CompanyAuthReqParams) params).setCompany_license_img(licenseImgUrl);
                    ((CompanyAuthReqParams) params).setCompany_boss_name(edt_company_boss_name.getText().toString());
                    ((CompanyAuthReqParams) params).setId_img_a(bossIdImgAUrl);
                    ((CompanyAuthReqParams) params).setId_img_b(bossIdImgBUrl);
                    ((CompanyAuthReqParams) params).setCompany_boss_tel(edt_company_boss_tel.getText().toString());
                    ((CompanyAuthReqParams) params).setCompany_other_name(edt_company_other_name.getText().toString());
                    ((CompanyAuthReqParams) params).setCompany_other_tel(edt_company_other_tel.getText().toString());

                } else {
                    params = new PersonalAuthReqParams();
                    ((PersonalAuthReqParams) params).setNationality(nationalityName);
                    ((PersonalAuthReqParams) params).setProvince(provinceName);
                    ((PersonalAuthReqParams) params).setCity(cityName);
                    ((PersonalAuthReqParams) params).setUser_name(edt_user_name.getText().toString());
                    ((PersonalAuthReqParams) params).setId_no(edt_id_no.getText().toString());
                    ((PersonalAuthReqParams) params).setId_img_a(personalIdImgAUrl);
                    ((PersonalAuthReqParams) params).setId_img_b(personalIdImgBUrl);

                }
                authPresenter.auth(this, user_type, params);
                break;
            case R.id.tv_standing_off:
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                EventBus.getDefault().post(new SelectFragmentEvent("首页"));
                finish();
                break;
            case R.id.ll_upload_boss_id_img_a:
                view_no = PicturePresenter.VIEW_NO[1];
                onViewClicked();
                showPopupView();
                break;
            case R.id.ll_upload_boss_id_img_b:
                view_no = PicturePresenter.VIEW_NO[2];
                onViewClicked();
                showPopupView();
                break;
            case R.id.ll_upload_p_id_img_a:
                view_no = PicturePresenter.VIEW_NO[3];
                onViewClicked();
                showPopupView();
                break;
            case R.id.ll_upload_p_id_img_b:
                view_no = PicturePresenter.VIEW_NO[4];
                onViewClicked();
                showPopupView();
                break;
            case R.id.ll_license_img:
                view_no = PicturePresenter.VIEW_NO[5];
                onViewClicked();
                showPopupView();
                break;

            default:
                break;
        }

    }

    private void setBtnBlueBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_bg_shape));
        v.setTextColor(getResources().getColor(R.color.white));
    }

    private void setBtnBlueBorderBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_border_bg_shape));
        v.setTextColor(getResources().getColor(R.color.primary_blue));
    }

    private void setVisiable(View[] views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    private void setGone(View[] views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    private void initViewArr() {
        if (companyViews == null) {
            companyViews = new View[]{
                    edt_company_name,
                    edt_company_license_no,
                    llLicenseImg,
                    edt_company_boss_name,
                    llBossIdImg,
                    edt_company_boss_tel,
                    edt_company_other_name,
                    edt_company_other_tel};
        }
        if (personalViews == null) {
            personalViews = new View[]{
                    frame_nationality,
                    edt_user_name,
                    edt_id_no,
                    edt_id_no,
                    llPIdImg};
        }
    }

    @Override
    public void onEdtContentsLegal() {

    }

    @Override
    public void onEdtContentsIllegal(Map<String, Boolean> verify) {

    }

    @Override
    public void onCompanyAuthSuccess() {
        go2SuccessActivity();
    }

    @Override
    public void onCompanyAuthFailed(String msg) {
        ToastUtils.showCustomToast(msg,0);
    }

    @Override
    public void onPersonalAuthSuccess() {
        go2SuccessActivity();
    }

    @Override
    public void onPersonalAuthFailed(String msg) {
        ToastUtils.showCustomToast(msg,0);
    }

    private void go2SuccessActivity() {
        startActivity(new Intent(AuthActivity.this, RegisterSuccessActivity.class));
        finish();
    }

    //打开相册、相机获取照片
    @Override
    public void onViewClicked() {
        initPopupView();
    }


    @Override
    public void initPopupView() {
        if (popupWindow == null) {
            popupWindow = new PicturePopupWindow(AuthActivity.this);
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
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        if (view_no == PicturePresenter.VIEW_NO[1]) {
            llUploadBossIdImgA.setBackground(bitmapDrawable);
            imgAddBossA.setVisibility(View.GONE);
            tvBossATxt.setVisibility(View.GONE);
        } else if (view_no == PicturePresenter.VIEW_NO[2]) {
            llUploadBossIdImgB.setBackground(bitmapDrawable);
            imgAddBossB.setVisibility(View.GONE);
            tvBossBTxt.setVisibility(View.GONE);
        } else if (view_no == PicturePresenter.VIEW_NO[3]) {
            llUploadPIdImgA.setBackground(bitmapDrawable);
            imgAddPA.setVisibility(View.GONE);
            tvPATxt.setVisibility(View.GONE);
        } else if (view_no == PicturePresenter.VIEW_NO[4]) {
            llUploadPIdImgB.setBackground(bitmapDrawable);
            imgAddPB.setVisibility(View.GONE);
            tvPBTxt.setVisibility(View.GONE);
        } else if (view_no == PicturePresenter.VIEW_NO[5]) {
           llLicenseImg.setBackground(bitmapDrawable);
           imgAddLicense.setVisibility(View.GONE);
           tvLicenseTxt.setVisibility(View.GONE);
        }
        uploadFile();
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(AuthActivity.this).inflate(R.layout.activity_auth, null);
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
        intent.setClass(AuthActivity.this, PictureSettingActivity.class);
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
        cameraFile=tempFile;
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
            Uri uri = AuthActivity.this.getContentResolver().insert(MediaStore.Images
                    .Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            imgUri=uri;
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//            imgUri=Uri.fromFile(tempFile);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public BaseActivity getActivity() {
        return AuthActivity.this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        picturePresenter.onActivityResult(requestCode, resultCode, intent);


        switch (requestCode) {
            case PicturePresenter.REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    imgUri = Uri.fromFile(cameraFile);
                }
                break;

            case PicturePresenter.REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    imgUri = intent.getData();
                }
                break;
        }

        if (imgUri == null) {
            return;
        }
        String cropImagePath = PictureFileUtil.getRealFilePathFromUri(this, imgUri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null) {
            showView(bitMap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        picturePresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//    public void onEventMainThread(PictureEvent event) {
//        Uri uri = event.getUri();
//        if (uri == null) {
//            return;
//        }
//        String cropImagePath = PictureFileUtil.getRealFilePathFromUri(this, uri);
//        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//        if (bitMap != null) {
//            showView(bitMap);
//        }
//    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
        authPresenter.detachView();
        picturePresenter.detachView();
    }

    @Override
    public void onUploadFileSuccess(UploadFileResponseBean fileResponseBean) {
        if (fileResponseBean != null) {
            String url=fileResponseBean.getUrl();
            if (view_no == PicturePresenter.VIEW_NO[1]) {
                bossIdImgAUrl = url;
            } else if (view_no == PicturePresenter.VIEW_NO[2]) {
                bossIdImgBUrl = url;
            } else if (view_no == PicturePresenter.VIEW_NO[3]) {
                personalIdImgAUrl = url;
            } else if (view_no == PicturePresenter.VIEW_NO[4]) {
                personalIdImgBUrl = url;
            } else if (view_no == PicturePresenter.VIEW_NO[5]) {
                licenseImgUrl = url;
            }
        }
    }

    @Override
    public void onUploadFileFailed(String msg) {
        String textStr = null;
        if (view_no == PicturePresenter.VIEW_NO[1]) {
            textStr = "企业法人身份证正面照上传失败    "+msg;
        }else if (view_no == PicturePresenter.VIEW_NO[2]) {
            textStr = "企业法人身份证反面照上传失败    "+msg;
        }else if (view_no == PicturePresenter.VIEW_NO[3]) {//个人身份证正面
            textStr = "身份证正面照上传失败    "+msg;
        }else if (view_no == PicturePresenter.VIEW_NO[4]) {//个人身份证反面
            textStr = "身份证反面照上传失败    "+msg;
        } else if (view_no == PicturePresenter.VIEW_NO[5]) {//营业执照
            textStr = "营业执照上传失败    "+msg;
        }
        ToastUtils.showCustomToast(textStr, 0);
    }

    private void uploadFile() {
        if (uploadFilePresenter == null) {
            uploadFilePresenter = new UploadFilePresenter(new UploadFileModel());
            uploadFilePresenter.attachView(this);
        }
        String textStr = null;

        if (view_no == PicturePresenter.VIEW_NO[1]) {
            textStr = "企业法人身份证正面照上传失败";
        }else if (view_no == PicturePresenter.VIEW_NO[2]) {
            textStr = "企业法人身份证反面照上传失败";
        }else if (view_no == PicturePresenter.VIEW_NO[3]) {//个人身份证正面
            textStr = "身份证正面照上传失败";
        }else if (view_no == PicturePresenter.VIEW_NO[4]) {//个人身份证反面
            textStr = "身份证反面照上传失败";
        } else if (view_no == PicturePresenter.VIEW_NO[5]) {//营业执照
            textStr = "营业执照上传失败";
        }
        try {
            File file = PictureFileUtil.Uri2File(imgUri, this);
            String token = (String) SPUtil.get(this, SPUtil.TOKEN, "token");
            if (file != null) {
                uploadFilePresenter.uploadFile(this, token, file);
            } else {
                ToastUtils.showCustomToast(textStr, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showCustomToast(textStr, 0);
        }
    }
}
