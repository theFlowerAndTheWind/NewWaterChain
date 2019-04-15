package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.city.ProvinceBean;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderExchangeReqBean;
import com.shuzijieshui.www.waterchain.utils.GsonUtil;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserGoodsInfoActivity1 extends BaseActivity {
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ll_receiver_name)
    LinearLayout llName;
    @BindView(R.id.edt_receiver_name)
    EditText edtName;
    @BindView(R.id.ll_receiver_tel)
    LinearLayout llTel;
    @BindView(R.id.edt_receiver_tel)
    EditText edtTel;
    @BindView(R.id.rl_receiver_city)
    RelativeLayout rlCity;
    @BindView(R.id.tv_receiver_city)
    TextView tvCity;
    @BindView(R.id.ll_receiver_addr)
    LinearLayout llAddr;
    @BindView(R.id.edt_receiver_addr)
    EditText edtAddr;

    private CreateOrderExchangeReqBean params = new CreateOrderExchangeReqBean();
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initJsonData();
        StatusBarUtil.setImmersionStatus(this, titleBar);
        getIntentExtra(getIntent());
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("收货信息");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_goods_info_1);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void getIntentExtra(Intent intent) {
        if (intent != null) {
            CreateOrderExchangeReqBean bean = intent.getParcelableExtra(CreateOrderExchangeReqBean.class.getSimpleName());
            if (bean != null) {
                params.setReceiver(bean.getReceiver());
                params.setTel(bean.getTel());
                params.setAddress(bean.getAddress());
                params.setProvince(bean.getProvince());
                params.setCity(bean.getCity());
                edtName.setText(params.getReceiver());
                edtTel.setText(params.getTel());
                edtAddr.setText(params.getAddress());
                tvCity.setText(new StringBuilder().append(params.getProvince()).append(params.getCity()).toString());
            }
        }
    }

    @OnClick({R.id.rl_receiver_city, R.id.btn_save, R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.rl_receiver_city:
                hideShowKeyboard();
                ShowPickerView();
                hideShowKeyboard();
                break;

            case R.id.btn_save:
                params.setReceiver(edtName.getText().toString());
                params.setTel(edtTel.getText().toString());
                params.setAddress(edtAddr.getText().toString());

                Intent intent=new Intent(this,ConfirmGoodsOrderActivity.class);
                intent.putExtra(ConfirmGoodsOrderActivity.INT_EXTRA, 2);
                intent.putExtra(CreateOrderExchangeReqBean.class.getSimpleName(), params);
                startActivity(intent);
                finish();


                break;
            case R.id.left_ll:
                goBack(view);
                break;
        }
    }

    /**
     * 省市县弹框
     */
    private void ShowPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                params.setProvince(options1Items.get(options1));
                params.setCity(options2Items.get(options1).get(options2));
                tvCity.setText(new StringBuilder().append(params.getProvince()).append(params.getCity()).toString());
            }
        }).setTitleText("城市选择")
                .setDividerColor(mContext.getResources().getColor(R.color.primary_dark_blue))
                .setTextColorCenter(mContext.getResources().getColor(R.color.primary_dark_blue)) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, null);//三级选择器
        pvOptions.show();
    }

    /**
     * 从assert文件夹中获取json数据
     */
    private void initJsonData() {

        //省市县
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

}
