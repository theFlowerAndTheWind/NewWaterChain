package com.shuzijieshui.www.waterchain.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.SysMsg;
import com.shuzijieshui.www.waterchain.contract.model.SysMsgModel;
import com.shuzijieshui.www.waterchain.contract.presenter.SysMsgPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.ui.adapter.GoodsAdapter;
import com.shuzijieshui.www.waterchain.ui.adapter.SysMsgAdapter;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: fushizhe
 *
 * update by sxt on 2019/4/15
 */
public class UserMessageActivity extends BaseActivity implements CommonViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;


    private SysMsgPresenter sysMsgPresenter;
    private SysMsgAdapter sysMsgAdapter;
    private List<SysMsg> list;
    private int count = 0;
    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
        sysMsgPresenter = new SysMsgPresenter(new SysMsgModel());
        sysMsgPresenter.attachView(this);
        showLoadingDialog();
        requestMsg();
    }

    private void requestMsg() {
        if (sysMsgPresenter == null) {
            sysMsgPresenter = new SysMsgPresenter(new SysMsgModel());
            sysMsgPresenter.attachView(this);
        }
        sysMsgPresenter.getSysMsg(this, count);
    }


    private void initView() {
        tv_title_center.setText("系统消息");
        list = new ArrayList<>();
        sysMsgAdapter = new SysMsgAdapter(this, list);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setAdapter(sysMsgAdapter);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                count = 0;
                requestMsg();
            }

            @Override
            public void onLoadMore() {
                requestMsg();
            }
        });
    }


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        requestMsg();
    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.left_ll:
                goBack(view);
                break;

            default:
                break;
        }

    }


    @Override
    public void onRequestSucc(Object o) {
        xrv.refreshComplete();
        xrv.loadMoreComplete();
        if (o != null) {
            List<SysMsg> results = (List<SysMsg>) o;
            if (results != null) {
                if (results.size() == 0) {
                    if (count == 0) {
                        relativeHint.setVisibility(View.VISIBLE);
                        xrv.setVisibility(View.GONE);
                        tvDetail.setText("您还没有兑换过任何物品！");
                    } else {
                        count = list.size();
                    }

                } else {
                    relativeHint.setVisibility(View.GONE);
                    xrv.setVisibility(View.VISIBLE);
                    if (isRefresh) list.clear();
                    list.addAll(results);
                    sysMsgAdapter.notifyDataSetChanged();
                    count = list.size();
                }
            }
        }

        dismissLoadingDialog();
        isRefresh = false;
    }

    @Override
    public void onRequestFail(String msg) {
        isRefresh = false;
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }
}
