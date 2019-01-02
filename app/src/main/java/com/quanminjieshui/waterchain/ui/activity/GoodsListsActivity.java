/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GoodsListsActivity
 * Author: sxt
 * Date: 2019/1/2 9:18 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: GoodsListsActivity
 * @Description: 兑换商城
 * @Author: sxt
 * @CreateDate: 2019/1/2 9:18 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 9:18 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsListsActivity extends BaseActivity {
    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_detail)
    TextView tvDetadil;
    @BindView(R.id.relative_hint)
    RelativeLayout rlHint;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("兑换商城");
        rlHint.setVisibility(View.VISIBLE);
        tvDetadil.setText("敬请期待！");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                EventBus.getDefault().post(new SelectFragmentEvent("发现"));
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}