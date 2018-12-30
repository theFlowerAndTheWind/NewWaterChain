package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/14.
 * Class Note:启屏页--广告页
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.count_down_tv)
    TextView countDown;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {

        timer = new CountDownTimer(4 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText((int) (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                judgeGuideOrMain();
            }
        }.start();
    }

    public void jumpGuidOrSplash() {
        boolean isFirstLaunch = (boolean) SPUtil.get(this, SPUtil.IS_FIRST_LAUNCH, true);

        if (isFirstLaunch) {
            jump(GuideActivity.class);
        } else {
            //强制登录
            boolean isLogin = (boolean) SPUtil.get(this, SPUtil.IS_LOGIN, false);
            if (isLogin) {
                jump(MainActivity.class);
            } else {
                jump(LoginActivity.class);
            }
//            //不强制登录
//            jump(MainActivity.class);
        }
        finish();
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(SplashActivity.this, cls));
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    public void judgeGuideOrMain() {
        //首次加载
        jumpGuidOrSplash();
    }

    @OnClick(R.id.count_down_tv)
    public void onViewClicked() {
        judgeGuideOrMain();
        timer.cancel();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
