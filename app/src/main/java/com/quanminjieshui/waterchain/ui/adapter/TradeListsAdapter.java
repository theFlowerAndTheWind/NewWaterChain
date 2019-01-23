/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TradeListsAdapter
 * Author: sxt
 * Date: 2019/1/2 7:45 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.TradeListsResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: TradeListsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/2 7:45 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 7:45 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsAdapter extends RecyclerView.Adapter<TradeListsAdapter.TradeHolder> {

    private Context context;
    private List<TradeListsResponseBean.TradeEntity> list;
    private OnItemClickedListener onItemClickedListener;

    public TradeListsAdapter(Context context, List<TradeListsResponseBean.TradeEntity> list,OnItemClickedListener onItemClickedListener) {
        this.context = context;
        if (list == null) {
            list = new ArrayList<>();
        } else {
            this.list = list;
        }
        this.onItemClickedListener=onItemClickedListener;
    }

    @NonNull
    @Override
    public TradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_all_trade_lists, parent, false);
        AutoUtils.auto(view);
        return new TradeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeHolder holder, int position) {
        if (list != null) {
            final TradeListsResponseBean.TradeEntity tradeEntity = list.get(position);
            if (tradeEntity != null) {
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickedListener.onItemClicked(tradeEntity.getId());
                    }
                });
                String action_type = tradeEntity.getAction_type().trim();
                if (action_type.equals("贡献")) {
                    holder.tvActionType.setText("贡献");
                    holder.tvActionType.setTextColor(context.getResources().getColor(R.color.primary_red));
                } else if (action_type.equals("获取")) {
                    holder.tvActionType.setText("获取");
                    holder.tvActionType.setTextColor(context.getResources().getColor(R.color.text_green));
                }
                final String status = tradeEntity.getStatus();
                if (TextUtils.isEmpty(status)) {
                    //不处理
                } else if (status.equals("部分成交") || status.equals("全部成交") || status.equals("已撤销")) {
                    holder.tvStatus.setBackground(context.getResources().getDrawable(R.drawable.gray_border_bg_gray_shape));
                    holder.tvStatus.setEnabled(false);
                    holder.tvStatus.setText(status);
                    holder.tvStatus.setTextColor(context.getResources().getColor(R.color.text_gray));
                } else if (status.equals("等待成交")) {//文档只给了上面三种选择,实际请求回来有这种
                    holder.tvStatus.setBackground(context.getResources().getDrawable(R.drawable.btn_blue_border_selector));
                    holder.tvStatus.setEnabled(true);
                    holder.tvStatus.setText("撤销");
                    holder.tvStatus.setTextColor(context.getResources().getColor(R.color.primary_blue));
                    holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickedListener.onCancleClicked(tradeEntity.getId());
                        }
                    });
                }
                holder.tvAddTime.setText(tradeEntity.getAdd_time());
                holder.tvPrice.setText(tradeEntity.getPrice());
                holder.tvOldTotal.setText(tradeEntity.getOld_total());
                holder.tvDealTotal.setText(tradeEntity.getDeal_total());
                holder.tvFee.setText(tradeEntity.getFee());
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class TradeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_action_type)
        TextView tvActionType;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_add_time)
        TextView tvAddTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_old_total)
        TextView tvOldTotal;
        @BindView(R.id.tv_deal_total)
        TextView tvDealTotal;
        @BindView(R.id.tv_fee)
        TextView tvFee;
        @BindView(R.id.container)
        LinearLayout container;

        public TradeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickedListener {
        void onItemClicked(int id);
        void onCancleClicked(int tid);
    }

}