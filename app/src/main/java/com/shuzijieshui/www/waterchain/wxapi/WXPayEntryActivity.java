package com.shuzijieshui.www.waterchain.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.ui.activity.ConfirmOrderActivity;
import com.shuzijieshui.www.waterchain.utils.Key;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, Key.WX_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_wxpayentry);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case 0:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("class",4);
                intent.putExtras(bundle);
                intent.setClass(WXPayEntryActivity.this,ConfirmOrderActivity.class);
                startActivity(intent);
                break;
            case -1:
                ToastUtils.showCustomToast("支付失败", 0);
                break;
            case -2:
                ToastUtils.showCustomToastMsg("取消支付", 150);
                break;
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
