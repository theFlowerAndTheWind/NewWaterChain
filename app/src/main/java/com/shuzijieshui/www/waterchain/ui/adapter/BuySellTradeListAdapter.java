/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BuySellTradeListAdapter
 * Author: sxt
 * Date: 2019/1/1 3:54 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.adapter
 * @ClassName: BuySellTradeListAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/1 3:54 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/1 3:54 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BuySellTradeListAdapter extends RecyclerView.Adapter<BuySellTradeListAdapter.BuySellHolder> {

    private Context context;
    private List<TradeCenterResponseBean.BuySellEntity> list ;

    public BuySellTradeListAdapter(Context context, List<TradeCenterResponseBean.BuySellEntity>list) {
        this.context = context;
        if(list==null){
            this.list=new ArrayList<>();
        }else{
            this.list=list;
        }

    }

    @NonNull
    @Override
    public BuySellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_trade_list, parent, false);
        AutoUtils.auto(view);

        return new BuySellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuySellHolder holder, int position) {
        if(list.size()>0){
            TradeCenterResponseBean.BuySellEntity buySellEntity = list.get(position);
            if(buySellEntity.getName().trim().startsWith("贡献")){
                holder.tvName.setTextColor(context.getResources().getColor(R.color.primary_red));
            }else if(buySellEntity.getName().trim().startsWith("获取")){
                holder.tvName.setTextColor(context.getResources().getColor(R.color.text_green));
            }
            holder.tvName.setText(buySellEntity.getName());
            holder.tvPrice.setText(buySellEntity.getPrice());
            holder.tvTotal.setText(buySellEntity.getTotal());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BuySellHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_total)
        TextView tvTotal;

        public BuySellHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}