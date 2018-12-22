package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 */
public class UserMessageActivity extends BaseActivity{
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.rc_message_list)
    XRecyclerView messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("系统消息");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_user_message);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll,R.id.img_title_left})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.img_title_left:
                goBack(view);
                break;
            case R.id.left_ll:
                goBack(view);
                break;

            default:
                break;
        }

    }



}
