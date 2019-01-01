package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/1/1.
 * Class Note:
 */

public class ChangeInfoActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;
    @BindView(R.id.tv_title_right)
    TextView tv_title_right;
    @BindView(R.id.change_name_et)
    EditText name_et;
    @BindView(R.id.change_phone_et)
    EditText phone_et;
    @BindView(R.id.change_address_et)
    EditText address_et;
    private String nameStr,phoneStr,addressStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);

        initView();
        initData(getIntent());
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_change_info);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll,R.id.tv_title_right})
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_ll:
                goBack(v);
                finish();
                break;
            case R.id.tv_title_right:
                if(!Util.isFastDoubleClick()){
                    switch (getIntent().getIntExtra("jumpAction", -1)) {
                        case R.id.name_rl2:
                            String name = name_et.getText().toString();
                            if(TextUtils.isEmpty(name)){
                                ToastUtils.showCustomToast("姓名不能为空");
                                return;
                            }
                            bundle.putString("name",name);
                            break;
                        case R.id.phone_rl3:
                            String phone = phone_et.getText().toString();
                            if(TextUtils.isEmpty(phone)){
                                ToastUtils.showCustomToast("手机号不能为空");
                                return;
                            }
                            if(!Util.isPhone(phone)){
                                ToastUtils.showCustomToast("请输入正确的手机号");
                                return;
                            }
                            bundle.putString("phone",phone);
                            break;
                        case R.id.address_rl5:
                            String address = address_et.getText().toString();
                            if(TextUtils.isEmpty(address)){
                                ToastUtils.showCustomToast("详细地址不能为空");
                                return;

                            }
                            bundle.putString("address",address);
                            break;
                        default:
                            break;
                    }
                    hideShowKeyboard();
                    intent.putExtras(bundle);
                    intent.setClass(ChangeInfoActivity.this,DistributionInfoActivity.class);
                    startActivity(intent);

                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initData(intent);
    }

    private void initData(Intent intent){
        if (intent != null) {
            switch (intent.getIntExtra("jumpAction", -1)) {
                case R.id.name_rl2:
                    nameStr = intent.getStringExtra("name");
                    name_et.setVisibility(View.VISIBLE);
                    name_et.setText(nameStr);
                    tv_title_center.setText("联系人姓名");
                    break;
                case R.id.phone_rl3:
                    phoneStr = intent.getStringExtra("phone");
                    phone_et.setVisibility(View.VISIBLE);
                    phone_et.setText(phoneStr);
                    tv_title_center.setText("手机号码");
                    break;
                case R.id.address_rl5:
                    addressStr = intent.getStringExtra("address");
                    address_et.setVisibility(View.VISIBLE);
                    address_et.setText(addressStr);
                    tv_title_center.setText("详细地址");
                    break;
                default:
                    break;
            }
        }
    }
    private void initView() {
        tv_title_right.setText("确定");
        tv_title_right.setVisibility(View.VISIBLE);

    }
}
