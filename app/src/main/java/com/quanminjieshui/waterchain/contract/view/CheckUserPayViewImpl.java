package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.CheckUserPayResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public interface CheckUserPayViewImpl extends IBaseViewImpl {
    void onCheckUserPaySuccess(CheckUserPayResponseBean beans);
    void onCheckUserPayFailed(String msg);
}
