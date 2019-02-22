package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by WanghongHe on 2018/12/13 14:42.
 */

public interface BannerListViewImpl extends IBaseViewImpl {
    void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list);
    void onBannerListFailed(String msg);
}
