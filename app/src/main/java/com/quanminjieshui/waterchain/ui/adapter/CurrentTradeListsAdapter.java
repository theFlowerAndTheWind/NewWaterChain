/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: CurrentTradeListsAdapter
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.TradeListsResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.TradeCenterPresenter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: CurrentTradeListsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 6:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/31 3:36 AM
 * @UpdateRemark: 添加是否隐藏 已成交量、手续费 逻辑
 * @Version: 1.0
 */
public class CurrentTradeListsAdapter extends RecyclerView.Adapter<CurrentTradeListsAdapter.TradeHolder> {

    private Context context;
    private List<TradeListsResponseBean.TradeListEntity> list;
    private boolean isHide=false;//true->当前委托   false->全部委托

    public CurrentTradeListsAdapter(Context context, List<TradeListsResponseBean.TradeListEntity> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    public CurrentTradeListsAdapter(Context context, List<TradeListsResponseBean.TradeListEntity> list,boolean isHide) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
        this.isHide=isHide;
    }

    @NonNull
    @Override
    public TradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_all_trade_lists, parent, false);
        AutoUtils.auto(view);
        return new TradeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TradeHolder holder, int position) {
        final TradeListsResponseBean.TradeListEntity tradeListEntity = list.get(position);
        String action_type = tradeListEntity.getAction_type().trim();
        if (action_type.equals("贡献")) {
            holder.tvActionType.setText("贡献");
            holder.tvActionType.setTextColor(context.getResources().getColor(R.color.primary_red));
        } else if (action_type.equals("获取")) {
            holder.tvActionType.setText("获取");
            holder.tvActionType.setTextColor(context.getResources().getColor(R.color.text_green));
        }
        holder.tvPrice.setText(tradeListEntity.getAvg_price());
        holder.tvOldTotal.setText(tradeListEntity.getOld_total());
        holder.tvShiji.setText(tradeListEntity.getShiji());
        holder.tvDealTotal.setText(tradeListEntity.getDeal_total());
        holder.tvFee.setText(tradeListEntity.getFee());
        if(isHide){
            holder.ll3.setVisibility(View.GONE);
            holder.ll4.setVisibility(View.GONE);
            holder.container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int visibility = holder.container.getVisibility();
                    if(visibility==View.VISIBLE){
                        holder.ll3.setVisibility(View.GONE);
                        holder.ll4.setVisibility(View.GONE);
                    }else if(visibility==View.GONE){
                        holder.ll3.setVisibility(View.VISIBLE);
                        holder.ll4.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TradeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.tv_action_type)
        TextView tvActionType;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_old_total)
        TextView tvOldTotal;
        @BindView(R.id.tv_deal_total)
        TextView tvDealTotal;
        @BindView(R.id.tv_shiji)
        TextView tvShiji;
        @BindView(R.id.tv_fee)
        TextView tvFee;
        @BindView(R.id.ll3)
        LinearLayout ll3;
        @BindView(R.id.ll4)
        LinearLayout ll4;

        public TradeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
