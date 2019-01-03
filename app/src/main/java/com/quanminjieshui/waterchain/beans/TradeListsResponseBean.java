package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class TradeListsResponseBean {

    public List<TradeEntity> lists;

    public List<TradeEntity> getLists() {
        return lists;
    }

    /**
     * │             {
     * │                 "id":7034,
     * │                 "add_time":"2019-01-03 19:08",
     * │                 "price":"0.00000",
     * │                 "old_total":"6.43998",
     * │                 "total":"6.43998",
     * │                 "status":"等待成交",
     * │                 "action_type":"贡献",
     * │                 "deal_total":"0.00000",
     * │                 "more":0,
     * │                 "show_cancel":1,
     * │                 "fee":"0.00000JSL",
     * │                 "price_avg":"0.00000"
     * │             },
     */
    public class TradeEntity {
        private int id;
        private String add_time;
        private String price;
        private String old_total;//委托量
        private String total;
        private String status;
        private String action_type;
        private String deal_total;//实际成交
        private int more;
        private int show_cancel;
        private String fee;
        private String price_avg;

        public int getId() {
            return id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public String getPrice() {
            return price;
        }

        public String getOld_total() {
            return old_total;
        }

        public String getTotal() {
            return total;
        }

        public String getStatus() {
            return status;
        }

        public String getAction_type() {
            return action_type;
        }

        public String getDeal_total() {
            return deal_total;
        }

        public int getMore() {
            return more;
        }

        public int getShow_cancel() {
            return show_cancel;
        }

        public String getFee() {
            return fee;
        }

        public String getPrice_avg() {
            return price_avg;
        }
    }
}
