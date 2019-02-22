/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.adapter
 * @ClassName: CurrentTradeListsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 6:36 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 6:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.adapter
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
    private List<TradeCenterResponseBean.UserCurrentTradeEntity> temp;
    private List<TradeCenterResponseBean.UserCurrentTradeEntity> list;
    private OnCancleClickedListener cancleListener;

    public CurrentTradeListsAdapter(Context context, List<TradeCenterResponseBean.UserCurrentTradeEntity> list, OnCancleClickedListener cancleListener) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
        this.cancleListener = cancleListener;
    }

    @NonNull
    @Override
    public TradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_cur_trade_list, parent, false);
        AutoUtils.auto(view);
        return new TradeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TradeHolder holder, int position) {
        final TradeCenterResponseBean.UserCurrentTradeEntity userCurrentTradeEntity = list.get(position);
        if (userCurrentTradeEntity != null) {
            String action_type = userCurrentTradeEntity.getAction_type().trim();
            if (action_type.equals("贡献")) {
                holder.tvActionType.setText("贡献");
                holder.tvActionType.setTextColor(context.getResources().getColor(R.color.primary_red));
            } else if (action_type.equals("获取")) {
                holder.tvActionType.setText("获取");
                holder.tvActionType.setTextColor(context.getResources().getColor(R.color.text_green));
            }
            holder.tvAddTime.setText(userCurrentTradeEntity.getAdd_time());
            final String status = userCurrentTradeEntity.getStatus();
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
                        cancleListener.onCancle(userCurrentTradeEntity.getId());
                    }
                });
            }
            holder.tvPrice.setText(userCurrentTradeEntity.getPrice());
            holder.tvOldTotal.setText(userCurrentTradeEntity.getOld_total());
            holder.tvDealTotal.setText(userCurrentTradeEntity.getDeal_total());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TradeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_action_type)
        TextView tvActionType;
        @BindView(R.id.tv_add_time)
        TextView tvAddTime;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_old_total)
        TextView tvOldTotal;
        @BindView(R.id.tv_deal_total)
        TextView tvDealTotal;

        public TradeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCancleClickedListener {
        void onCancle(int tid);
    }

}
