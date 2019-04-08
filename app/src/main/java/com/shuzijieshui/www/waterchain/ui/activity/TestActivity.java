/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestActivity
 * Author: ccdc_android
 * Date: 2018/12/14 11:06 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigkoo.pickerview.OptionsPickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.FactoryListResponse;
import com.shuzijieshui.www.waterchain.beans.TradeLineResponseBean;
import com.shuzijieshui.www.waterchain.beans.city.ProvinceBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.GsonUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.test
 * @ClassName: TestActivity
 * @Description: 用于各接口测试
 * @Author: sxt
 * @CreateDate: 2018/12/14 11:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/14 11:06 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TestActivity extends BaseActivity {
    EditText edt;
    Button btnRequest;
    Button btnShow;

    LineChart lineChart;
    View[] views;
    private TradeLineResponseBean tradeLineResponseBean;
    private String edtcontent = "";
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
        btnShow = findViewById(R.id.btn_show);
        edt = findViewById(R.id.edt);
        lineChart = (LineChart) findViewById(R.id.lineChart);


    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);


    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_request, R.id.btn_show})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_request:
//                edtcontent = edt.getText().toString();
                HashMap<String, Object> params = new HashMap<>();
//                params.put("type", edtcontent);
//                params.put("id", "22");
//                RetrofitFactory.getInstance().createService()
//                        .getAccountDetail(RequestUtil.getRequestHashBody(params, false))
//                        .compose(TestActivity.this.<BaseEntity>bindToLifecycle())
//                        .compose(ObservableTransformerUtils.<BaseEntity>io())
//                        .subscribe(new BaseObserver(TestActivity.this) {
//
//                            @Override
//                            protected void onSuccess(Object bean) throws Exception {
//                            }
//
//                            @Override
//                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//
//                            }
//                        });

//                ShowPickerView();

//                Intent intent=new Intent(this,PaySuccessActivity.class);
//                intent.putExtra("from","WXPayEntryActivity");
//                startActivity(intent);
                break;

            case R.id.btn_show:
//                Intent intent = new Intent(TestActivity.this, WebViewActivity.class);
//                intent.putExtra("URL", url);
//                startActivity(intent);

                Intent i=new Intent(this,PaySuccessActivity.class);
                i.putExtra("from","ConfirmGoodsOrderActivity");
                startActivity(i);

                break;
        }
    }


    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

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

            }
        }).setTitleText("城市选择")
                .setDividerColor(mContext.getResources().getColor(R.color.primary_dark_blue))
                .setTextColorCenter(mContext.getResources().getColor(R.color.primary_dark_blue)) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

}