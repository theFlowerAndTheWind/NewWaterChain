package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public interface CheckUserPayViewImpl extends IBaseViewImpl {
    void onCheckUserPaySuccess(CheckUserPayResponseBean beans);
    void onCheckUserPayFailed(String msg);
}
