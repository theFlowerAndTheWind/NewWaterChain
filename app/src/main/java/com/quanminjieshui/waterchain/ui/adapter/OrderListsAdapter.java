package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class OrderListsAdapter extends RecyclerView.Adapter<OrderListsAdapter.OrderListViewHolder> {
    private Context context;

    public OrderListsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        public OrderListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
