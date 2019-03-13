package com.shuzijieshui.www.waterchain.beans;

/**
 * 微信支付返回数据
 */
public class CreateOrderResponseBean1 {
    private String oid;//oid 	订单id 	字符串(string) 	number|4
    private WechatBean orderStr;//创建订单返回的串   微信支付返回微信所需参数，支付宝支付返回一个串

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public WechatBean getOrderStr() {
        return orderStr;
    }
}
