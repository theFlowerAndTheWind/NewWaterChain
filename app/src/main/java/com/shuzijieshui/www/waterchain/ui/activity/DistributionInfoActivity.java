package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.city.ProvinceBean;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderReqParams;
import com.shuzijieshui.www.waterchain.ui.view.ActionSheetDialog;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.GsonUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by songxiaotao on 2018/12/30.
 * Class Note:配送信息
 */

public class DistributionInfoActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;
    @BindView(R.id.area_rl4)
    RelativeLayout areaRl;
    @BindView(R.id.address_tv5)
    TextView address;
    @BindView(R.id.name_tv2)
    TextView name;
    @BindView(R.id.phone_tv3)
    TextView phone;
    @BindView(R.id.deliveryTime_tv66)
    TextView deliveryTime_tv66;
    @BindView(R.id.deliveryType_tv11)
    TextView deliveryType_tv11;
    @BindView(R.id.tvRegion)
    TextView tvRegion;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String deliveryType = "洗涤企业配送", deliveryRegion, deliveryTime;
    private int deliveryTypeInt = 1;
    private int jumpAction = -1;
    private String nameStr = "", phoneStr = "", addressStr = "";
    private TimePickerView pvCustomTime;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private AlertChainDialog alertChainDialog;
    private ActionSheetDialog actionSheetDialog;
    private CreateOrderReqParams params = new CreateOrderReqParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntentExtras(intent);
    }

    private void initView() {
        tv_title_center.setText("配送信息");
        alertChainDialog = new AlertChainDialog(this);
        actionSheetDialog = new ActionSheetDialog(this);
        initIntentExtras(getIntent());
        initJsonData();
        initCustomTimePicker();
    }

    private void initIntentExtras(Intent intent) {
        if (intent != null) {
            jumpAction = intent.getIntExtra("jumpAction", -1);
            switch (jumpAction) {
                case R.id.name_rl2:
                    nameStr = intent.getStringExtra("name");
                    break;
                case R.id.phone_rl3:
                    phoneStr = intent.getStringExtra("phone");
                    break;
                case R.id.address_rl5:
                    addressStr = intent.getStringExtra("address");
                    break;
                case R.id.wash_delivery_rl:
                    CreateOrderReqParams extraParams = intent.getParcelableExtra("params");
                    if (extraParams != null) {
                        nameStr = extraParams.getContact_name();
                        phoneStr = extraParams.getContact_tel();
                        addressStr = extraParams.getAddress();
                        deliveryRegion = extraParams.getProvince() + "_" + extraParams.getCity();
                        deliveryTime = extraParams.getPickup_time();
                        int express = extraParams.getExpress();
                        if (express == 1) {
                            deliveryType = "企业配送";
                        } else if (express == 2) {
                            deliveryType = "自取";
                        }
                        //
                        params.setExpress(express);
                        params.setContact_name(nameStr);
                        params.setContact_tel(phoneStr);
                        params.setProvince(extraParams.getProvince());
                        params.setCity(extraParams.getCity());
                        params.setAddress(addressStr);
                        params.setPickup_time(deliveryTime);

                        //
//                        name.setText(nameStr);
//                        phone.setText(phoneStr);
//                        address.setText(addressStr);
                        deliveryType_tv11.setText(deliveryType);
                        if (deliveryRegion.equals("_")) deliveryRegion = "";
                        tvRegion.setText(deliveryRegion);
                        deliveryTime_tv66.setText(deliveryTime);
                    }
                    break;
            }
            name.setText(nameStr);
            phone.setText(phoneStr);
            address.setText(addressStr);
        }
    }


    private void initDeliveryData(Intent intent) {
        if (intent != null) {
            nameStr = intent.getStringExtra("name");
            phoneStr = intent.getStringExtra("phone");
            addressStr = intent.getStringExtra("address");
            address.setText(TextUtils.isEmpty(addressStr) ? "" : addressStr);
            name.setText(TextUtils.isEmpty(nameStr) ? "" : nameStr);
            phone.setText(TextUtils.isEmpty(phoneStr) ? "" : phoneStr);
        }
    }

    @OnClick({R.id.img_title_left, R.id.btn_save,
            R.id.area_rl4, R.id.time_re6, R.id.deliveryTypeRl1, R.id.address_rl5, R.id.name_rl2, R.id.phone_rl3})
    public void OnClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("from", "DistributionInfoActivity");
        switch (view.getId()) {
            case R.id.img_title_left:
                goBack(view);
                finish();
                break;
            case R.id.address_rl5:
                if (!Util.isFastDoubleClick()) {
                    bundle.putInt("jumpAction", R.id.address_rl5);
                    bundle.putString("address", String.valueOf(address.getText().equals("某个详细地址") ? "" : address.getText()));
                    jumpActivity(bundle, ChangeInfoActivity.class);
                }
                break;
            case R.id.name_rl2:
                if (!Util.isFastDoubleClick()) {
                    bundle.putInt("jumpAction", R.id.name_rl2);
                    bundle.putString("name", String.valueOf(name.getText().equals("张三") ? "" : name.getText()));
                    jumpActivity(bundle, ChangeInfoActivity.class);
                }
                break;
            case R.id.phone_rl3:
                if (!Util.isFastDoubleClick()) {
                    bundle.putInt("jumpAction", R.id.phone_rl3);
                    bundle.putString("phone", String.valueOf(phone.getText().equals("18866668888") ? "" : phone.getText()));
                    jumpActivity(bundle, ChangeInfoActivity.class);
                }
                break;
            case R.id.area_rl4:
                ShowPickerView();
                break;
            case R.id.time_re6:
                if (pvCustomTime != null) {
                    pvCustomTime.show();
                }
                break;
            case R.id.deliveryTypeRl1:
                if (actionSheetDialog != null) {
                    new ActionSheetDialog(DistributionInfoActivity.this)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItem("洗涤企业配送", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    deliveryType = "洗涤企业配送";
                                    deliveryTypeInt = 1;
                                    deliveryType_tv11.setText(deliveryType);
                                }
                            })
                            .addSheetItem("自取", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    deliveryType = "自取";
                                    deliveryTypeInt = 2;
                                    deliveryType_tv11.setText(deliveryType);
                                }
                            }).show();

                }
                break;
            case R.id.btn_save:
