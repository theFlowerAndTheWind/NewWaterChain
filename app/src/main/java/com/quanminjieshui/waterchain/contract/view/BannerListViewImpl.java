package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by WanghongHe on 2018/12/13 14:42.
 */

public interface BannerListViewImpl extends IBaseViewImpl {
    void onBannerListSuccess(List<Object> list);
    void onBannerListFailed(String msg);
}
