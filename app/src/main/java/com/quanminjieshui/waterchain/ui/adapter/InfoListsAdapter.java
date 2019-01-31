/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InfoListsAdapter
 * Author: sxt
 * Date: 2019/1/1 10:20 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.InfoListsResponseBean;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.adapter
 * @ClassName: InfoListsAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/1 10:20 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/1 10:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class InfoListsAdapter extends RecyclerView.Adapter<InfoListsAdapter.InfoHolder> {


    private Context context;
    private LinkedList<InfoListsResponseBean.InfoEntity> list;
    private OnItemClickListener onItemClickListener;

    public InfoListsAdapter(Context context, LinkedList<InfoListsResponseBean.InfoEntity> list) {
        this.context = context;
        if (list == null) {
            list = new LinkedList<>();
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_recycle_item_info_lists, parent, false);
        AutoUtils.auto(view);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
        if (list != null && list.size() > 0) {
            final InfoListsResponseBean.InfoEntity infoEntity = list.get(position);
            if (infoEntity != null) {
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickListener(infoEntity.getId());
                    }
                });
                GlidImageManager.getInstance().loadImageView(context, infoEntity.getImg(), holder.img, R.mipmap.default_img);
                holder.tvTitle.setText(infoEntity.getTitle());
                CharSequence charSequence;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    charSequence = Html.fromHtml(infoEntity.getContent(),Html.FROM_HTML_MODE_LEGACY);
                } else {
                    charSequence = Html.fromHtml(infoEntity.getContent());
                }
                holder.tvContent.setText(charSequence);
                holder.tvPublishtime.setText(infoEntity.getPublishtime());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener=onItemClickListener;
    }


    class InfoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.container)
        RelativeLayout container;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_publishtime)
        TextView tvPublishtime;

        public InfoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int id);
    }
}