package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.ServiceListResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public interface ServiceListViewImpl extends IBaseViewImpl {
    void onServiceListSuccess(List<ServiceListResponseBean.ServiceListEntity> serviceListEntities);
    void onServiceListFailed(String msg);
}
