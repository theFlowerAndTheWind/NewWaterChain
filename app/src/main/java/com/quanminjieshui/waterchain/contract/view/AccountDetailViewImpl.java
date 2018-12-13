package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.AccountDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface AccountDetailViewImpl extends IBaseViewImpl {

    void onAccountDetailSuccess(AccountDetailResponseBean accountDetailResponseBean);
    void onAccountDetailFailed(String msg);
}
