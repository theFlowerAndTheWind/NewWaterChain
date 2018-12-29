package com.quanminjieshui.waterchain.beans.request;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:创建订单
 */

public class CreateOrderReqParams {

    int uid; 	//用户id 	数字(number) 	是 	placeholder="非必填"
    int fsid; 	//洗涤工厂服务项目id 	数字(number) 	是 	placeholder="非必填"
    String [] trade_detail; 	//洗涤类别信息 	数组(array) 	是 	placeholder="非必填" 	array( 0 => fscid_数量 )
    int express; 	//取件方式 	数字(number) 	是 	placeholder="非必填" 	1洗涤企业配送 2自取
    String contact_name; 	//联系人姓名 	字符串(string) 	是 	placeholder="非必填"
    String contact_tel; 	//联系人手机号 	字符串(string) 	是 	placeholder="非必填"
    String province; 	//省份 	字符串(string) 	是 	placeholder="非必填"
    String city; 	//城市 	字符串(string) 	是 	placeholder="非必填"
    String address; 	//详细地址 	字符串(string) 	是 	placeholder="非必填"
    int pay_cate; 	//支付类型 	数字(number) 	是 	placeholder="非必填" 	1全额支付 2组合支付
    String pickup_time; 	//配送时间 	字符串(string) 	否 	placeholder="非必填"

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFsid() {
        return fsid;
    }

    public void setFsid(int fsid) {
        this.fsid = fsid;
    }

    public String[] getTrade_detail() {
        return trade_detail;
    }

    public void setTrade_detail(String[] trade_detail) {
        this.trade_detail = trade_detail;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPay_cate() {
        return pay_cate;
    }

    public void setPay_cate(int pay_cate) {
        this.pay_cate = pay_cate;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }
}
