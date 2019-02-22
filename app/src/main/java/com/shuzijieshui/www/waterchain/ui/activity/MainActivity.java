package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.ActivityManager;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AppUpdateResponseBean;
import com.shuzijieshui.www.waterchain.contract.presenter.AppUpdatePresenter;
import com.shuzijieshui.www.waterchain.contract.view.AppUpdateViewImpl;
import com.shuzijieshui.www.waterchain.event.LoginStatusChangedEvent;
import com.shuzijieshui.www.waterchain.event.SelectFragmentEvent;
import com.shuzijieshui.www.waterchain.ui.fragment.FindFragment;
import com.shuzijieshui.www.waterchain.ui.fragment.HomeFragment;
import com.shuzijieshui.www.waterchain.ui.fragment.PersonalFragment;
import com.shuzijieshui.www.waterchain.ui.fragment.TransactionFragment;
import com.shuzijieshui.www.waterchain.ui.fragment.WashFragment;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.SPUtil;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity implements AppUpdateViewImpl {

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

    private AppUpdatePresenter appUpdatePresenter;
    private boolean isUpdate = false;
    private AlertChainDialog alertChainDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        ButterKnife.bind(this);
        appUpdatePresenter = new AppUpdatePresenter();
        appUpdatePresenter.attachView(this);
        checkAppVer();
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
        alertChainDialog = new AlertChainDialog(this);
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

//        for (int i = 0; i < rb.length; i++) {
//            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
//            drawables = rb[i].getCompoundDrawables();
//            //获取drawables，2/5表示图片要缩小的比例
//            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 3, drawables[1].getMinimumHeight() * 2 / 3);
//            //定义一个Rect边界
//            drawables[1].setBounds(r);
//            //给每一个RadioButton设置图片大小
//            rb[i].setCompoundDrawables(null, drawables[1], null, null);
////            rb[i].setTextSize(Util.px2sp(this,24,1334));
//            AutoUtils.auto(rb[i]);
//        }
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

    public void hideFragment() {
        if (homeFragment != null) {
            fragmentManager.beginTransaction().hide(homeFragment).commitAllowingStateLoss();
        }

        if (washFragment != null) {
            fragmentManager.beginTransaction().hide(washFragment).commitAllowingStateLoss();
        }

        if (transactionFragment != null) {
            fragmentManager.beginTransaction().hide(transactionFragment).commitAllowingStateLoss();
        }

        if (findFragment != null) {
            fragmentManager.beginTransaction().hide(findFragment).commitAllowingStateLoss();
        }

        if (personalFragment != null) {
            fragmentManager.beginTransaction().hide(personalFragment).commitAllowingStateLoss();
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
            ToastUtils.showCustomToastMsg("再按一次退出程序", 150);
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
                case "交易中心":
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


    public void showHome() {
        hideFragment();
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

    public void showWash() {
        hideFragment();
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

    public void showTransaction() {
        hideFragment();
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

    public void showFind() {
        hideFragment();
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

    public void showPersonal() {
        hideFragment();
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
        if (appUpdatePresenter != null) appUpdatePresenter.detachView();
        super.onDestroy();
    }

    private void checkAppVer() {
        if (appUpdatePresenter == null) {
            appUpdatePresenter = new AppUpdatePresenter();
            appUpdatePresenter.attachView(this);
        }
        appUpdatePresenter.appVersion(this, Util.versionName(this));
    }

    @Override
    public void onAppUpdateSuccess(Object bean) {
        if (bean instanceof AppUpdateResponseBean) {
            String version = ((AppUpdateResponseBean) bean).getVer();
            if (TextUtils.isEmpty(version)) {
                isUpdate = false;
                return;
            } else {
                String[] versionService = version.split(".");
                String[] versionLocal = Util.versionName(this).split(".");
                int service = Integer.parseInt(versionService[0] + versionService[1] + versionService[2]);
                int local = Integer.parseInt(versionLocal[0] + versionLocal[1] + versionLocal[2]);
                if (TextUtils.isEmpty(version) && service > local) {
                    isUpdate = true;
                } else {
                    isUpdate = false;
                }
            }
        }
        if (isUpdate) {
            if (alertChainDialog != null) {
                alertChainDialog.builder().setCancelable(false);
                alertChainDialog.setTitle("提示消息")
                        .setMsg(isUpdate ? "有新版可供更新" : "当前已是最新版本")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (isUpdate) {
                                    update();
                                }
                            }


                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        }
    }


    @Override
    public void onAppUpdateFailed(String msg) {
        ToastUtils.showCustomToast(msg, 1);
    }

    private void update() {
        //TODO 版本更新
    }

}