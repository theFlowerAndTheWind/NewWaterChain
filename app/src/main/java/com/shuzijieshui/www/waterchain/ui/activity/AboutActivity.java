package com.shuzijieshui.www.waterchain.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class AboutActivity extends BaseActivity{
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("关于我们");
        tvTitleLeft.setText("关闭");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll,R.id.img_title_left,R.id.tv_title_left})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.img_title_left:
            case R.id.left_ll:
            case R.id.tv_title_left:
                goBack(view);
                finish();
                break;


            default:
                break;
        }

    }



}
