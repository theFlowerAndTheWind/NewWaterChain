/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SmsResponseBean
 * Author: sxt
 * Date: 2018/12/8 3:05 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.beans;

/**
 * @ClassName: SmsResponseBean
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/8 3:05 PM
 */
public class SmsResponseBean {

    private int respCode;

    public SmsResponseBean(int respCode) {
        this.respCode = respCode;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }


    @Override
    public String toString() {
        return new StringBuilder().append("SmsResponseBean{")
                .append("respCode='").append(respCode).append("\'")
                .append("}").toString();
    }
}
