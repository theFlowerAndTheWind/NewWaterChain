package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.model.BuyBackModel;
import com.shuzijieshui.www.waterchain.contract.model.Stock2JslModel;
import com.shuzijieshui.www.waterchain.contract.presenter.BuyBackPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.Stock2JslPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.Stock2JslViewImpl;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class StockActivity extends BaseActivity implements CommonViewImpl, Stock2JslViewImpl {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_txt)
    TextView tvTxt;
    @BindView(R.id.tv_stock_count)
    TextView tvStockCount;
    @BindView(R.id.edt_input)
    EditText edtInput;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edtBorder;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edtBorderIllegal;

    private int style = 0;//0回购虚拟股票  1兑换水方
    public static final String STYLE = "style";
    public static final String STOCK = "stock";
    private float fCount = 0;
    private float fStock;
    private BuyBackPresenter buyBackPresenter;
    private Stock2JslPresenter stock2JslPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        getIntentExtra();
        initView();

    }

    private void setFullScreen() {
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置对话框置顶显示
        win.setAttributes(lp);
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        style = intent.getIntExtra(STYLE, 0);
        fStock = intent.getFloatExtra(STOCK, 0);
    }

    private void initView() {
        if (style == 0) {
            tvTitle.setText("申请回购");
            tvTxt.setText("申请回购数量");
            tvTip.setVisibility(View.GONE);
            btnCommit.setText("提交申请");
        } else if (style == 1) {
            tvTitle.setText("兑换水方");
            tvTxt.setText("兑换数量");
            tvTip.setVisibility(View.VISIBLE);
            btnCommit.setText("确认兑换");
        }
        tvStockCount.setText(Util.insertComma(String.format("%.0f", fStock)) + " 虚拟股票");
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String input = edtInput.getText().toString();
                    if (!TextUtils.isEmpty(input)) {
                        if (input.endsWith(".")) {
                            input = input + "0";
                        }
                        fCount = Float.valueOf(input);
                        if (fCount > fStock) {
                            inputIllegal("输入值不可大于" + fStock);
                            return;
                        }
                        edtInput.setBackground(edtBorder);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    inputIllegal("请输入正确的数值");
                }
            }
        });
    }

    private void inputIllegal(String msg) {
        ToastUtils.showCustomToastMsg(msg, 150);
        edtInput.setBackground(edtBorderIllegal);
        edtInput.setText("");
    }

    private void buyBack() {
        if (fCount <= 0) {
            inputIllegal("请输入正确的数值");
            return;
        }
        if (buyBackPresenter == null) {
            buyBackPresenter = new BuyBackPresenter(new BuyBackModel());
            buyBackPresenter.attachView(this);
        }
        buyBackPresenter.buyBack(this, fCount);
    }

    public void stock2Jsl() {
        if (fCount <= 0) {
            inputIllegal("请输入正确的数值");
            return;
        }
        if (stock2JslPresenter == null) {
            stock2JslPresenter = new Stock2JslPresenter(new Stock2JslModel());
            stock2JslPresenter.attachView(this);
        }
        stock2JslPresenter.stock2Jsl(this, fCount);
    }

    @OnClick({R.id.img_cancle, R.id.btn_commit})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_cancle:
                finish();
                break;
            case R.id.btn_commit:
                if (style == 0) {
                    buyBack();
                } else if (style == 1) {
                    stock2Jsl();
                }
                finish();
                break;
        }
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_stock);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }


    @Override
    public void onRequestSucc(Object o) {
        ToastUtils.showCustomToast("申请成功，请耐心等待审核", 1);
    }

    @Override
    public void onRequestFail(String msg) {
        ToastUtils.showCustomToast("申请失败", 0);
    }

    @Override
    public void onStock2JslSucc(Object o) {
        ToastUtils.showCustomToast("兑换成功", 1);

    }

    @Override
    public void onStock2JslFail(String msg) {
        ToastUtils.showCustomToast("兑换失败", 0);
    }

    @Override
    public void finish() {
        super.finish();
        // 参数1：MainActivity进场动画，参数2：SecondActivity出场动画
        overridePendingTransition(0, R.anim.actionsheet_dialog_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (buyBackPresenter != null) buyBackPresenter.detachView();
        if (stock2JslPresenter != null) stock2JslPresenter.detachView();
    }
}
