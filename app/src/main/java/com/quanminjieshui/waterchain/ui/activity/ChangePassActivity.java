package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.model.ChangePassModel;
import com.quanminjieshui.waterchain.contract.presenter.ChangePassPresenter;
import com.quanminjieshui.waterchain.contract.view.ChangePassViewImpl;
import com.quanminjieshui.waterchain.utils.SPUtil;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePassActivity extends BaseActivity implements ChangePassViewImpl {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.edt_old_pass)
    EditText edtOldPass;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;

    private ChangePassPresenter changePassPresenter;

    @OnClick({R.id.left_ll,R.id.btn_find})
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.left_ll:
                goBack(v);
                finish();
                break;

            case R.id.btn_find:
                changePassPresenter.changePass(this,(String) SPUtil.get(this,SPUtil.USER_LOGIN,""),
                        edtOldPass.getText().toString(), edtNewPass.getText().toString());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changePassPresenter=new ChangePassPresenter(new ChangePassModel());
        changePassPresenter.attachView(this);

        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("修改密码");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_change_pass);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void changePassSuccess() {
        showToast("修改成功！");
        finish();
    }

    @Override
    public void changePassFiled(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        changePassPresenter.detachView();
    }
}
