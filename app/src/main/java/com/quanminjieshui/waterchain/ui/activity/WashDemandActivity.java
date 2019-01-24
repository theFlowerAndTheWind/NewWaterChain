package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.ui.adapter.WashDetailListsAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/28.
 * Class Note:
 */

public class WashDemandActivity extends BaseActivity {

    @BindView(R.id.wash_detail_list)
    RecyclerView wash_detail_list;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;

    private int fscID,pieceCount;
    private String [] trade_detail;
    private WashDetailListsAdapter adapter;
    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> washFatoryCageGory = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("洗涤需求");
        if(getIntent()!=null){
            washFatoryCageGory = getIntent().getParcelableArrayListExtra("washFatoryCageGory");
            adapter = new WashDetailListsAdapter(WashDemandActivity.this,washFatoryCageGory);
            wash_detail_list.setLayoutManager(new LinearLayoutManager(WashDemandActivity.this));
            wash_detail_list.setAdapter(adapter);
            adapter.setOnPlusClickListener(new WashDetailListsAdapter.OnPlusClickListener() {
                @Override
                public void onItemPlusClick(int position) {
                    fscID = washFatoryCageGory.get(position).getFscid();
                    pieceCount = washFatoryCageGory.get(position).getPiceCount();

                }
            });
            adapter.setOnSubtractClickListener(new WashDetailListsAdapter.OnSubtractClickListener() {
                @Override
                public void onItemSubtractClick(int position) {
                    fscID = washFatoryCageGory.get(position).getFscid();
                    pieceCount = washFatoryCageGory.get(position).getPiceCount();
                }
            });

        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_wash_detail_list);

    }

    @OnClick({R.id.left_ll,R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_ll:
                goBack(view);
                break;
            case R.id.btn_save:
                trade_detail = new String[washFatoryCageGory.size()];
                for(int i=0;i<washFatoryCageGory.size();i++){
                    trade_detail[i] = washFatoryCageGory.get(i).getFscid()+"_"+washFatoryCageGory.get(i).getPiceCount()+"_"+washFatoryCageGory.get(i).getC_name();
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("class",2);
                bundle.putStringArray("trade_detail",trade_detail);
                intent.setClass(WashDemandActivity.this,ConfirmOrderActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
