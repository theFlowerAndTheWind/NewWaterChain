package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songxiaotao on 2018/12/28.
 * Class Note:洗涤需求
 */

public class WashDetailListsAdapter extends RecyclerView.Adapter<WashDetailListsAdapter.RecycleHolder>{

    private Context context;

    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> list =new ArrayList<>();

    private OnPlusClickListener plusListener;

    private OnSubtractClickListener subtractListener;

    public WashDetailListsAdapter(Context context, ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> list){
        this.context = context;
        this.list.clear();
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_services_counter,parent,false);
        AutoUtils.auto(view);
        return new RecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleHolder holder, final int position) {
        final FactoryServiceResponseBean.WashFatoryCageGory entry = list.get(position);
        if(entry==null){return;}
        final int[] piceCount = {entry.getPiceCount()};

        holder.serviceName.setText(entry.getC_name());
        holder.counter.setText(String.valueOf(piceCount[0]));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(plusListener!=null){
                    piceCount[0] +=1;
                    if(piceCount[0]>65534){
                        piceCount[0]=65535;
                    }
                    entry.setPiceCount(piceCount[0]);
                    holder.counter.setText(String.valueOf(entry.getPiceCount()));
                    plusListener.onItemPlusClick(position);
                }
            }
        });
        holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subtractListener!=null){
                    piceCount[0] -=1;
                    if(piceCount[0]<=0){
                        piceCount[0]=0;
                    }
                    entry.setPiceCount(piceCount[0]);
                    holder.counter.setText(String.valueOf(entry.getPiceCount()));
                    subtractListener.onItemSubtractClick(position);
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
        ImageView btnPlus;
        @BindView(R.id.btn_subtract)
        ImageView btnSubtract;
        @BindView(R.id.tv_service_name)
        TextView serviceName;
        @BindView(R.id.tv_counter)
        TextView counter;

        public RecycleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public List<FactoryServiceResponseBean.WashFatoryCageGory> getList(){
        return Collections.unmodifiableList(list);
    }


    public interface OnPlusClickListener{
        void onItemPlusClick(int position);
    }

    public void setOnPlusClickListener(OnPlusClickListener plusListener){
        this.plusListener = plusListener;
    }

    public interface OnSubtractClickListener{
        void onItemSubtractClick(int position);
    }

    public void setOnSubtractClickListener(OnSubtractClickListener subtractListener){
        this.subtractListener = subtractListener;
    }


}
