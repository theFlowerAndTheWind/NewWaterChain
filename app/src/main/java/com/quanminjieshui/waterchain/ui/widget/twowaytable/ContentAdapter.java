package com.quanminjieshui.waterchain.ui.widget.twowaytable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/24.
 * Class Note:
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {
    private List<ContentBean> contentBeanList;
    private Context context;

    public ContentAdapter(List<ContentBean> contentBeanList, Context context) {
        this.contentBeanList = contentBeanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.table_item_content, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.t0.setText(contentBeanList.get(position).getT0() + "");
        holder.t1.setText(contentBeanList.get(position).getT1() + "");
        holder.t2.setText(contentBeanList.get(position).getT2() + "");
    }

    @Override
    public int getItemCount() {
        return contentBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t0, t1, t2;

        public MyViewHolder(View itemView) {
            super(itemView);
            t0 = (TextView) itemView.findViewById(R.id.t0);
            t1 = (TextView) itemView.findViewById(R.id.t1);
            t2 = (TextView) itemView.findViewById(R.id.t2);
        }
    }
}

