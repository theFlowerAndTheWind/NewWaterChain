//package com.shuzijieshui.www.waterchain.ui.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.shuzijieshui.www.waterchain.R;
//import com.shuzijieshui.www.waterchain.beans.TotalPriceResponseBean;
//import com.zhy.autolayout.utils.AutoUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by songxiaotao on 2019/1/2.
// * Class Note:
// */
//
//public class CreateOrderListAdapter extends RecyclerView.Adapter<CreateOrderListAdapter.OrderListViewHolder>{
//
//    private Context context;
//    private List<TotalPriceResponseBean.WashType> list;
//
//    public CreateOrderListAdapter(Context context, List<TotalPriceResponseBean.WashType> list) {
//        this.context = context;
//        if (list == null) {
//            this.list = new ArrayList<>();
//        } else {
//            this.list = list;
//        }
//    }
//
//    @NonNull
//    @Override
//    public CreateOrderListAdapter.OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.poppupwindow_create_order_item, parent, false);
//        AutoUtils.auto(v);
//        return new CreateOrderListAdapter.OrderListViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CreateOrderListAdapter.OrderListViewHolder holder, int position) {
//        final TotalPriceResponseBean.WashType entity = list.get(position);
//        if(entity==null) return;
//
//        holder.costName.setText(entity.getC_name());
//        holder.costNumber.setText(new StringBuilder("+ ¥").append(entity.getUnit_price()).append(" X ").append(entity.getTotal()).toString());
//
////        if(position == list.size()-1){
////            holder.line.setVisibility(View.INVISIBLE);
////        }else{
////            holder.line.setVisibility(View.VISIBLE);
////        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if (list == null) {
//            return 0;
//        } else {
//            return list.size();
//        }
//    }
//
//    class OrderListViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.cost_name)
//        TextView costName;
//        @BindView(R.id.cost_number)
//        TextView costNumber;
//
//
//        public OrderListViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//        }
//    }
//}
