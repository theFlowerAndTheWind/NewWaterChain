package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class TradeDetailResponseBean {

    public String action_type;// 	交易类型 	字符串(string) 		贡献|获得
    public String deal_total; 	//成交额 	字符串(string)
    public String avg_price; 	//成交均价 	字符串(string)
    public String old_total; 	//委托量 	字符串(string)
    public String fee; 	//手续费 	字符串(string)
    public String trade_detail; 	//交易列表 	数组(array)

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getDeal_total() {
        return deal_total;
    }

    public void setDeal_total(String deal_total) {
        this.deal_total = deal_total;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }

    public String getOld_total() {
        return old_total;
    }

    public void setOld_total(String old_total) {
        this.old_total = old_total;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTrade_detail() {
        return trade_detail;
    }

    public void setTrade_detail(String trade_detail) {
        this.trade_detail = trade_detail;
    }
}
