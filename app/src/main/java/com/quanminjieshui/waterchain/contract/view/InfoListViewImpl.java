package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.InfoListResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoListViewImpl extends IBaseViewImpl {
    void infoListSuccess(InfoListResponseBean infoListResponseBean);
    void infoListFailed(String msg);
}
