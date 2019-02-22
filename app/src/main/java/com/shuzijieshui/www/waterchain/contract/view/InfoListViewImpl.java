package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.InfoListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoListViewImpl extends IBaseViewImpl {
    void infoListSuccess(InfoListsResponseBean infoListResponseBean);
    void infoListFailed(String msg);
}
