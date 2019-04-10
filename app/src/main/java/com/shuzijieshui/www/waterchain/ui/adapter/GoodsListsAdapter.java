/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InfoListsAdapter
 * Author: sxt
 * Date: 2019/1/1 10:20 PM
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现兑换商城
 */
public class GoodsListsAdapter extends RecyclerView.Adapter<GoodsListsAdapter.GoodsHolder> {


    private Context context;
    private List<GoodsListsResponseBean> list;
    private GoodsListItemListener goodsListItemListener;

    public GoodsListsAdapter(Context context, List<GoodsListsResponseBean> list, GoodsListItemListener goodsListItemListener) {
        this.context = context;
        this.goodsListItemListener = goodsListItemListener;
        if (list == null) {
            list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public GoodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_info_lists, parent, false);
        AutoUtils.auto(view);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsHolder holder, final int position) {
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsListItemListener.onItemClickListener(list.get(position).getId());
            }
        });
        GlidImageManager.getInstance().loadImageView(context, list.get(position).getImg(), holder.img, R.mipmap.default_img);
        holder.tvTitle.setText(list.get(position).getName());
        holder.tvContent.setText(list.get(position).getJsl()+" 水方");
        holder.tvContent.setTextColor(context.getResources().getColor(R.color.primary_red));
        holder.tvPublishtime.setText("市场价：¥ "+list.get(position).getPrice());
        holder.tvPublishtime.setVisibility(View.GONE);//产品要求去掉市场价信息
        if (position == list.size()-1){
            holder.divider.setVisibility(View.INVISIBLE);
        }else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GoodsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.container)
        RelativeLayout container;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_publishtime)
        TextView tvPublishtime;
        @BindView(R.id.divider)
        View divider;
        public GoodsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface GoodsListItemListener {
        void onItemClickListener(int id);
    }
}