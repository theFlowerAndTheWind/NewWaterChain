package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:交易中心
 */

public class TradeCenterResponseBean {
    private String cur_price;//当前市价        float|2-4|5	1 JSL ≈ 3 节水指标
    private String price_limit;//涨跌幅        float|2-4|5	涨跌幅
    private String price_limit_color;//涨跌幅颜色       红涨绿跌 red|green
    private String day_vol;//24小时成交量      float|2-4|5	24小时成交量 非交易日返回"--"
    private String trade_status;//是否交易日      交易中|休息
    private TradeListEntry trade_list;//最新的十个报单	对象(object)		买5卖5
    private List<TradeDetailEntity> trade_detail_list;//最近的十单成交(仅PC端显示，移动端不显示！！！)	数组(array)		最近的十单成交
    private List<UserCurrentTradeEntity> user_cur_trade;//当前委托	数组(array)		当前委托
    private List<Object> user_history_trade;//历史委托
    private UserAccountEntity user_account;//用户账户情况	对象(object)		用户账户
    private String line_chart;
    private int is_login;//登录状态	数字(number)	number|1|10	1登录 0未登录

    public String getCur_price() {
        return cur_price;
    }

    public void setCur_price(String cur_price) {
        this.cur_price = cur_price;
    }

    public String getPrice_limit() {
        return price_limit;
    }

    public void setPrice_limit(String price_limit) {
        this.price_limit = price_limit;
    }

    public String getPrice_limit_color() {
        return price_limit_color;
    }

    public void setPrice_limit_color(String price_limit_color) {
        this.price_limit_color = price_limit_color;
    }

    public String getDay_vol() {
        return day_vol;
    }

    public void setDay_vol(String day_vol) {
        this.day_vol = day_vol;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public TradeListEntry getTrade_list() {
        return trade_list;
    }

    public void setTrade_list(TradeListEntry trade_list) {
        this.trade_list = trade_list;
    }

    public List<TradeDetailEntity> getTrade_detail_list() {
        return trade_detail_list;
    }

    public void setTrade_detail_list(List<TradeDetailEntity> trade_detail_list) {
        this.trade_detail_list = trade_detail_list;
    }

    public List<UserCurrentTradeEntity> getUser_cur_trade() {
        return user_cur_trade;
    }

    public void setUser_cur_trade(List<UserCurrentTradeEntity> user_cur_trade) {
        this.user_cur_trade = user_cur_trade;
    }

    public List<Object> getUser_history_trade() {
        return user_history_trade;
    }

    public void setUser_history_trade(List<Object> user_history_trade) {
        this.user_history_trade = user_history_trade;
    }

    public UserAccountEntity getUser_account() {
        return user_account;
    }

    public void setUser_account(UserAccountEntity user_account) {
        this.user_account = user_account;
    }

    public String getLine_chart() {
        return line_chart;
    }

    public void setLine_chart(String line_chart) {
        this.line_chart = line_chart;
    }

    public int getIs_login() {
        return is_login;
    }

    public void setIs_login(int is_login) {
        this.is_login = is_login;
    }


    public class TradeListEntry {
        private List<BuySellEntity> buy;//5份买单报价	数组(array)		5份买单报价
        private List<BuySellEntity> sell;//5份卖单报价	数组(array)		5份卖单报价

        public List<BuySellEntity> getBuy() {
            return buy;
        }

        public void setBuy(List<BuySellEntity> buy) {
            this.buy = buy;
        }

        public List<BuySellEntity> getSell() {
            return sell;
        }

        public void setSell(List<BuySellEntity> sell) {
            this.sell = sell;
        }
    }

    /**
     * buy  sell共用，根据name前两个字区分
     */
    public static class BuySellEntity extends Object{
        private String total;//贡献的数量	浮点型	float|2-4|5	贡献的数量
        private String price;//贡献的价格	浮点型(float)	float|2-4|5	贡献的价格
        private String name;//名称	字符串(string)		贡献1

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class TradeDetailEntity {
        private int id;
        private String add_time;//成交时间	字符串(string)		成交时间
        private int action_type;//成交方向			1|2 获取|贡献
        private String price;//成交方向	字符串(string)		1|2 获取|贡献
        private String total;//成交方向	字符串(string)		1|2 获取|贡献
        private String type_name;//成交方向	字符串(string)		1|2 获取|贡献

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getAction_type() {
            return action_type;
        }

        public void setAction_type(int action_type) {
            this.action_type = action_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }

    public class UserCurrentTradeEntity extends Object {
        //以下字段为文档内容，实际返回数据并无提示
        private String id;//委托单id
        private String trading_type;//	报单类型	数字(number)	number|1|12	1限价单 2市价单
        private String action_type;//	报单方向	字符串(string)	number|1|12	1 获取（卖单） 2 贡献（买单）
        private String price;//	价格	浮点型(float)	float|2-4|5	报单价格， 市价单 --表示
        private String old_total;//	报单数量	浮点型(float)	float|2-4|5	报单数量
        private String total;//	剩余可成交数量	浮点型(float)	float|2-4|5	剩余可成交数量
        private String status;//	报单状态	字符串(string)		部分成交|全部成交|已撤销
        private String add_time;//	报单时间	字符串(string)		报单时间
        private String avg_price;//	成交均价	浮点型(float)	float|2-4|5	成交均价
        private String fee;//	手续费	浮点型(float)	float|2-4|5	action_type1获取 2贡献 获取的时候 手续费单位为ds 贡献的时候 手续费单位为jsl

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTrading_type() {
            return trading_type;
        }

        public void setTrading_type(String trading_type) {
            this.trading_type = trading_type;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOld_total() {
            return old_total;
        }

        public void setOld_total(String old_total) {
            this.old_total = old_total;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAvg_price() {
            return avg_price;
        }

        public void setAvg_price(String avg_price) {
            this.avg_price = avg_price;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }
    }

    public class UserAccountEntity {
        private String jsl;//账户jsl数量		float|2-4|5	账户jsl数量
        private String ds;//账户ds情况		float|2-4|5	账户ds情况

        public String getJsl() {
            return jsl;
        }

        public void setJsl(String jsl) {
            this.jsl = jsl;
        }

        public String getDs() {
            return ds;
        }

        public void setDs(String ds) {
            this.ds = ds;
        }
    }
}
