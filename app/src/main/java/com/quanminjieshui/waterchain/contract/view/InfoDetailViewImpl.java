package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.InfoDetailRespoonseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoDetailViewImpl extends IBaseViewImpl {
    void infoDetailSuccess(InfoDetailRespoonseBean infoDetailRespoonseBean);
    void infoDetailFailed(String msg);
}
