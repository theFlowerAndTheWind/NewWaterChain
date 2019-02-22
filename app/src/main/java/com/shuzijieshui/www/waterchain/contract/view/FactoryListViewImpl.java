package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.FactoryListResponse;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public interface FactoryListViewImpl extends IBaseViewImpl {
    void onFactoryListSuccess(FactoryListResponse factoryListResponse);
    void onFactoryListFailed(String msg);
}
