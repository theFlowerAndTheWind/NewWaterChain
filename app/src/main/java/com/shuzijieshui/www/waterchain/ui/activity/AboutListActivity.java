package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AppUpdateResponseBean;
import com.shuzijieshui.www.waterchain.contract.presenter.AppUpdatePresenter;
import com.shuzijieshui.www.waterchain.contract.view.AppUpdateViewImpl;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class AboutListActivity extends BaseActivity implements AppUpdateViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;

    private AppUpdatePresenter appUpdatePresenter;
    private AlertChainDialog alertChainDialog;
    private boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUpdatePresenter = new AppUpdatePresenter();
        appUpdatePresenter.attachView(this);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("关于我们");
        alertChainDialog = new AlertChainDialog(this);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_about_list);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll, R.id.img_title_left, R.id.btn_about, R.id.btn_ver})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_about:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEBVIEW_ACT_TITLE, "关于我们");
                intent.putExtra(WebViewActivity.GET_URL_TYPE, "about");
                startActivity(intent);
//                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.img_title_left:
                goBack(view);
                break;
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_ver:
                appUpdatePresenter.appVersion(this, Util.versionName(AboutListActivity.this));
                showLoadingDialog();
                break;
            default:
                break;
        }

    }

    @Override
    public void onAppUpdateSuccess(Object bean) {
        dismissLoadingDialog();
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
        if (alertChainDialog != null) {
            alertChainDialog.builder().setCancelable(false);
            alertChainDialog.setTitle(isUpdate?"发现新版本":"提示消息")
                    .setMsg(isUpdate ? "有新版可供更新,为了您有更好的使用体验，请前往应用市场更新" : "当前已是最新版本")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isUpdate) {
                                update();
                            }
                        }
                    }).show();
        }
    }

    private void update() {
        //TODO 版本更新
    }

    @Override
    public void onAppUpdateFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (appUpdatePresenter != null) {
            appUpdatePresenter.detachView();
        }
    }


}
