package com.quanminjieshui.waterchain.ui.adapter;

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

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.FactoryListResponseBean;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;
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

    private List<FactoryListResponseBean> list ;

    private WashShopAdapter.OnItemClickListener listener;

    public WashShopAdapter(Context context, List<FactoryListResponseBean> list){

        this.context = context;
        if(list == null){
            this.list = new ArrayList<>();
        }else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public WashShopAdapter.RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_washshop_item,parent,false);
        AutoUtils.auto(view);
        return new WashShopAdapter.RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WashShopAdapter.RecycleHolder holder, final int position) {
        GlidImageManager.getInstance().loadImageUri(context,list.get(position).getLogo(),holder.serviceImg,R.drawable.ic_default_image);
        holder.serviceName.setText(TextUtils.isEmpty(list.get(position).getF_name()) ? "未知" :list.get(position).getF_name());
        holder.serviceScope.setText("服务范围："+(TextUtils.isEmpty(list.get(position).getService_scope()) ? "未知" :list.get(position).getService_scope()));
        holder.businessScope.setText("业务范围："+(TextUtils.isEmpty(list.get(position).getBussiness_scope()) ? "未知" :list.get(position).getBussiness_scope()));
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

    public void setOnItemClickListener(WashShopAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
