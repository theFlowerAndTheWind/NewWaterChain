package com.quanminjieshui.waterchain.beans.request;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public class CheckUserPayReqBean {

    String token; 	//token 	字符串(string) 	是 	placeholder="非必填"
    String device_type; 	//设备类型 	字符串(string) 	是 	placeholder="非必填"
    int id; 	//商品id 	数字(number) 	是 	placeholder="非必填"
    int count; 	//兑换数量 	数字(number) 	是 	placeholder="非必填" 	活动类商品传1

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
