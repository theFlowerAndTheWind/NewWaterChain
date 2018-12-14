package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class BaseBean {
    public String token = "登录Token";
    public String device_type = "android";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }
}
