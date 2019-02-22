package com.shuzijieshui.www.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:交易中心折线图
 */

public class TradeLineResponseBean {
    private List<ChartDataEntity> data;
    private List<String> xasix;

    public List<ChartDataEntity> getData() {
        return data;
    }

    public List<String> getXasix() {
        return xasix;
    }

   public static class ChartDataEntity{
        private String price;
        private String tdate;

       public String getPrice() {
           return price;
       }

       public void setPrice(String price) {
           this.price = price;
       }

       public String getTdate() {
           return tdate;
       }

       public void setTdate(String tdate) {
           this.tdate = tdate;
       }
   }

}
