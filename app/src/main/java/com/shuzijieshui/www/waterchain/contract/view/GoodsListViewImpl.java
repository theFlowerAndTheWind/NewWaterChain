package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

import java.util.List;

public interface GoodsListViewImpl extends IBaseViewImpl {
    void onGoodsListSucc(List<GoodsListsResponseBean> beans, int cate_id);
    void onGoodsListFail(String msg);
}
