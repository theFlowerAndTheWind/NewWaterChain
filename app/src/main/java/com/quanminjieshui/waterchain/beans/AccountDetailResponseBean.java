package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:我的资产
 */

public class AccountDetailResponseBean {

    private String jsl; 	//可用jsl 	字符串(string)
    private String jsl_freeze; 	//冻结jsl 	字符串(string)
    private String jsl_lock_view; 	//锁定jsl 	字符串(string)
    private String jsl_gyj; 	//公益金jsl 	字符串(string)
    private String ds; 	//可用节水指标 	字符串(string)
    private String ds_freeze;// 	冻结节水指标 	字符串(string)

    public String getJsl() {
        return jsl;
    }


    public String getJsl_freeze() {
        return jsl_freeze;
    }


    public String getJsl_lock_view() {
        return jsl_lock_view;
    }


    public String getJsl_gyj() {
        return jsl_gyj;
    }


    public String getDs() {
        return ds;
    }


    public String getDs_freeze() {
        return ds_freeze;
    }

}
