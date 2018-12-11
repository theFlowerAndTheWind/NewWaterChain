package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.ActivityManager;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.ui.fragment.FindFragment;
import com.quanminjieshui.waterchain.ui.fragment.HomeFragment;
import com.quanminjieshui.waterchain.ui.fragment.PersonalFragment;
import com.quanminjieshui.waterchain.ui.fragment.TransactionFragment;
import com.quanminjieshui.waterchain.ui.fragment.WashFragment;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.ll)
    public LinearLayout ll;
    @BindView(R.id.rg_main)
    public RadioGroup rg_main;
    @BindView(R.id.rb1)
    public RadioButton rb1;
    @BindView(R.id.rb2)
    public RadioButton rb2;
    @BindView(R.id.rb3)
    public RadioButton rb3;
    @BindView(R.id.rb4)
    public RadioButton rb4;
    @BindView(R.id.rb5)
    public RadioButton rb5;

    private HomeFragment homeFragment;
    private WashFragment washFragment;
    private TransactionFragment transactionFragment;
    private PersonalFragment personalFragment;
    private FindFragment findFragment;
    private long mExitTime;
    private FragmentManager fragmentManager;

    RadioButton[] rb = new RadioButton[5];
    Drawable drawables[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化页面Ui
     */
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }



    /**
     * 网络状况改变情况下 重试刷新数据
     *
     * @param viewId
     */
    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void createFragments() {
        homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, homeFragment).commit();

    }


    private void initView() {
        createFragments();
        initRbsize();
    }


    private void initRbsize() {
        rb[0] = rb1;
        rb[1] = rb2;
        rb[2] = rb3;
        rb[3] = rb4;
        rb[4] = rb5;

        for (int i = 0; i < rb.length; i++) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            drawables = rb[i].getCompoundDrawables();
            //获取drawables，2/5表示图片要缩小的比例
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 3, drawables[1].getMinimumHeight() * 2 / 3);
            //定义一个Rect边界
            drawables[1].setBounds(r);
            //给每一个RadioButton设置图片大小
            rb[i].setCompoundDrawables(null, drawables[1], null, null);
//            rb[i].setTextSize(Util.px2sp(this,24,1334));
            AutoUtils.auto(rb[i]);
        }
        rb1.setChecked(true);
    }

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4,R.id.rb5})
    public void onClick(View view) {
        hideFragment();
        switch (view.getId()) {
            case R.id.rb1:

                if (homeFragment != null) {
                    fragmentManager.beginTransaction().show(homeFragment).commit();
                }
                break;
            case R.id.rb2:

                if (washFragment != null) {
                    fragmentManager.beginTransaction().show(washFragment).commit();
                    return;
                }
                washFragment = new WashFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, washFragment).commit();
                break;
            case R.id.rb3:

                if (transactionFragment != null) {
                    fragmentManager.beginTransaction().show(transactionFragment).commit();
                    return;
                }
                transactionFragment = new TransactionFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, transactionFragment).commit();
                break;
            case R.id.rb4:

                if (findFragment != null) {
                    fragmentManager.beginTransaction().show(findFragment).commit();
                    return;
                }
                findFragment = new FindFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, findFragment).commit();
                break;
            case R.id.rb5:
                if(personalFragment != null){
                    fragmentManager.beginTransaction().show(personalFragment).commit();
                    return;
                }
                personalFragment = new PersonalFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, personalFragment).commit();
                break;
            default:
                break;
        }
    }

    private void hideFragment() {
        if (homeFragment != null) {
            fragmentManager.beginTransaction().hide(homeFragment).commit();
        }

        if (washFragment != null) {
            fragmentManager.beginTransaction().hide(washFragment).commit();
        }

        if (transactionFragment != null) {
            fragmentManager.beginTransaction().hide(transactionFragment).commit();
        }

        if (findFragment != null) {
            fragmentManager.beginTransaction().hide(findFragment).commit();
        }

        if (personalFragment != null) {
            fragmentManager.beginTransaction().hide(personalFragment).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showCustomToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.AppExit(mContext);
        }
    }
}
