package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.GoodsListsResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2019/1/30.
 * Class Note:
 */

public interface GoodsListViewImpl extends IBaseViewImpl {
    void onGetGoodsListonRefresh(List<GoodsListsResponseBean> goodsListsResponseBean,int cateId);
    void onGetGoodsListonloadMore(List<GoodsListsResponseBean> goodsListsResponseBean,int cateId);
    void onGetGoodsListFailed(boolean isRefresh,String msg,int cateId);
}
