package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.AuthDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface AuthDetailViewImpl extends IBaseViewImpl {
    void authDetailSuccess(AuthDetailResponseBean authDetailResponseBean);
    void authDetailFailed(String msg);
}
