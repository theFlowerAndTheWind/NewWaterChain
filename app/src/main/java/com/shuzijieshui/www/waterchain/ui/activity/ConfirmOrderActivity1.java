package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CreateOrderRes;
import com.shuzijieshui.www.waterchain.beans.ServiceDetail;
import com.shuzijieshui.www.waterchain.beans.TotalPriceRes;
import com.shuzijieshui.www.waterchain.contract.model.CreateOrderModel;
import com.shuzijieshui.www.waterchain.contract.model.TotalPriceModel;
import com.shuzijieshui.www.waterchain.contract.presenter.CreateOrderPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.TotalPricePresenter;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.CreateOrderViewImpl;
import com.shuzijieshui.www.waterchain.ui.view.NewAlertDialog;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;
import com.zzhoujay.richtext.RichText;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOrderActivity1 extends BaseActivity implements CommonViewImpl, CreateOrderViewImpl {
    @BindView(R.id.img_title_left)
    ImageView imgTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_s_name)
    TextView tvSName;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.ll_total_input)
    LinearLayout llTotalInput;
    @BindView(R.id.edt_total_input)
    EditText edtTotalInput;
    @BindView(R.id.tv_total_input)
    TextView tvTotalInput;
    @BindView(R.id.ll_part_confirm)
    LinearLayout llPartConfirm;
    @BindView(R.id.ll_fullPayment)
    LinearLayout llFullPayment;
    @BindView(R.id.img_pay_cate_1)
    ImageView imgPayCate1;
    @BindView(R.id.ll_combinedPayment)
    LinearLayout llCombinedPayment;
    @BindView(R.id.img_pay_cate_2)
    ImageView imgPayCate2;
    @BindView(R.id.tv_t_p_result)
    TextView tvTPResult;
    @BindView(R.id.ll_input_layout)
    LinearLayout llInputLayout;
    @BindView(R.id.llUseJsl)
    LinearLayout llUseJsl;
    @BindView(R.id.edt_use_jsl)
    EditText edtUseJsl;
    @BindView(R.id.gs4)
    View graySpace4;
    @BindView(R.id.tv_deduction)
    TextView tvDeduction;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.ll_expense_deductible)
    LinearLayout llExpenseDeduction;
    @BindView(R.id.tv_expense_deductible)
    TextView tvExpenseDeductible;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;
    @BindView(R.id.ll_create_order)
    LinearLayout llCreateOrder;
    @BindView(R.id.btn_pay_rmb)
    Button btnPayRMB;
    @BindView(R.id.btn_create_order)
    Button btnCreateOrder;

    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edtBorder;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edtBorderIllegal;

    private RichText richText;

    private boolean step = false;//false对应原型中输入订单数量页面  true对应输入订单数量之后点击"下一步"后确认订单页面
    private float count = 0;//用户输入购买数量
    private int payCate = 1;//支付类型   1全额支付  2组合支付
    private float fTotalPrice;//订单总金额
    private float fUserJsl;//用户拥有水方数量
    private float fMaxUseJsl;//理论用户最多可用水方
    private float fMaxMoney;//理论用户最多可抵扣金额
    private float fScale;//水方与RMB兑换比例（1水方=fScale RMB）
    private float fTrueMaxUseJsl;//实际用户可用最多水方
    private float fUserInputJsl = 0;//用户输入本次交易使用节水量  不可超过fTrueMaxUseJsl，不可超过fUserJsl
    private String strTotalPrice = "¥ 0.00";
    private NewAlertDialog dialog;
    private ServiceDetail serviceDetail;
    private TotalPricePresenter totalPricePresenter;
    private CreateOrderPresenter createOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RichText.initCacheDir(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        totalPricePresenter = new TotalPricePresenter(new TotalPriceModel());
        totalPricePresenter.attachView(this);
        createOrderPresenter = new CreateOrderPresenter(new CreateOrderModel());
        createOrderPresenter.attachView(this);
        getIntentExtra();
        initView();


    }

    private void getIntentExtra() {
        serviceDetail = getIntent().getParcelableExtra("ServiceDetail");
    }

    private void initView() {
        tvTitleCenter.setText("确认订单");
        GlidImageManager.getInstance().loadImageView(this, serviceDetail.getImg(), img, R.mipmap.default_img);
        tvSName.setText(serviceDetail.getS_name());
        richText.from(serviceDetail.getIntro()).into(tvIntro);
        edtTotalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String input = edtTotalInput.getText().toString();
                    if (!TextUtils.isEmpty(input)) {
                        if (input.endsWith(".")) {
                            input = input + "0";
                        }
                        count = Float.valueOf(input);
                        tvTotalInput.setText(count + "吨");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showCustomToastMsg("请正确填写订单数量", 150);
                    llTotalInput.setBackground(edtBorderIllegal);
                    edtTotalInput.setText("");
                }
            }
        });
        edtUseJsl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String input = edtUseJsl.getText().toString();
                    if (!TextUtils.isEmpty(input)) {
                        if (input.endsWith(".")) {
                            input = input + "0";
                        }
                        fUserInputJsl = Float.valueOf(input);
                        if (fUserInputJsl > fUserJsl) {
                            llUseJsl.setBackground(edtBorderIllegal);
                            edtUseJsl.setText("");
                            ToastUtils.showCustomToastMsg("您个账户仅有" + fUserJsl + "水方", 150);
                            return;
                        }
                        if (fUserInputJsl > fTrueMaxUseJsl) {
                            llUseJsl.setBackground(edtBorderIllegal);
                            edtUseJsl.setText("");
                            ToastUtils.showCustomToastMsg("水方最大可抵扣订单金额的5%", 150);
                            return;
                        }
                        float fDeduction = fScale * fUserInputJsl;
                        String inputDeduction = String.format("%.2f", fDeduction);
                        tvDeduction.setText("抵¥" + inputDeduction);
                        tvExpenseDeductible.setText("-¥" + inputDeduction);
                        btnPayRMB.setText("¥" + String.format("%.2f", fTotalPrice - fDeduction));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showCustomToastMsg("水方最大可抵扣订单金额的5%", 150);
                    llUseJsl.setBackground(edtBorderIllegal);
                    edtTotalInput.setText("");
                }
            }
        });
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_order_1);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll, R.id.btn_next_step, R.id.ll_fullPayment, R.id.ll_combinedPayment,
            R.id.ll_tip, R.id.btn_create_order})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_next_step:
                step = true;
                requestTotalPrice();
                break;
            case R.id.ll_fullPayment:
                payCate = 1;
                imgPayCate1.setVisibility(View.VISIBLE);
                imgPayCate2.setVisibility(View.GONE);
                llInputLayout.setVisibility(View.GONE);
                graySpace4.setVisibility(View.GONE);
                llExpenseDeduction.setVisibility(View.GONE);
                count = 0;
                btnPayRMB.setText(strTotalPrice);
                break;
            case R.id.ll_combinedPayment:
                payCate = 2;
                imgPayCate1.setVisibility(View.GONE);
                imgPayCate2.setVisibility(View.VISIBLE);
                llInputLayout.setVisibility(View.VISIBLE);
                graySpace4.setVisibility(View.VISIBLE);
                llExpenseDeduction.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_tip:
                showDialog("水方抵扣", "水方最大可抵扣订单金额的5%");
                break;
            case R.id.btn_create_order:
                createOrder();
                break;
        }
    }

    private void requestTotalPrice() {

        if (count <= 0) {
            llTotalInput.setBackground(edtBorderIllegal);
            ToastUtils.showCustomToastMsg("请正确填写订单数量", 150);
            return;
        }
        if (totalPricePresenter == null) {
            totalPricePresenter = new TotalPricePresenter(new TotalPriceModel());
            totalPricePresenter.attachView(this);
        }
        llTotalInput.setBackground(edtBorder);
        totalPricePresenter.getTotalPrice(this, count, serviceDetail.getId());
    }

    private void createOrder() {
        if (count <= 0) {
            ToastUtils.showCustomToastMsg("请正确填写订单数量", 150);
            return;
        }
        if (fUserInputJsl <= 0) {
            llUseJsl.setBackground(edtBorderIllegal);
            edtUseJsl.setText("");
            ToastUtils.showCustomToastMsg("请正确填写使用水方数量", 150);
            return;
        }
        if (createOrderPresenter == null) {
            createOrderPresenter = new CreateOrderPresenter(new CreateOrderModel());
            createOrderPresenter.attachView(this);
        }
        llUseJsl.setBackground(edtBorder);
        createOrderPresenter.createOrder(this, serviceDetail.getId(), count, fUserInputJsl, payCate);
    }


    private void showViews() {
        llTotalInput.setVisibility(View.GONE);
        tvTotalInput.setVisibility(View.VISIBLE);
        llPartConfirm.setVisibility(View.VISIBLE);
        btnNextStep.setVisibility(View.GONE);
        llCreateOrder.setVisibility(View.VISIBLE);
    }

    private void showDialog(String title, String msg) {
        if (dialog == null) {
            dialog = new NewAlertDialog(this);
        }
        dialog.builder().setCancelable(true);
        dialog.setTitle(title)
                .setMsg(msg)
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }).show();
    }


    @Override
    public void onRequestSucc(Object o) {
        if (o != null) {
            try {
                TotalPriceRes totalPriceRes = (TotalPriceRes) o;
                fTotalPrice = Float.valueOf(totalPriceRes.getTotal_price());
                fUserJsl = Float.valueOf(totalPriceRes.getUser_jsl());
                fMaxUseJsl = Float.valueOf(totalPriceRes.getMax_use_jsl());
                fMaxMoney = Float.valueOf(totalPriceRes.getMax_money());
                fScale = Float.valueOf(totalPriceRes.getScale());
                fTrueMaxUseJsl = Float.valueOf(totalPriceRes.getTrue_max_use_jsl());
                String str = new StringBuffer("共").append(totalPriceRes.getUser_jsl())
                        .append("水方  可用")
                        .append(totalPriceRes.getMax_use_jsl())
                        .append("水方，抵¥")
                        .append(totalPriceRes.getMax_money())
                        .toString();
                tvTPResult.setText(str);
                strTotalPrice = new StringBuilder("¥").append(String.format("%.2f", fTotalPrice)).toString();
                tvTotalPrice.setText(strTotalPrice);
//                if (payCate == 1) {
                btnPayRMB.setText(strTotalPrice);
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        showViews();
    }

    @Override
    public void onRequestFail(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onCreateOrderSuccess(Object o) {
        if (o != null) {
            CreateOrderRes createOrderRes = (CreateOrderRes) o;
            Intent intent = new Intent(this, PaySuccessActivity.class);
            intent.putExtra("from", "ConfirmOrderActivity1");
            intent.putExtra("oid", createOrderRes.getOid());
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onCreateOrderFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (richText != null) {
            richText.clear();
        }
        richText = null;
        if (totalPricePresenter != null) totalPricePresenter.detachView();
        if (createOrderPresenter != null) createOrderPresenter.detachView();
    }
}
