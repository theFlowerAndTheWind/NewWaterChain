package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;
import com.quanminjieshui.waterchain.ui.activity.OrderDetailActivity;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        if(entity==null) return;
        holder.rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("fid",entity.getFid());
                intent.putExtra("id",entity.getId());
                intent.setClass(context,OrderDetailActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tvFactoryName.setText(entity.getF_name());
        GlidImageManager.getInstance().loadImageView(context,list.get(position).getImg(),holder.img,R.mipmap.default_img);
        holder.tvService.setText(entity.getS_name());
        holder.tvTotalPrice.setText(entity.getTotal_price());
        holder.tvStatus.setText(entity.getStatus());

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
        @BindView(R.id.rl1)
        RelativeLayout rl1;
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
            ButterKnife.bind(this,itemView);
        }
    }
}
