package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.GoodsDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public interface GoodsDetailViewImpl extends IBaseViewImpl {
    void onGoodsDetailSuccess(GoodsDetailResponseBean beans);
    void onGoodsDetailFailed(String msg);
}
