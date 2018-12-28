/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.view
 * @ClassName: TradeListsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.view
 * @ClassName: TradeListsViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TradeListsViewImpl extends IBaseViewImpl {
    void onGetTradeListsSuccess(Object o);//参数类型待确定
    void onGetTradeListsFailed(String msg);
}
