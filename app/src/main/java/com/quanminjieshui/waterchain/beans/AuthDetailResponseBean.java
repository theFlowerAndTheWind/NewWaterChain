package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class AuthDetailResponseBean {

    public String user_status;// 	认证状态 	字符串(string) 		待认证|待审核|已驳回|已通过
    public String name;// 	姓名|营业执照登记名称 	字符串(string)
    public String number;// 	证件号码|营业执照注册号 	字符串(string)

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
