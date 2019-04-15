package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.SysMsg;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SysMsgAdapter extends RecyclerView.Adapter<SysMsgAdapter.SysMsgHolder> {

    private Context context;
    private List<SysMsg> list;

    public SysMsgAdapter(Context context, List<SysMsg> list) {
        this.context = context;
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    @Override
    public SysMsgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycler_item_sys_msg, parent, false);
        AutoUtils.auto(view);
        return new SysMsgHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SysMsgHolder holder, int position) {
        SysMsg sysMsg = list.get(position);
        if (sysMsg != null) {
            holder.tvTypeName.setText(sysMsg.getType_name());
            holder.tvAddTime.setText(sysMsg.getAdd_time());
            holder.tvEventTxt.setText(sysMsg.getEvent_text());
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


    class SysMsgHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_add_time)
        TextView tvAddTime;
        @BindView(R.id.tv_event_text)
        TextView tvEventTxt;

        public SysMsgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
