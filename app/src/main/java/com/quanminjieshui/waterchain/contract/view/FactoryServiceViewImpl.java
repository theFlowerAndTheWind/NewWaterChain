package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/22.
 * Class Note:
 */

public interface FactoryServiceViewImpl extends IBaseViewImpl {
    void onFactoryServiceSuceess(FactoryServiceResponseBean factoryServiceResponseBean);
    void onFactoryServiceFailed(String msg);
}
