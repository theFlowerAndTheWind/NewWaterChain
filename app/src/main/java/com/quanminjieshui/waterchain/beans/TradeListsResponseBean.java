package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class TradeListsResponseBean {

    public List<TradeListEntity> lists;

    public List<TradeListEntity> getLists() {
        return lists;
    }

    public void setLists(List<TradeListEntity> lists) {
        this.lists = lists;
    }

    public class TradeListEntity {
        private String action_type;//交易类型     贡献|获得
        private String deal_total;//已成交量
        private String avg_price;//成交均价（单价）
        private String old_total;//委托量
        private String fee;//手续费
        private String shiji;//实际成交
        private List<TradeDetailResponseBean> details;

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

        public String getShiji() {
            return shiji;
        }

        public void setShiji(String shiji) {
            this.shiji = shiji;
        }

        public List<TradeDetailResponseBean> getDetails() {
            return details;
        }

        public void setDetails(List<TradeDetailResponseBean> details) {
            this.details = details;
        }
    }
}
