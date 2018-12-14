package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:交易中心
 */

public class TradeCenterResponseBean {
    private Float cur_price; 	//当前市价 	浮点型(float) 	float|2-4|5 	1 JSL ≈ 3 节水指标
    private Float price_limit; 	//涨跌幅 	浮点型(float) 	float|2-4|5 	涨跌幅
    private String price_limit_color; 	//涨跌幅颜色 	字符串(string) 	string 	红涨绿跌 red|green
    private String day_vol; 	//24小时成交量 	字符串(string) 	float|2-4|5 	24小时成交量 非交易日返回"--"
    private String trade_status; 	//是否交易日 	字符串(string) 		交易中|休息
    private String is_login; 	//登录状态 	数字(number) 	number|1|10 	1登录 0未登录
    List<TradeListEntity> trade_list; 	//最新的十个报单 	对象(object) 		买5卖5
    private TradeDetailList trade_detail_list;
    private UserCurTrade user_cur_trade;
    private UsetAccount user_account;

    public Float getCur_price() {
        return cur_price;
    }

    public void setCur_price(Float cur_price) {
        this.cur_price = cur_price;
    }

    public Float getPrice_limit() {
        return price_limit;
    }

    public void setPrice_limit(Float price_limit) {
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

    public String getIs_login() {
        return is_login;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public List<TradeListEntity> getTrade_list() {
        return trade_list;
    }

    public void setTrade_list(List<TradeListEntity> trade_list) {
        this.trade_list = trade_list;
    }

    public TradeDetailList getTrade_detail_list() {
        return trade_detail_list;
    }

    public void setTrade_detail_list(TradeDetailList trade_detail_list) {
        this.trade_detail_list = trade_detail_list;
    }

    public UserCurTrade getUser_cur_trade() {
        return user_cur_trade;
    }

    public void setUser_cur_trade(UserCurTrade user_cur_trade) {
        this.user_cur_trade = user_cur_trade;
    }

    public UsetAccount getUser_account() {
        return user_account;
    }

    public void setUser_account(UsetAccount user_account) {
        this.user_account = user_account;
    }

    public static class TradeListEntity{
        private TradeBuy buy; //buy 	5份买单报价 	数组(array) 		5份买单报价
        private TradeSell sell; //sell 	5份卖单报价 	数组(array) 		5份卖单报价

        public TradeBuy getBuy() {
            return buy;
        }

        public void setBuy(TradeBuy buy) {
            this.buy = buy;
        }

        public TradeSell getSell() {
            return sell;
        }

        public void setSell(TradeSell sell) {
            this.sell = sell;
        }
    }

    public static class TradeBuy{
        private Float total; 	//贡献的数量 	浮点型(float) 	float|2-4|5 	贡献的数量
        private Float price; 	//贡献的价格 	浮点型(float) 	float|2-4|5 	贡献的价格
        private String name; 	//名称 	字符串(string) 		贡献1

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TradeSell{
        private Float total; 	//数量 	浮点型(float) 	float|2-4|5 	同买单结构一样
        private Float price; 	//价格 	浮点型(float) 	float|2-4|5 	价格
        private String name; 	//名称 	字符串(string) 		获取1

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TradeDetailList{
        private String add_time; 	//成交时间 	字符串(string) 		成交时间
        private String action_type; 	//成交方向 	字符串(string) 		1|2 获取|贡献
        private Float price;  //价格 	浮点型(float) 	float|2-4|5 	价格
        private Float total; 	//数量 	浮点型(float) 	float|2-4|5 	数量
        private String type_name; 	//成交方向text 	字符串(string) 		获取|贡献

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }

    public static class UserCurTrade{
        private int id; 	//委托单id 	数字(number) 	number|2-4 	委托单id
        private int trading_type; 	//报单类型 	数字(number) 	number|1|12 	1限价单 2市价单
        private String action_type; 	//报单方向 	字符串(string) 	number|1|12 	1 获取（卖单） 2 贡献（买单）
        private Float price; 	//价格 	浮点型(float) 	float|2-4|5 	报单价格， 市价单 --表示
        private Float old_total; 	//报单数量 	浮点型(float) 	float|2-4|5 	报单数量
        private Float total; 	//剩余可成交数量 	浮点型(float) 	float|2-4|5 	剩余可成交数量
        private String status; 	//报单状态 	字符串(string) 		部分成交|全部成交|已撤销
        private String add_time; 	//报单时间 	字符串(string) 		报单时间
        private Float avg_price; 	//成交均价 	浮点型(float) 	float|2-4|5 	成交均价
        private Float fee; 	//手续费 	浮点型(float) 	float|2-4|5 	action_type1获取 2贡献 获取的时候 手续费单位为ds 贡献的时候 手续费单位为jsl

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTrading_type() {
            return trading_type;
        }

        public void setTrading_type(int trading_type) {
            this.trading_type = trading_type;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getOld_total() {
            return old_total;
        }

        public void setOld_total(Float old_total) {
            this.old_total = old_total;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
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

        public Float getAvg_price() {
            return avg_price;
        }

        public void setAvg_price(Float avg_price) {
            this.avg_price = avg_price;
        }

        public Float getFee() {
            return fee;
        }

        public void setFee(Float fee) {
            this.fee = fee;
        }
    }

    public static class UsetAccount{
        private Float jsl; 	//账户jsl数量 	浮点型(float) 	float|2-4|5 	账户jsl数量
        private Float ds; 	//账户ds情况 	浮点型(float) 	float|2-4|5 	账户ds情况

        public Float getJsl() {
            return jsl;
        }

        public void setJsl(Float jsl) {
            this.jsl = jsl;
        }

        public Float getDs() {
            return ds;
        }

        public void setDs(Float ds) {
            this.ds = ds;
        }
    }


}
