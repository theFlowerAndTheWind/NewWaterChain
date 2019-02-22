package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:洗涤商城
 */

public class Factory {

    int id;
    String f_name;
    String logo;
    String service_area;//服务范围
    String service_lists;//业务范围

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

    public String getService_area() {
        return service_area;
    }

    public void setService_area(String service_area) {
        this.service_area = service_area;
    }

    public String getService_lists() {
        return service_lists;
    }

    public void setService_lists(String service_lists) {
        this.service_lists = service_lists;
    }
}
