package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.AccountDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface AccountDetailViewImpl extends IBaseViewImpl {

    void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean);
    void onAccountDetailFailed(String msg);
}
