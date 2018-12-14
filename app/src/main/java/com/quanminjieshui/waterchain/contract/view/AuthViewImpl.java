package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.Map;

public interface AuthViewImpl extends IBaseViewImpl {
    void onEdtContentsLegal();

    void onEdtContentsIllegal(Map<String, Boolean> verify);

    void onCompanyAuthSuccess();

    void onCompanyAuthFailed(String msg);

    void onPersonalAuthSuccess();

    void onPersonalAuthFailed(String msg);
}
