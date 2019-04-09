package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:我的资产
 *
 * update by sxt on 2019/4/8
 */

public class AccountDetailResponseBean {

//    private String jsl; 	//可用jsl 	字符串(string)
//    private String jsl_freeze; 	//冻结jsl 	字符串(string)
//    private String jsl_lock_view; 	//锁定jsl 	字符串(string)
//    private String jsl_gyj; 	//公益金jsl 	字符串(string)
//    private String ds; 	//可用节水指标 	字符串(string)
//    private String ds_freeze;// 	冻结节水指标 	字符串(string)
//
//    public String getJsl() {
//        return jsl;
//    }
//
//
//    public String getJsl_freeze() {
//        return jsl_freeze;
//    }
//
//
//    public String getJsl_lock_view() {
//        return jsl_lock_view;
//    }
//
//
//    public String getJsl_gyj() {
//        return jsl_gyj;
//    }
//
//
//    public String getDs() {
//        return ds;
//    }
//
//
//    public String getDs_freeze() {
//        return ds_freeze;
//    }

    private String jsl;
    private String jsl_all;//可用jsl
    private String jsl_freeze;//冻结jsl
    private String jsl_lock_view;
    private String is_investor;//用户是否为投资人
    private String stock;//可用虚拟股票
    private String stock_freeze;//冻结虚拟股票
    private String stock_lock_view;//锁定虚拟股票
    private String balance_v;//即将过期水方数量
    private String expire_time;//过期时间
    private String jsl_gyj;//有时限水方数量

    public String getJsl() {
        return jsl;
    }

    public String getJsl_all() {
        return jsl_all;
    }

    public String getJsl_freeze() {
        return jsl_freeze;
    }

    public String getJsl_lock_view() {
        return jsl_lock_view;
    }

    public String getIs_investor() {
        return is_investor;
    }

    public String getStock() {
        return stock;
    }

    public String getStock_freeze() {
        return stock_freeze;
    }

    public String getStock_lock_view() {
        return stock_lock_view;
    }

    public String getBalance_v() {
        return balance_v;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public String getJsl_gyj() {
        return jsl_gyj;
    }
}
