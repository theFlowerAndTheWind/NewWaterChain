package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.FactoryDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/21.
 * Class Note:
 */

public interface FactoryDetailViewImpl extends IBaseViewImpl {
    void onFactoryDetailSuccess(FactoryDetailResponseBean factoryDetailResponseBean);
    void onFactoryDetailFailed(String msg);
}
