package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class AuthDetailResponseBean {

    private String user_status;// 	认证状态 	字符串(string) 		待认证|待审核|已驳回|已通过
    private String user_name;// 	姓名|营业执照登记名称 	字符串(string)
    private String id_no;// 	证件号码（18）|营业执照注册号（15） 	字符串(string)
    private String company_name;
    private String company_license_no;
    private int user_type;//0未认证 1个人 2企业

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_license_no() {
        return company_license_no;
    }

    public void setCompany_license_no(String company_license_no) {
        this.company_license_no = company_license_no;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
