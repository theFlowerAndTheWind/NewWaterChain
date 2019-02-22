package com.shuzijieshui.www.waterchain.beans.request;

import com.shuzijieshui.www.waterchain.beans.BaseBean;

/**
 * sxt
 * 企业认证参数
 */

public class CompanyAuthReqParams extends BaseBean {
    private String province;
    private String city;
    private String company_name;
    private String company_license_no;
    private String company_license_img;
    private String company_boss_name;
    private String company_boss_tel;
    private String id_img_a;
    private String id_img_b;
    private String company_other_name;
    private String company_other_tel;

    public CompanyAuthReqParams() {
    }

    public CompanyAuthReqParams(String province, String city,
                                String company_name, String company_license_no, String company_license_img,
                                String company_boss_name, String company_boss_tel,
                                String id_img_a, String id_img_b,
                                String company_other_name, String company_other_tel) {
        this.province = province;
        this.city = city;
        this.company_name = company_name;
        this.company_license_no = company_license_no;
        this.company_license_img = company_license_img;
        this.company_boss_name = company_boss_name;
        this.company_boss_tel = company_boss_tel;
        this.id_img_a = id_img_a;
        this.id_img_b = id_img_b;
        this.company_other_name = company_other_name;
        this.company_other_tel = company_other_tel;
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

    public String getCompany_license_img() {
        return company_license_img;
    }

    public void setCompany_license_img(String company_license_img) {
        this.company_license_img = company_license_img;
    }

    public String getCompany_boss_name() {
        return company_boss_name;
    }

    public void setCompany_boss_name(String company_boss_name) {
        this.company_boss_name = company_boss_name;
    }

    public String getCompany_boss_tel() {
        return company_boss_tel;
    }

    public void setCompany_boss_tel(String company_boss_tel) {
        this.company_boss_tel = company_boss_tel;
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

    public String getCompany_other_name() {
        return company_other_name;
    }

    public void setCompany_other_name(String company_other_name) {
        this.company_other_name = company_other_name;
    }

    public String getCompany_other_tel() {
        return company_other_tel;
    }

    public void setCompany_other_tel(String company_other_tel) {
        this.company_other_tel = company_other_tel;
    }
}
