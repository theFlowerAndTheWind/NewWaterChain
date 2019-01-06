/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TradeLineViewImpl
 * Author: sxt
 * Date: 2019/1/4 11:31 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.view
 * @ClassName: TradeLineViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/4 11:31 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 11:31 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TradeLineViewImpl extends IBaseViewImpl {

    void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean);
    void onTradeLineFailed(String msg);
}