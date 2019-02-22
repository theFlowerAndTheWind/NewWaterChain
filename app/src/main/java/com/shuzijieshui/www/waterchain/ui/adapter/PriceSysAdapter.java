package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceSysAdapter extends RecyclerView.Adapter<PriceSysAdapter.PriceHolder> {

    private Context context;
    private LinkedList<FactoryServiceResponseBean.WashFatoryCageGory> list = new LinkedList<>();

    public PriceSysAdapter(Context context, LinkedList list) {
        this.context = context;
        this.list.addAll(list);
        FactoryServiceResponseBean.WashFatoryCageGory bean = new FactoryServiceResponseBean.WashFatoryCageGory("类别", "单价（元）", "单位", "规格");
        this.list.addFirst(bean);

    }

    @NonNull
    @Override
    public PriceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_cate_lists, parent, false);
        AutoUtils.auto(view);
        return new PriceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceHolder holder, int position) {
        FactoryServiceResponseBean.WashFatoryCageGory washFatoryCageGory = list.get(position);
        if (washFatoryCageGory != null) {
            if (position == 0) {
                holder.container.setBackgroundColor(context.getResources().getColor(R.color.divider_gray));
            }
            holder.tvCName.setText(washFatoryCageGory.getC_name());
            holder.tvPiece.setText(washFatoryCageGory.getPiece());
            holder.tvStandard.setText(washFatoryCageGory.getStandard());
            holder.tvUnitPrice.setText(washFatoryCageGory.getUnit_price());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PriceHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llContainer)
        LinearLayout container;
        @BindView(R.id.tv_c_name)
        TextView tvCName;
        @BindView(R.id.tv_piece)
        TextView tvPiece;
        @BindView(R.id.tv_standard)
        TextView tvStandard;
        @BindView(R.id.tv_unit_price)
        TextView tvUnitPrice;

        public PriceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
