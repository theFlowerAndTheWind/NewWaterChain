package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.TradeDetailResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeDetailAdapter extends RecyclerView.Adapter<TradeDetailAdapter.TradeDetailHolder> {

    private Context context;
    private List<TradeDetailResponseBean.TradeDetailEntry> list;

    public TradeDetailAdapter(Context context, List<TradeDetailResponseBean.TradeDetailEntry> list) {
        this.context = context;
        if (list == null)
            list = new ArrayList<>();
        else
            this.list = list;
    }

    @NonNull
    @Override
    public TradeDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_trade_detail, parent, false);
        AutoUtils.auto(view);
        return new TradeDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeDetailHolder holder, int position) {
        final TradeDetailResponseBean.TradeDetailEntry tradeDetailEntry = list.get(position);
        if (tradeDetailEntry != null) {
            holder.tvAddTime.setText(tradeDetailEntry.getAdd_time());
            holder.tvPrice.setText(tradeDetailEntry.getPrice());
            holder.tvTotal.setText(tradeDetailEntry.getTotal());
            holder.tvFee.setText(tradeDetailEntry.getFee());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TradeDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_add_time)
        TextView tvAddTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_total)
        TextView tvTotal;
        @BindView(R.id.tv_fee)
        TextView tvFee;

        public TradeDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
