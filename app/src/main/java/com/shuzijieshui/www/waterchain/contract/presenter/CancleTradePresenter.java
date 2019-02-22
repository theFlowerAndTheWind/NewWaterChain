/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CancleTradePresenter
 * Author: sxt
 * Date: 2019/1/3 8:10 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.CancleTradeModel;
import com.shuzijieshui.www.waterchain.contract.view.CancleTradeViewImpl;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.presenter
 * @ClassName: CancleTradePresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/3 8:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/3 8:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CancleTradePresenter extends BasePresenter<CancleTradeViewImpl> {
    private CancleTradeModel cancleTradeModel;

    public CancleTradePresenter() {
    }

    public CancleTradePresenter(CancleTradeModel cancleTradeModel) {
        this.cancleTradeModel = cancleTradeModel;
    }

    public void cancle(BaseActivity activity, int tid) {
        if (cancleTradeModel == null) {
            cancleTradeModel = new CancleTradeModel();
        }

        cancleTradeModel.cancle(activity, tid, new CancleTradeModel.CancleTradeCallBack() {


            @Override
            public void onCancleSuccess() {
                if (mView != null) {
                    mView.onCancleSuccess();
                }
            }

            @Override
            public void onCancleFailed(String msg) {
                if (mView != null) {
                    mView.onCancleFailed(msg);
                }
            }
        });
    }
}