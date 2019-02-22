package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AccountDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.AccountDetailModel;
import com.shuzijieshui.www.waterchain.contract.view.AccountDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public class AccountDetailPresenter extends BasePresenter<AccountDetailViewImpl> {

    private AccountDetailModel accountDetailModel;

    public AccountDetailPresenter(){}

    public AccountDetailPresenter(AccountDetailModel accountDetailModel){
        this.accountDetailModel = accountDetailModel;
    }

    public void getAccountDetail(BaseActivity activity){
        if(accountDetailModel == null){
            accountDetailModel = new AccountDetailModel();
        }
        accountDetailModel.getAccountDetail(activity, new AccountDetailModel.AccountDetailCallBack() {
            @Override
            public void success(AccountDetailResponseBean accountDetailResponseBean) {
                if(mView!=null){
                    mView.onAccountDetailSuccess(accountDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onAccountDetailFailed(msg);
                }

            }
        });
    }
}
