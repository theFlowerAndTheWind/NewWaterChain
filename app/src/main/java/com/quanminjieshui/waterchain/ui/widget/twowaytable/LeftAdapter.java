package com.quanminjieshui.waterchain.ui.widget.twowaytable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/24.
 * Class Note:
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private List<FactoryServiceResponseBean.WashFatoryCageGory> leftList;
    private Context context;

    public LeftAdapter(List<FactoryServiceResponseBean.WashFatoryCageGory> leftList, Context context) {
        this.leftList = leftList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.table_item_left, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleText.setText(leftList.get(position).getC_name());
    }

    @Override
    public int getItemCount() {
        return leftList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.titleText);
        }
    }
}

