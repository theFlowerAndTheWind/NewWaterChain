package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:支付宝支付返回数据
 */

public class CreateOrderResponseBean {
    private String oid;//oid 	订单id 	字符串(string) 	number|4
    private String orderStr;//创建订单返回的串   微信支付返回微信所需参数，支付宝支付返回一个串

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderStr() {
        return orderStr;
    }
}
