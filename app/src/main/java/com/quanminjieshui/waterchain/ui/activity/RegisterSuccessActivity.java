/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegisterSuccessActivity
 * Author: sxt
 * Date: 2018/12/9 11:58 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: RegisterSuccessActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 11:58 AM
 */
public class RegisterSuccessActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.img_title_left)
    ImageView img_title_left;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("等待审核");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_register_success);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.img_title_left, R.id.btn_register_success})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_success:
                startActivity(new Intent(RegisterSuccessActivity.this,MainActivity.class));
                break;
            case R.id.img_title_left:
                goBack(view);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
