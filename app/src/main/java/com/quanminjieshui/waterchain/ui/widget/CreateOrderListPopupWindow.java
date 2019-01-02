package com.quanminjieshui.waterchain.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.CreateOrderListBean;
import com.quanminjieshui.waterchain.ui.adapter.CreateOrderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WanghongHe on 2019/1/2 10:31.
 * 下单列表
 */

public class CreateOrderListPopupWindow extends PopupWindow{

    private Context mContext;
    private XRecyclerView recyclerView;
    private TextView totalCost;
    private ImageView cancelImg;
    private LayoutInflater mInflater;
    private View mContentView;
    private List<CreateOrderListBean> arrayList;
    private CreateOrderListAdapter createOrderListAdapter;

    public CreateOrderListPopupWindow(Context context, List<CreateOrderListBean> arrayList){
        this.mContext = context;
        if(arrayList == null){
            this.arrayList = new ArrayList<>();
        }else{
            this.arrayList = arrayList;
        }
        //打气筒
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //打气
        mContentView = mInflater.inflate(R.layout.popupwindow_creatorder_list, null);

        //设置View
        setContentView(mContentView);

        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        /**
         * 设置进出动画
         */
          setAnimationStyle(R.style.Popupwindow);

        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        setBackgroundDrawable(new ColorDrawable());

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);

        /**
         * 设置点击外部可以消失
         */

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });



        /**
         * 初始化View与监听器
         */
        initView();
    }

    private void initView() {
        recyclerView = mContentView.findViewById(R.id.cost_info_list);
        totalCost = mContentView.findViewById(R.id.total_cost);
        cancelImg = mContentView.findViewById(R.id.cancel);

        cancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        LinearLayoutManager layoutManage = new LinearLayoutManager(mContext);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(layoutManage);
        createOrderListAdapter = new CreateOrderListAdapter(mContext,arrayList);
        recyclerView.setAdapter(createOrderListAdapter);
    }


}
