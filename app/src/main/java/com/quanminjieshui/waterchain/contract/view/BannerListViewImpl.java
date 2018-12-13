package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.BannerListResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/13 14:42.
 */

public interface BannerListViewImpl extends IBaseViewImpl {
    void onBannerListSuccess(BannerListResponseBean bannerListResponseBean);
    void onBannerListFailed(String msg);
}
