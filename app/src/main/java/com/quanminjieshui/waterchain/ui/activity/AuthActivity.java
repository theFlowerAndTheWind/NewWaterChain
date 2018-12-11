/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AuthActivity
 * Author: sxt
 * Date: 2018/12/9 8:19 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: AuthActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 8:19 PM
 */
public class AuthActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.img_title_left)
    ImageView img_title_left;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @BindView(R.id.linear_choice)
    LinearLayout linear_choice;
    @BindView(R.id.btn_company)
    Button btn_company;
    @BindView(R.id.btn_personal)
    Button btn_personal;

    @BindView(R.id.frame_nationality)
    FrameLayout frame_nationality;

    @BindView(R.id.sp_nationality)
    Spinner sp_nationality;

    @BindView(R.id.linear_cities)
    LinearLayout linear_cities;
    @BindView(R.id.sp_province)
    Spinner sp_province;
    @BindView(R.id.sp_city)
    Spinner sp_city;

    @BindView(R.id.edt_company_name)
    EditText edt_company_name;
    @BindView(R.id.edt_company_license_no)
    EditText edt_company_license_no;
    @BindView(R.id.edt_company_boss_name)
    EditText edt_company_boss_name;
    @BindView(R.id.linear_boss_id_img_a)
    LinearLayout linear_boss_id_img_a;
    @BindView(R.id.edt_boss_id_img_a)
    EditText edt_boss_id_img_a;
    @BindView(R.id.btn_upload_boss_id_img_a)
    ImageButton btn_upload_boss_id_img_a;
    @BindView(R.id.linear_boss_id_img_b)
    LinearLayout linear_boss_id_img_b;
    @BindView(R.id.edt_boss_id_img_b)
    EditText edt_boss_id_img_b;
    @BindView(R.id.btn_upload_boss_id_img_b)
    ImageButton btn_upload_boss_id_img_b;
    @BindView(R.id.edt_company_boss_tel)
    EditText edt_company_boss_tel;
    @BindView(R.id.edt_company_other_name)
    EditText edt_company_other_name;
    @BindView(R.id.edt_company_other_tel)
    EditText edt_company_other_tel;

    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_id_no)
    EditText edt_id_no;
    @BindView(R.id.linear_p_id_img_a)
    LinearLayout linear_p_id_img_a;
    @BindView(R.id.edt_p_id_img_a)
    EditText edt_p_id_img_a;
    @BindView(R.id.btn_upload_p_id_img_a)
    ImageButton btn_upload_p_id_img_a;
    @BindView(R.id.linear_p_id_img_b)
    LinearLayout linear_p_id_img_b;
    @BindView(R.id.edt_p_id_img_b)
    EditText edt_p_id_img_b;
    @BindView(R.id.btn_upload_p_id_img_b)
    ImageButton btn_upload_p_id_img_b;

    @BindView(R.id.btn_next)
    Button btn_next;


    private View[] companyViews;
    private View[] personalViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
        initViewArr();
    }

    private void initView() {
        tv_title_center.setText("身份认证");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_auth);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_company, R.id.btn_personal, R.id.img_title_left,R.id.btn_next})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_company:
                if(companyViews==null||personalViews==null){
                    initViewArr();
                }
                setBtnBlueBgShape(btn_company);
                setBtnBlueBorderBgShape(btn_personal);
                setVisiable(companyViews);
                setGone(personalViews);
                break;
            case R.id.btn_personal:
                if(companyViews==null||personalViews==null){
                    initViewArr();
                }
                setBtnBlueBgShape(btn_personal);
                setBtnBlueBorderBgShape(btn_company);
                setVisiable(personalViews);
                setGone(companyViews);
                break;
            case R.id.img_title_left:
                goBack(view);
                break;

            case R.id.btn_next:
                startActivity(new Intent(AuthActivity.this,RegisterSuccessActivity.class));
                break;
            default:
                break;
        }

    }

    private void setBtnBlueBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_bg_shape));
        v.setTextColor(getResources().getColor(R.color.white));
    }

    private void setBtnBlueBorderBgShape(Button v) {
        v.setBackground(getResources().getDrawable(R.drawable.blue_border_bg_shape));
        v.setTextColor(getResources().getColor(R.color.primary_blue));
    }

    private void setVisiable(View[] views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    private void setGone(View[] views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    private void initViewArr(){
        if (companyViews == null) {
            companyViews = new View[]{
                    edt_company_name,
                    edt_company_license_no,
                    edt_company_boss_name,
                    linear_boss_id_img_a,
                    linear_boss_id_img_b,
                    edt_company_boss_tel,
                    edt_company_other_name,
                    edt_company_other_tel};
        }
        if (personalViews == null) {
            personalViews = new View[]{
                    frame_nationality,
                    edt_user_name,
                    edt_id_no,
                    edt_id_no,
                    linear_p_id_img_a,
                    linear_p_id_img_b};
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
