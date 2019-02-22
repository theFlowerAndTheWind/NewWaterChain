package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public interface FactoryServiceViewImpl extends IBaseViewImpl {
    void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean);
    void onFactoryServiceFailed(String msg);
}
