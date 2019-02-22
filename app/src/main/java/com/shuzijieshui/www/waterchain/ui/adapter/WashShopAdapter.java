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
 * Created by songxiaotao on 2018/12/19.
 * Class Note:洗涤商城
 */

public class WashShopAdapter extends RecyclerView.Adapter<WashShopAdapter.RecycleHolder>{

    private Context context;

    private List<Factory> list ;

    private OnItemClickListener listener;

    public WashShopAdapter(Context context, List<Factory> list){

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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_washshop_item,parent,false);
        AutoUtils.auto(view);
        return new RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, final int position) {
        GlidImageManager.getInstance().loadImageView(context,list.get(position).getLogo(),holder.serviceImg,R.mipmap.default_img);
        holder.serviceName.setText(TextUtils.isEmpty(list.get(position).getF_name()) ? "未知" :list.get(position).getF_name());
        holder.serviceScope.setText((TextUtils.isEmpty(list.get(position).getService_area()) ? "未知" :list.get(position).getService_area()));
        holder.businessScope.setText((TextUtils.isEmpty(list.get(position).getService_lists()) ? "未知" :list.get(position).getService_lists()));
        if(position == list.size()-1){
            holder.line.setVisibility(View.INVISIBLE);
        }else{
            holder.line.setVisibility(View.VISIBLE);
        }
        holder.btnService.setOnClickListener(new View.OnClickListener() {
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

        @BindView(R.id.service_img)
        ImageView serviceImg;
        @BindView(R.id.tv_service_name)
        TextView serviceName;
        @BindView(R.id.tv_service_scope)
        TextView serviceScope;
        @BindView(R.id.tv_bussiness_scope)
        TextView businessScope;
        @BindView(R.id.btn_service)
        Button btnService;
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
