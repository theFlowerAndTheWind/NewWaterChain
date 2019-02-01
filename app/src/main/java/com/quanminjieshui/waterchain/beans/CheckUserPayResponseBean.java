package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public class CheckUserPayResponseBean {

    String goods_pay; 	//共需支付水方数量 	字符串(string)
    String user_gyj; 	//用户持有公益金 	字符串(string)
    String pay_gyj; 	//需支付公益金 	字符串(string)
    String user_jsl; 	//用户持有水方 	字符串(string)
    String pay_jsl; 	//需支付水方 	字符串(string)
    String can_order; 	//是否可以支付 	字符串(string) 		1（可以支付）|0（不足支付）

    public String getGoods_pay() {
        return goods_pay;
    }

    public void setGoods_pay(String goods_pay) {
        this.goods_pay = goods_pay;
    }

    public String getUser_gyj() {
        return user_gyj;
    }

    public void setUser_gyj(String user_gyj) {
        this.user_gyj = user_gyj;
    }

    public String getPay_gyj() {
        return pay_gyj;
    }

    public void setPay_gyj(String pay_gyj) {
        this.pay_gyj = pay_gyj;
    }

    public String getUser_jsl() {
        return user_jsl;
    }

    public void setUser_jsl(String user_jsl) {
        this.user_jsl = user_jsl;
    }

    public String getPay_jsl() {
        return pay_jsl;
    }

    public void setPay_jsl(String pay_jsl) {
        this.pay_jsl = pay_jsl;
    }

    public String getCan_order() {
        return can_order;
    }

    public void setCan_order(String can_order) {
        this.can_order = can_order;
    }
}
