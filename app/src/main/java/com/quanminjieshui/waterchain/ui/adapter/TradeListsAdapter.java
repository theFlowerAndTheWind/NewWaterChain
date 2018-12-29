/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: TradeListsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 6:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 6:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * @CreateDate: 2018/12/27 6:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 6:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsAdapter extends RecyclerView.Adapter<TradeListsAdapter.TradeHolder> {

    private Context context;
    private List<TradeListsResponseBean.TradeListEntity> list;

    public TradeListsAdapter(Context context, List<TradeListsResponseBean.TradeListEntity> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public TradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_trade_lists, parent, false);
        AutoUtils.auto(view);
        return new TradeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeHolder holder, int position) {
        final TradeListsResponseBean.TradeListEntity tradeListEntity = list.get(position);
        String action_type = tradeListEntity.getAction_type().trim();
        if (action_type.equals("贡献")) {
            holder.tvActionType.setText("贡献");
            holder.tvActionType.setTextColor(context.getResources().getColor(R.color.primary_red));
        } else if (action_type.equals("获取")) {
            holder.tvActionType.setText("获取");
            holder.tvActionType.setTextColor(context.getResources().getColor(R.color.text_green));
        }
        holder.tvAvgPrice.setText(tradeListEntity.getAvg_price());
        holder.tvOldTotal.setText(tradeListEntity.getOld_total());
        holder.tvShiji.setText(tradeListEntity.getShiji());
        holder.tvDealTotal.setText(tradeListEntity.getDeal_total());
        holder.tvFee.setText(tradeListEntity.getFee());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TradeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_action_type)
        TextView tvActionType;
        @BindView(R.id.tv_avg_price)
        TextView tvAvgPrice;
        @BindView(R.id.tv_old_total)
        TextView tvOldTotal;
        @BindView(R.id.tv_deal_total)
        TextView tvDealTotal;
        @BindView(R.id.tv_shiji)
        TextView tvShiji;
        @BindView(R.id.tv_fee)
        TextView tvFee;

        public TradeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
