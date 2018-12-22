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
    private boolean isFirstLaunch = true;
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

    public void jumpGuidOrSplash(){
        isFirstLaunch = (boolean) SPUtil.get(this,"isFirstLaunch",true);
        if(isFirstLaunch){
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }else{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
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
