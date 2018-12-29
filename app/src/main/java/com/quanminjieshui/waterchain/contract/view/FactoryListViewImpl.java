package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.FactoryListResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:
 */

public interface FactoryListViewImpl extends IBaseViewImpl {
    void onFactoryListSuccess(List<FactoryListResponseBean> factoryListEntities);
    void onFactoryListFailed(String msg);
}
