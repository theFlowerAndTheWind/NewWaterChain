package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:获取支付总金额
 */

public class TotalPriceResponseBean {
    public String total_price; 	//总金额 	字符串(string)
    public List<WashType> lists; 	//洗涤类型信息 	数组(array) 		array( 0=>array( 'c_name'=>类型名称, 'unit_price'=>单价, 'total'=>数量) )
    public String pay_price; 	//需支付人民币 	字符串(string)
    public String pay_jsl; 	//需支付jsl 	字符串(string)
    public int can_pay; 	//是否可以支付 	字符串(string) 		当支付类型为组合支付时，判断用户已有jsl是否足以支付。1是 0否
    private int save_count;

    public int getSave_count() {
        return save_count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<WashType> getLists() {
        return lists;
    }

    public void setLists(List<WashType> lists) {
        this.lists = lists;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public String getPay_jsl() {
        return pay_jsl;
    }

    public void setPay_jsl(String pay_jsl) {
        this.pay_jsl = pay_jsl;
    }

    public int getCan_pay() {
        return can_pay;
    }

    public void setCan_pay(int can_pay) {
        this.can_pay = can_pay;
    }

    public static class WashType{
        private String id;
        private String unit_price;
        private String c_name;
        private String total;
        private String price;

        public String getId() {
            return id;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public String getC_name() {
            return c_name;
        }

        public String getTotal() {
            return total;
        }

        public String getPrice() {
            return price;
        }
    }
}
