package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:我的资产
 */

public class AccountDetailResponseBean {

    public String jsl; 	//可用jsl 	字符串(string)
    public String jsl_freeze; 	//冻结jsl 	字符串(string)
    public String jsl_lock_view; 	//锁定jsl 	字符串(string)
    public String jsl_gyj; 	//公益金jsl 	字符串(string)
    public String ds; 	//可用节水指标 	字符串(string)
    public String ds_freeze;// 	冻结节水指标 	字符串(string)

    public String getJsl() {
        return jsl;
    }

    public void setJsl(String jsl) {
        this.jsl = jsl;
    }

    public String getJsl_freeze() {
        return jsl_freeze;
    }

    public void setJsl_freeze(String jsl_freeze) {
        this.jsl_freeze = jsl_freeze;
    }

    public String getJsl_lock_view() {
        return jsl_lock_view;
    }

    public void setJsl_lock_view(String jsl_lock_view) {
        this.jsl_lock_view = jsl_lock_view;
    }

    public String getJsl_gyj() {
        return jsl_gyj;
    }

    public void setJsl_gyj(String jsl_gyj) {
        this.jsl_gyj = jsl_gyj;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getDs_freeze() {
        return ds_freeze;
    }

    public void setDs_freeze(String ds_freeze) {
        this.ds_freeze = ds_freeze;
    }
}
