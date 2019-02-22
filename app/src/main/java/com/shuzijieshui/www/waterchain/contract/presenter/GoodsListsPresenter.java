package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GoodsListModel;
import com.shuzijieshui.www.waterchain.contract.view.GoodsListViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2019/1/30.
 * Class Note:
 */

public class GoodsListsPresenter extends BasePresenter<GoodsListViewImpl> {

    private GoodsListModel goodsListModel;

    public GoodsListsPresenter(){}

    public GoodsListsPresenter(GoodsListModel goodsListModel){
        this.goodsListModel = goodsListModel;
    }

    /**
     *
     * @param activity
     * @param cate_id 商品分类 1（实物商品）|2（活动）
     * @param count 当前页面已显示指定分类下数据个数
     */
    public void getGoodsLists(BaseActivity activity, final int cate_id, int count, final String currentPage){
        if (goodsListModel ==null){
            goodsListModel = new GoodsListModel();
        }
        goodsListModel.getGoodsLists(activity, cate_id, count, new GoodsListModel.GoodsListsCallBack() {

            @Override
            public void failed(String msg) {
                if (currentPage.equals("1")){
                    if (mView != null ) {
                        mView.onGetGoodsListFailed(true,msg,cate_id);
                    }
                }else {
                    if (mView != null) {mView.onGetGoodsListFailed(false,msg,cate_id);}
                }

            }

            @Override
            public void success(List<GoodsListsResponseBean> goodsListsResponseBean) {
                if (currentPage.equals("1")){
                    if (mView != null) {mView.onGetGoodsListonRefresh(goodsListsResponseBean,cate_id);}

                }else {
                    if (mView != null) {mView.onGetGoodsListonloadMore(goodsListsResponseBean,cate_id);}
                }

            }
        });
    }
}