//                params.setExpress(deliveryTypeInt);
//                params.setContact_name(nameStr);
//                params.setContact_tel(phoneStr);
//                if (!TextUtils.isEmpty(deliveryRegion) && deliveryRegion.contains("_") && deliveryRegion.length() > 2) {
//                    String[] split = deliveryRegion.split("_");
//                    params.setProvince(split[0]);
//                    params.setCity(split[1]);
//                }
//                params.setAddress(addressStr);
//                params.setPickup_time(deliveryTime);
//                bundle.putInt("class", 3);
//                bundle.putParcelable("params", params);
//                jumpActivity(bundle, ConfirmOrderActivity.class);
//                finish();
                break;
            default:
                break;
        }
    }

    private void jumpActivity(Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(DistributionInfoActivity.this, cls);
        startActivity(intent);
    }

    /**
     * 从assert文件夹中获取json数据
     */
    private void initJsonData() {
        Observable.create(new ObservableOnSubscribe<ArrayList<ProvinceBean>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<ProvinceBean>> emitter) throws Exception {
                ArrayList<ProvinceBean> cities = GsonUtil.parseData(GsonUtil.readFile("city.json"));
                emitter.onNext(cities);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<ProvinceBean>>() {
                    @Override
                    public void accept(ArrayList<ProvinceBean> provinces) throws Exception {
                        for (int i = 0; i < provinces.size(); i++) {//遍历省份
                            ArrayList<String> ProvinceList = new ArrayList<>();//该省的城市列表（第二级）
                            if (provinces.get(i).getP().equals("国外")) {
                                return;
                            } else {
                                ProvinceList.add(provinces.get(i).getP());
                            }

                            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                            for (int j = 0; j < provinces.get(i).getC().size(); j++) {//遍历该省份的所有城市
                                if (Util.isEmpty(provinces.get(i).getC().get(j).getN())) {//直辖市没有
                                    return;
                                }
                                String CityName = provinces.get(i).getC().get(j).getN();

                                CityList.add(CityName);//添加城市
                                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                                if (provinces.get(i).getC().get(j).getA() == null
                                        || provinces.get(i).getC().get(j).getA().size() == 0) {
                                    City_AreaList.add("");
                                } else {
                                    for (int k = 0; k < provinces.get(i).getC().get(j).getA().size(); k++) {
                                        City_AreaList.add(provinces.get(i).getC().get(j).getA().get(k).getS());
                                    }
                                }
                                Province_AreaList.add(City_AreaList);//添加该省所有地区数据

                            }

                            /**
                             * 添加省份数据
                             */
                            options1Items.addAll(ProvinceList);

                            /**
                             * 添加城市数据
                             */
                            options2Items.add(CityList);

                            /**
                             * 添加地区数据
                             */
                            options3Items.add(Province_AreaList);
                        }


                    }
                });
    }

    /**
     * 省市县弹框
     */
    private void ShowPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) + "_" +
                        options2Items.get(options1).get(options2) + "_" +
                        options3Items.get(options1).get(options2).get(options3);
                LogUtils.d("选择的城市列表：" + tx);
                deliveryRegion = tx;
                if (deliveryRegion.equals("_")) deliveryRegion = "";
                tvRegion.setText(deliveryRegion);
            }
        }).setTitleText("城市选择")
                .setDividerColor(mContext.getResources().getColor(R.color.primary_dark_blue))
                .setTextColorCenter(mContext.getResources().getColor(R.color.primary_dark_blue)) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * 初始化时间选择器
     */
    private void initCustomTimePicker() {

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                deliveryTime = sf.format(date);
                deliveryTime_tv66.setText(deliveryTime);
            }
        })
                .setCancelText("取消")
                .setSubmitText("确认")
                .setContentSize(20)
                .setTitleSize(20)
                .setTitleText("配送时间")
                .setTitleColor(Color.BLACK)
                .setDividerColor(mContext.getResources().getColor(R.color.primary_dark_blue))//设置分割线的颜色
                .setTextColorCenter(mContext.getResources().getColor(R.color.primary_dark_blue))//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(mContext.getResources().getColor(R.color.divider_gray))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setSubmitColor(mContext.getResources().getColor(R.color.primary_dark_blue))
                .setCancelColor(mContext.getResources().getColor(R.color.primary_dark_blue))
                .setDate(selectedDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.6f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_distribution_info);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }
}