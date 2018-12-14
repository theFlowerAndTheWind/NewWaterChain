package com.quanminjieshui.waterchain.beans.request;

import com.quanminjieshui.waterchain.beans.BaseBean;

public class PersonalAuthReqParams extends BaseBean {
    private String nationality;
    private String province;
    private String city;
    private String user_name;
    private String id_no;
    private String id_img_a;
    private String id_img_b;

    public PersonalAuthReqParams(){}

    public PersonalAuthReqParams(String nationality, String province, String city,
                                 String user_name,
                                 String id_no, String id_img_a, String id_img_b) {
        this.nationality = nationality;
        this.province = province;
        this.city = city;
        this.user_name = user_name;
        this.id_no = id_no;
        this.id_img_a = id_img_a;
        this.id_img_b = id_img_b;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getId_img_a() {
        return id_img_a;
    }

    public void setId_img_a(String id_img_a) {
        this.id_img_a = id_img_a;
    }

    public String getId_img_b() {
        return id_img_b;
    }

    public void setId_img_b(String id_img_b) {
        this.id_img_b = id_img_b;
    }
}
