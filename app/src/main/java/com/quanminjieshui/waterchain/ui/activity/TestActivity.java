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
package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.beans.FactoryListResponse;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;

import java.util.HashMap;

import butterknife.OnClick;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.test
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
    private String url="";

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
                edtcontent = edt.getText().toString();
                HashMap<String, Object> params = new HashMap<>();
//                params.put("type", edtcontent);
                params.put("count", "10");
                RetrofitFactory.getInstance().createService()
                        .factoryList(RequestUtil.getRequestHashBody(params, false))
                        .compose(TestActivity.this.<BaseEntity<FactoryListResponse>>bindToLifecycle())
                        .compose(ObservableTransformerUtils.<BaseEntity<FactoryListResponse>>io())
                        .subscribe(new BaseObserver<FactoryListResponse>(TestActivity.this) {

                            @Override
                            protected void onSuccess(FactoryListResponse bean) throws Exception {
//                                url=bean.getUrl();
                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                            }
                        });


                break;

            case R.id.btn_show:
                Intent intent=new Intent(TestActivity.this,WebViewActivity.class);
                intent.putExtra("URL",url);
                startActivity(intent);

                break;
        }
    }



}