package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

import java.util.List;

/**
 *  │ {
 *     │     "code":1,
 *     │     "msg":"操作成功",
 *     │     "data":{
 *     │         "old_total":"147.64575",
 *     │         "action_type":"贡献",
 *     │         "uid":6,
 *     │         "avg_price":"0.00346",
 *     │         "deal_total":"0.51085",
 *     │         "fee":"0.73822T",
 *     │         "trade_detail":[
 *     │             {
 *     │                 "add_time":"2019-01-07 21:56:01",
 *     │                 "price":"0.00346",
 *     │                 "total":"147.64575",
 *     │                 "fee":"0.73822T"
 *     │             }
 *     │         ]
 *     │     }
 *     │ }
 */
public class TradeDetailResponseBean {

    private String old_total;
    private String action_type;
    private int uid;
    private String avg_price;
    private String deal_total;
    private String fee;
    private List<TradeDetailEntry>trade_detail;

    public String getOld_total() {
        return old_total;
    }

    public String getAction_type() {
        return action_type;
    }

    public int getUid() {
        return uid;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public String getDeal_total() {
        return deal_total;
    }

    public String getFee() {
        return fee;
    }

    public List<TradeDetailEntry> getTrade_detail() {
        return trade_detail;
    }

    public static class TradeDetailEntry{
        private String add_time;
        private String price;
        private String total;
        private String fee;

        public String getAdd_time() {
            return add_time;
        }

        public String getPrice() {
            return price;
        }

        public String getTotal() {
            return total;
        }

        public String getFee() {
            return fee;
        }
    }

}
