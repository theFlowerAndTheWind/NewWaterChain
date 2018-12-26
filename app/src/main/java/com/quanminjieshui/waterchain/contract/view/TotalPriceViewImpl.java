package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:
 */

public interface TotalPriceViewImpl extends IBaseViewImpl {
    void onTotalPriceSuccess(TotalPriceResponseBean totalPriceResponseBean);
    void onTotalPriceFailed(String msg);
}
