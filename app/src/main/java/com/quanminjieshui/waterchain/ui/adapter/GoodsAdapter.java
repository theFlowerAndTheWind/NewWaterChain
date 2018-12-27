/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: GoodsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/26 11:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 11:06 PM
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
import com.quanminjieshui.waterchain.beans.GoodsResposeBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: GoodsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/26 11:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 11:06 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsHolder> {

    private Context context;
    private List<GoodsResposeBean> list;

    public GoodsAdapter(Context context, List<GoodsResposeBean> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public GoodsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_goods, parent, false);
        AutoUtils.auto(view);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsHolder holder, int position) {
        GoodsResposeBean goodsResposeBean = list.get(position);
        holder.tvOrderSn.setText(goodsResposeBean.getOrder_sn());
        holder.tvGName.setText(goodsResposeBean.getG_name());
        holder.tvCName.setText(goodsResposeBean.getC_name());
        holder.tvTotalPrice.setText(goodsResposeBean.getTotal_price());
        holder.tvCreatetime.setText(goodsResposeBean.getCreatetime());
        holder.tvStatusView.setText(goodsResposeBean.getStatus_view());
        if (position == list.size() - 1) {
            holder.vBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }


    class GoodsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_sn)
        TextView tvOrderSn;
        @BindView(R.id.tv_g_name)
        TextView tvGName;
        @BindView(R.id.tv_c_name)
        TextView tvCName;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.tv_createtime)
        TextView tvCreatetime;
        @BindView(R.id.tv_status_view)
        TextView tvStatusView;
        @BindView(R.id.v_bottom)
        View vBottom;

        public GoodsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
