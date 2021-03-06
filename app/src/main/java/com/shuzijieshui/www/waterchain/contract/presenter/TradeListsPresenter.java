/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.presenter
 * @ClassName: TradeListsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:32 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.TradeListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.TradeListsModel;
import com.shuzijieshui.www.waterchain.contract.view.TradeListsViewImpl;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.presenter
 * @ClassName: TradeListsPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:32 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsPresenter extends BasePresenter<TradeListsViewImpl> {

    private TradeListsModel tradeListsModel;


    public TradeListsPresenter(TradeListsModel tradeListsModel) {
        this.tradeListsModel = tradeListsModel;
    }

    public void getTradeLists(BaseActivity activity) {
        if (tradeListsModel == null) {
            tradeListsModel = new TradeListsModel();
        }
        tradeListsModel.getTradeLists(activity, new TradeListsModel.TradeListsCallback() {
            @Override
            public void onGetTradeListsSuccess(TradeListsResponseBean tradeListsResponseBean) {
                if (mView != null) {
                    mView.onGetTradeListsSuccess(tradeListsResponseBean);
                }
            }

            @Override
            public void onGetTradeListsFailed(String msg) {
                if (mView != null) {
                    mView.onGetTradeListsFailed(msg);
                }
            }
        });

    }
}
