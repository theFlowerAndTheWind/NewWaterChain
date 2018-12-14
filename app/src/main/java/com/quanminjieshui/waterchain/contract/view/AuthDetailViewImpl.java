package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.AuthDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface AuthDetailViewImpl extends IBaseViewImpl {
    void authDetailSuccess(AuthDetailResponseBean authDetailResponseBean);
    void authDetailFailed(String msg);
}
