package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.GoodsDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public interface GoodsDetailViewImpl extends IBaseViewImpl {
    void onGoodsDetailSuccess(List<GoodsDetailResponseBean> beans);
    void onGoodsDetailFailed(String msg);
}
