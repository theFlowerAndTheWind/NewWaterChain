package com.shuzijieshui.www.waterchain.beans;

public class TotalPriceRes {
//    *     │ {
//     *     │     "code":1,
//     *     │     "msg":"操作成功",
//     *     │     "data":{
//     *     │         "total_price":"11.00",
//     *     │         "user_jsl":"1243",
//     *     │         "max_use_jsl":"6",
//     *     │         "max_money":"0.55",
//     *     │         "scale":"0.09000",
//     *     │         "true_max_use_jsl":"6"
//                    *     │     }
//     *     │ }
    private String total_price;//订单总金额
    private String user_jsl;//用户拥有水方数量
    private String max_use_jsl;//理论用户最多可用水方
    private String max_money;//理论最多可抵扣金额
    private String scale;//水方与RMB兑换比例（1 水方 = scale RMB）
    private String true_max_use_jsl;//实际用户可用最多水方

    public String getTotal_price() {
        return total_price;
    }

    public String getUser_jsl() {
        return user_jsl;
    }

    public String getMax_use_jsl() {
        return max_use_jsl;
    }

    public String getMax_money() {
        return max_money;
    }

    public String getScale() {
        return scale;
    }

    public String getTrue_max_use_jsl() {
        return true_max_use_jsl;
    }






    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public void setUser_jsl(String user_jsl) {
        this.user_jsl = user_jsl;
    }

    public void setMax_use_jsl(String max_use_jsl) {
        this.max_use_jsl = max_use_jsl;
    }

    public void setMax_money(String max_money) {
        this.max_money = max_money;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public void setTrue_max_use_jsl(String true_max_use_jsl) {
        this.true_max_use_jsl = true_max_use_jsl;
    }
}
