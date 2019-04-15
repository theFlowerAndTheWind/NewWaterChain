package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GoodsListModel;
import com.shuzijieshui.www.waterchain.contract.view.GoodsListViewImpl;

import java.util.List;

public class GoodsListPresenter extends BasePresenter<GoodsListViewImpl> {
    private GoodsListModel goodsListModel;

    public GoodsListPresenter(GoodsListModel goodsListModel) {
        this.goodsListModel = goodsListModel;
    }

    public void goodsList(BaseActivity activity, int cate_id, int count) {
        if (goodsListModel == null) {
            goodsListModel = new GoodsListModel();
        }
        goodsListModel.getGoodsLists(activity, cate_id, count, new GoodsListModel.GoodsListsCallBack() {
            @Override
            public void success(List<GoodsListsResponseBean> goodsListsResponseBean, int cate_id) {
                if (mView != null) {
                    mView.onGoodsListSucc(goodsListsResponseBean, cate_id);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView != null) {
                    mView.onGoodsListFail(msg);
                }
            }
        });
    }

}
