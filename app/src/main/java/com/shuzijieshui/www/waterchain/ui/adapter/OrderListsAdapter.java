package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.ui.activity.OrderDetailActivity1;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListsAdapter extends RecyclerView.Adapter<OrderListsAdapter.OrderListViewHolder> {

    private Context context;
    private List<OrderListsResponseBean.OrderListEntity> list;

    public OrderListsAdapter(Context context, List<OrderListsResponseBean.OrderListEntity> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_order_lists, parent, false);
        AutoUtils.auto(v);
        return new OrderListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        final OrderListsResponseBean.OrderListEntity entity = list.get(position);
        if (entity == null) return;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", String.valueOf(entity.getId()));
                intent.setClass(context, OrderDetailActivity1.class);
                context.startActivity(intent);
            }
        });
        holder.tvFactoryName.setText(entity.getF_name());
        GlidImageManager.getInstance().loadImageView(context, list.get(position).getImg(), holder.img, R.drawable.ic_default_image);
        holder.tvService.setText(entity.getS_name());
        holder.tvTotalPrice.setText(entity.getTotal_price());
        holder.tvStatus.setText(entity.getStatus_view());

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.tv_factory_name)
        TextView tvFactoryName;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_service)
        TextView tvService;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        public OrderListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
