package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.ActivityManager;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.event.LoginStatusChangedEvent;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.ui.fragment.FindFragment;
import com.quanminjieshui.waterchain.ui.fragment.HomeFragment;
import com.quanminjieshui.waterchain.ui.fragment.PersonalFragment;
import com.quanminjieshui.waterchain.ui.fragment.TransactionFragment;
import com.quanminjieshui.waterchain.ui.fragment.WashFragment;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.img_title_center)
    ImageView img_title_center;
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
        StatusBarUtil.setImmersionStatus(this, titleBar);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

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
        left_ll.setVisibility(View.GONE);
        tv_title_center.setVisibility(View.GONE);
        img_title_center.setImageResource(R.mipmap.logo);
        img_title_center.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5})
    public void onClick(View view) {
        hideFragment();
        switch (view.getId()) {
            case R.id.rb1:
                tv_title_center.setText("首页");
                //by sxt
                left_ll.setVisibility(View.GONE);
                tv_title_center.setVisibility(View.GONE);
                img_title_center.setVisibility(View.VISIBLE);
                if (homeFragment != null) {
                    fragmentManager.beginTransaction().show(homeFragment).commit();

                }

                break;
            case R.id.rb2:
                tv_title_center.setText("洗涤");
                //by sxt
                tv_title_center.setVisibility(View.VISIBLE);
                img_title_center.setVisibility(View.GONE);
                if (washFragment != null) {
                    fragmentManager.beginTransaction().show(washFragment).commit();
                    return;
                }
                washFragment = new WashFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, washFragment).commit();

                break;
            case R.id.rb3:
                tv_title_center.setText("交易中心");
                //by sxt
                tv_title_center.setVisibility(View.VISIBLE);
                img_title_center.setVisibility(View.GONE);
                if (transactionFragment != null) {

                    String fragmentIsLogin = transactionFragment.getIsLogin();
                    String sp = getSp();
//                    LogUtils.e("tag",fragmentIsLogin+"******"+sp);
                    if (!fragmentIsLogin.equals(sp)) {
                        EventBus.getDefault().post(new LoginStatusChangedEvent("login_status_changed_main_transaction_reconnect"));
                    }

                    fragmentManager.beginTransaction().show(transactionFragment).commitAllowingStateLoss();//after event
                    return;
                }
                transactionFragment = new TransactionFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, transactionFragment).commit();//

                break;
            case R.id.rb4:
                tv_title_center.setText("发现");
                //by sxt
                tv_title_center.setVisibility(View.VISIBLE);
                img_title_center.setVisibility(View.GONE);
                if (findFragment != null) {
                    fragmentManager.beginTransaction().show(findFragment).commit();
                    return;
                }
                findFragment = new FindFragment();
                fragmentManager.beginTransaction().add(R.id.activity_main_ll, findFragment).commit();

                break;
            case R.id.rb5:
                tv_title_center.setText("我的");
                //by sxt
                tv_title_center.setVisibility(View.VISIBLE);
                img_title_center.setVisibility(View.GONE);
                if (personalFragment != null) {
                    String fragmentIsLogin = personalFragment.getIsLogin();
                    String sp = getSp();
                    if (!fragmentIsLogin.equals(sp)) {
                        EventBus.getDefault().post(new LoginStatusChangedEvent("login_status_changed_main_personal_refresh_nickname"));
                    }

                    fragmentManager.beginTransaction().show(personalFragment).commitAllowingStateLoss();//after event
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


    /*****************by sxt****************/
    public void onEventMainThread(SelectFragmentEvent event) {
        if (event != null) {
            String title = event.getTitle();
            switch (title) {
                case "首页":
                    showHome();
                    break;
                case "洗涤":
                    showWash();
                    break;
                case "交易":
                    showTransaction();
                    break;
                case "发现":
                    showFind();
                    break;
                case "我的":
                    showPersonal();
                    break;
                default:
                    showHome();
                    break;
            }
        }
    }


    private void showHome() {
        rb1.setChecked(true);
        tv_title_center.setText("首页");
        //by sxt
        left_ll.setVisibility(View.GONE);
        tv_title_center.setVisibility(View.GONE);
        img_title_center.setVisibility(View.VISIBLE);
        if (homeFragment != null) {
            /**
             * 概括的讲，onSaveInstanceState 这个方法会在activity 将要被kill之前被调用以保存每个实例的状态，以保证在将来的某个时刻回来时可以恢复到原来的状态，但和activity 的生命周期方法onStop 和 onPause 不一样，与两者并没有绝对的先后调用顺序，或者说并非所有场景都会调用onSaveInstanceState 方法。那么onSaveInstanceState 方法何时会被调用呢，或者这么问，什么时候activity 会被系统kill 掉呢？有以下几种比较常见的场景：
             * （1）用户主动按下home 键，系统不能确认activity 是否会被销毁，实际上此刻系统也无法预测将来的场景，比如说内存占用，应用运行情况等，所以系统会调用onSaveInstanceState保存activity状态 ；
             * （2）activity位于前台，按下电源键，直接锁屏；
             * （3）横竖屏切换；
             * （4）activity B启动后位于activity A之前，在某个时刻activity A因为系统回收资源的问题要被kill掉，A通过onSaveInstanceState保存状态。
             *
             * 那么，为什么会抛出异常呢？原因在于我们的activity在某种场景下处于被kill 掉的边缘，系统就调用了onSaveInstanceState 方法，这个方法里面会调用 FragmentManager saveAllState 方法，将fragment 的状态保存，在状态保存后用户又主动调了 onBackPressed ，而这个方法的超类super.onBackPressed 方法会判断FragmentManager 是否保存了状态，如果已经保存就会抛出IllegalStateException 的异常 。
             * ---------------------
             * 作者：EdisonChang
             * 来源：CSDN
             * 原文：https://blog.csdn.net/edisonchang/article/details/49873669
             * 版权声明：本文为博主原创文章，转载请附上博文链接！
             */
//            fragmentManager.beginTransaction().show(homeFragment).commit();
            fragmentManager.beginTransaction().show(homeFragment).commitAllowingStateLoss();
            return;
        }
        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, homeFragment).commitAllowingStateLoss();

    }

    private void showWash() {
        rb2.setChecked(true);
        tv_title_center.setText("洗涤");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if (washFragment != null) {
            fragmentManager.beginTransaction().show(washFragment).commitAllowingStateLoss();
            return;
        }
        washFragment = new WashFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, washFragment).commitAllowingStateLoss();


    }

    private void showTransaction() {
        rb3.setChecked(true);
        tv_title_center.setText("交易中心");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if (transactionFragment != null) {

            String fragmentIsLogin = transactionFragment.getIsLogin();
            String sp = getSp();
            if (!fragmentIsLogin.equals(sp)) {
                EventBus.getDefault().post(new LoginStatusChangedEvent("login_status_changed_main_transaction_reconnect"));
            }

            fragmentManager.beginTransaction().show(transactionFragment).commitAllowingStateLoss();//after event
            return;
        }
        transactionFragment = new TransactionFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, transactionFragment).commitAllowingStateLoss();
    }

    private void showFind() {
        rb4.setChecked(true);
        tv_title_center.setText("发现");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if (findFragment != null) {
            fragmentManager.beginTransaction().show(findFragment).commitAllowingStateLoss();
            return;
        }
        findFragment = new FindFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, findFragment).commitAllowingStateLoss();


    }

    private void showPersonal() {
        rb5.setChecked(true);
        tv_title_center.setText("我的");
        //by sxt
        tv_title_center.setVisibility(View.VISIBLE);
        img_title_center.setVisibility(View.GONE);
        if (personalFragment != null) {
            String fragmentIsLogin = personalFragment.getIsLogin();
            String sp = getSp();
            if (!fragmentIsLogin.equals(sp)) {
                EventBus.getDefault().post(new LoginStatusChangedEvent("login_status_changed_main_personal_refresh_nickname"));
            }

            fragmentManager.beginTransaction().show(personalFragment).commitAllowingStateLoss();//after event
            return;
        }
        personalFragment = new PersonalFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main_ll, personalFragment).commitAllowingStateLoss();
    }

    private String getSp() {
        boolean spIsLogin = (boolean) SPUtil.get(this, SPUtil.IS_LOGIN, false);
        String spUser_login = (String) SPUtil.get(this, SPUtil.USER_LOGIN, "token");
        return new StringBuilder().append(spIsLogin).append(spUser_login).toString();
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}