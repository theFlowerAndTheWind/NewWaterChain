package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/12/28.
 * Class Note:洗涤需求
 */

public class WashDetailListsAdapter extends RecyclerView.Adapter<WashDetailListsAdapter.RecycleHolder>{

    private Context context;

    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> list ;

    private WashDetailListsAdapter.OnPlusClickListener plusListener;

    private WashDetailListsAdapter.OnSubtractClickListener subtractListener;

    private int piece = 0;

    public WashDetailListsAdapter(Context context, ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> list){

        this.context = context;
        if(list == null){
            this.list = new ArrayList<>();
        }else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public WashDetailListsAdapter.RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_services_counter,parent,false);
        AutoUtils.auto(view);
        return new WashDetailListsAdapter.RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WashDetailListsAdapter.RecycleHolder holder, final int position) {
        holder.serviceName.setText(list.get(position).getC_name());

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(plusListener!=null){
                    ++piece;
                    holder.counter.setText(String.valueOf(piece));
                    plusListener.onItemPlusClick(piece,list.get(position).getC_name());
                }
            }
        });
        holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subtractListener!=null){
                    --piece;
                    holder.counter.setText(String.valueOf(piece));
                    subtractListener.onItemSubtractClick(piece,list.get(position).getC_name());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecycleHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.btn_plus)
        ImageButton btnPlus;
        @BindView(R.id.btn_subtract)
        ImageButton btnSubtract;
        @BindView(R.id.tv_service_name)
        TextView serviceName;
        @BindView(R.id.tv_counter)
        TextView counter;

        public RecycleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnPlusClickListener{
        void onItemPlusClick(int piece,String type);
    }

    public void setOnPlusClickListener(WashDetailListsAdapter.OnPlusClickListener plusListener){
        this.plusListener = plusListener;
    }

    public interface OnSubtractClickListener{
        void onItemSubtractClick(int piece,String type);
    }

    public void setOnSubtractClickListener(WashDetailListsAdapter.OnSubtractClickListener subtractListener){
        this.subtractListener = subtractListener;
    }


}
