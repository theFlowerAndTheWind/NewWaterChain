package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.Factory;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:首页洗涤企业接口
 */

public class FactoryListIndexAdapter extends RecyclerView.Adapter<FactoryListIndexAdapter.RecycleHolder>{

    private Context context;

    private List<Factory> list ;

    private OnItemClickListener listener;

    public FactoryListIndexAdapter(Context context, List<Factory> list){

        this.context = context;
        if(list == null){
            this.list = new ArrayList<>();
        }else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_item,parent,false);
        AutoUtils.auto(view);
        return new RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, final int position) {
        GlidImageManager.getInstance().loadImageView(context,list.get(position).getLogo(),holder.washImg,R.mipmap.default_img);
        holder.washDesc.setText(TextUtils.isEmpty(list.get(position).getService_lists()) ? "" :list.get(position).getService_lists());
        holder.washTitle.setText(list.get(position).getF_name());
        if(position == list.size()-1){
            holder.line.setVisibility(View.INVISIBLE);
        }else{
            holder.line.setVisibility(View.VISIBLE);
        }
        holder.washOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecycleHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.wash_img)
        ImageView washImg;
        @BindView(R.id.tv_order_title)
        TextView washTitle;
        @BindView(R.id.tv_order_desc)
        TextView washDesc;
        @BindView(R.id.btn_order)
        Button washOrder;
        @BindView(R.id.line)
        View line;
        public RecycleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
