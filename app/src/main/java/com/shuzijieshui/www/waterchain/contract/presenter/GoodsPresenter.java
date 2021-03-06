/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.presenter
 * @ClassName: GoodsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:33 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GoodsResposeBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.GoodsModel;
import com.shuzijieshui.www.waterchain.contract.view.GoodsViewImpl;

import java.util.List;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.presenter
 * @ClassName: GoodsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:33 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:33 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsPresenter extends BasePresenter<GoodsViewImpl> {
    private GoodsModel goodsModel;


    public GoodsPresenter(GoodsModel goodsModel) {
        this.goodsModel = goodsModel;
    }

    public void getGoods(BaseActivity activity,int count) {
        if (goodsModel == null) {
            goodsModel = new GoodsModel();
        }
        goodsModel.getGoods(activity,count, new GoodsModel.GoodsCallback() {
            @Override
            public void onGetGoodsSuccess(List<GoodsResposeBean> list) {
                if (mView != null) {
                    mView.onGetGoodsSuccess(list);
                }
            }

            @Override
            public void onGetGoodsFailed(String msg) {
                if (mView != null) {
                    mView.onGetGoodsFailed(msg);
                }
            }
        });

    }

}
