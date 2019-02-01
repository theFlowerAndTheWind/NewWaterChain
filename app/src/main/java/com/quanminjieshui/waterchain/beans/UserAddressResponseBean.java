package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2019/2/2.
 * Class Note:
 */

public class UserAddressResponseBean {
    String receiver; 	//联系人 	字符串(string)
    String tel; 	//联系电话 	字符串(string)
    String province; 	//省份 	字符串(string)
    String city; 	//城市 	字符串(string)
    String address; 	//详细地址 	字符串(string)
    String addr; 	//拼接后的地址 	字符串(string)

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
