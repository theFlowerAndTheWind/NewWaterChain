/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegisterResponseBean
 * Author: sxt
 * Date: 2018/12/8 5:05 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.beans;

/**
 * @ClassName: RegisterResponseBean
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/8 5:05 PM
 */
public class RegisterResponseBean {

    private int respCode;

    public RegisterResponseBean(int respCode) {
        this.respCode = respCode;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }
}
