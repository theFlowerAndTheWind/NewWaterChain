package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.InfoDetailRespoonseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public interface InfoDetailViewImpl extends IBaseViewImpl {
    void infoDetailSuccess(InfoDetailRespoonseBean infoDetailRespoonseBean);
    void infoDetailFailed(String msg);
}
