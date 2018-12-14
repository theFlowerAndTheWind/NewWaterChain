package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:交易中心折线图
 */

public class TradeLineResponseBean {
    public String tdate; 	//产生价格的时间 	字符串(string) 		2018-12-03 10:01:00
    public Float price; 	//报价 	浮点型(float) 	float|2-4|5

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
