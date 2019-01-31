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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * @ClassName: RegisterSuccessActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 11:58 AM
 */
public class RegisterSuccessActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
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
        setContentView(R.layout.activity_success);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll, R.id.btn_register_success})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_success:
                startActivity(new Intent(RegisterSuccessActivity.this, MainActivity.class));
                EventBus.getDefault().post(new SelectFragmentEvent("首页"));
                finish();
                break;
            case R.id.left_ll:
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
