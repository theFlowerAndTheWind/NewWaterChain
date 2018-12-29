package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:洗涤商城
 */

public class FactoryListResponseBean {

    int id;
    String f_name;
    String logo;
    String service_scope;//服务范围 TODO 接口未返回
    String bussiness_scope;//业务范围 TODO 接口未返回

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getService_scope() {
        return service_scope;
    }

    public void setService_scope(String service_scope) {
        this.service_scope = service_scope;
    }

    public String getBussiness_scope() {
        return bussiness_scope;
    }

    public void setBussiness_scope(String bussiness_scope) {
        this.bussiness_scope = bussiness_scope;
    }
}
