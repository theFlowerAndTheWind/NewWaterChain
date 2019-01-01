package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.city.ProvinceBean;
import com.quanminjieshui.waterchain.ui.view.ActionSheetDialog;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.utils.GsonUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.Util;

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
    private String deliveryType,deliveryRegion,deliveryTime,nameStr,phoneStr,addressStr;
    private TimePickerView pvCustomTime;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private AlertChainDialog alertChainDialog;
    private ActionSheetDialog actionSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initDeliveryData(intent);

    }

    private void initView() {
        tv_title_center.setText("配送信息");
        alertChainDialog = new AlertChainDialog(this);
        actionSheetDialog = new ActionSheetDialog(this);

        initJsonData();
        initCustomTimePicker();
    }

    private void initDeliveryData(Intent intent) {
        if(intent!=null){
            nameStr = intent.getStringExtra("name");
            phoneStr = intent.getStringExtra("phone");
            addressStr = intent.getStringExtra("address");
            address.setText(TextUtils.isEmpty(addressStr) ? "某个详细地址" :addressStr);
            name.setText(TextUtils.isEmpty(nameStr) ? "张三" :nameStr);
            phone.setText(TextUtils.isEmpty(phoneStr) ? "18866668888" :phoneStr);
        }
    }

    @OnClick({R.id.img_title_left,R.id.area_rl4,R.id.time_re6,R.id.deliveryTypeRl1,R.id.address_rl5,R.id.name_rl2,R.id.phone_rl3})
    public void OnClick(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.img_title_left:
                goBack(view);
                finish();
                break;
            case R.id.address_rl5:
                if (!Util.isFastDoubleClick()){
                    bundle.putInt("jumpAction",R.id.address_rl5);
                    bundle.putString("address", String.valueOf(address.getText().equals("某个详细地址") ? "" : address.getText()));
                    jumpActivity(bundle);
                }
                break;
            case R.id.name_rl2:
                if (!Util.isFastDoubleClick()){
                    bundle.putInt("jumpAction",R.id.name_rl2);
                    bundle.putString("name", String.valueOf(name.getText().equals("张三") ? "" : name.getText()));
                    jumpActivity(bundle);
                }
                break;
            case R.id.phone_rl3:
                if (!Util.isFastDoubleClick()){
                    bundle.putInt("jumpAction",R.id.phone_rl3);
                    bundle.putString("phone", String.valueOf(phone.getText().equals("18866668888") ? "" :phone.getText()));
                    jumpActivity(bundle);
                }
                break;
            case R.id.area_rl4:
                ShowPickerView();
                break;
            case R.id.time_re6:
                if(pvCustomTime!=null){
                    pvCustomTime.show();
                }
                break;
            case R.id.deliveryTypeRl1:
                if(actionSheetDialog!=null){
                    new ActionSheetDialog(DistributionInfoActivity.this)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItem("洗涤企业配送", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override public void onClick(int which) {
                                    deliveryType = "洗涤企业配送";
                                    deliveryType_tv11.setText(deliveryType);
                                }
                            })
                            .addSheetItem("自取", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override public void onClick(int which) {
                                    deliveryType = "自取";
                                    deliveryType_tv11.setText(deliveryType);
                                }
                            }).show();

                }
                break;
            default:break;
        }
    }

    private void jumpActivity(Bundle bundle){
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(DistributionInfoActivity.this,ChangeInfoActivity.class);
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
                            }else{
                                ProvinceList.add(provinces.get(i).getP());
                            }

                            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                            for (int j = 0; j < provinces.get(i).getC().size(); j++) {//遍历该省份的所有城市
                                if(Util.isEmpty(provinces.get(i).getC().get(j).getN())){//直辖市没有
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
                                    for(int k = 0;k < provinces.get(i).getC().get(j).getA().size();k++){
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
                String tx = options1Items.get(options1)+" "+
                        options2Items.get(options1).get(options2)+" "+
                        options3Items.get(options1).get(options2).get(options3);
                LogUtils.d("选择的城市列表："+tx);
                deliveryRegion = tx;
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
                LogUtils.d("选择配送时间:"+deliveryTime);
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
                .setType(new boolean[]{true, true, true,false,false,false})
                .setLabel("年", "月", "日","时","分","秒")
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