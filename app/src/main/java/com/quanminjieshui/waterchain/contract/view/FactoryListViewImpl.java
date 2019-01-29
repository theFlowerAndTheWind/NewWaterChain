package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.FactoryListResponse;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public interface FactoryListViewImpl extends IBaseViewImpl {
    void onFactoryListSuccess(FactoryListResponse factoryListResponse);
    void onFactoryListFailed(String msg);
}
