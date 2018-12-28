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

import android.os.Bundle;
import android.view.View;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;

import butterknife.OnClick;

/**
 *
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_request})
    public void onClick(View v){
        HashMap<String,Object> params=new HashMap<>();
//        params.put("id",1);


//        RetrofitFactory.getInstance().createService()
//                .orderList(RequestUtil.getRequestHashBody(params,false))
//                .compose(TestActivity.this.<BaseEntity>bindToLifecycle())
//                .compose(ObservableTransformerUtils.<BaseEntity>io())
//                .subscribe(new BaseObserver(TestActivity.this) {
//
//                    @Override
//                    protected void onSuccess(Object o) throws Exception {
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//
//                    }
//                });


        RetrofitFactory.getInstance().createService()
                .orderList(RequestUtil.getRequestHashBody(null,false))
                .compose(TestActivity.this.<BaseEntity<OrderListsResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<OrderListsResponseBean>>io())
                .subscribe(new BaseObserver<OrderListsResponseBean>() {

                    /**
                     * 返回成功
                     *
                     * @param bean
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(OrderListsResponseBean bean) throws Exception {
//                        LogUtils.e("tag",bean.get(0).getFid()+"\r\n"+list.get(0).getF_name());
                        LogUtils.e("tag",bean.getLists().get(0).getFid()+"\r\n"+bean.getLists().get(0).getF_name());
//                        callBack.success(list);
                    }

                    /**
                     * 返回失败
                     *
                     * @param e
                     * @param isNetWorkError 是否是网络错误
                     * @throws Exception
                     */
                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                    }
                });


    }

}