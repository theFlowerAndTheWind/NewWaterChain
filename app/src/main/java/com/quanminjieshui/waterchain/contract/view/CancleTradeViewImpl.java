/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CancleTradeViewImpl
 * Author: sxt
 * Date: 2019/1/3 7:58 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.view
 * @ClassName: CancleTradeViewImpl
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/3 7:58 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/3 7:58 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface CancleTradeViewImpl extends IBaseViewImpl {
    void onCancleSuccess();
    void onCancleFailed(String msg);
}