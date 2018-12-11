/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AuthRequestBean
 * Author: sxt
 * Date: 2018/12/10 12:44 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.beans;

import android.graphics.Bitmap;

/**
 * @ClassName: AuthRequestBean
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/10 12:44 PM
 */
public class AuthRequestBean {
    //用户id
    private String uid;
    /**
     * 用户身份
     * true 企业
     * false 个人
     */
    private boolean userStatus=true;
    //国籍  只针对个人用户
    private String nationality;
    private String province;
    private String city;
    private String company_name;
    private String company_license_no;
    private String company_boss_name;
    private String company_license_img;
    //身份证正面  企业、个人共用
    private String id_img_a;
    private String id_img_b;
    private String company_other_name;
    private String company_other_tel;
    private String user_name;
    private String id_no;

    public AuthRequestBean(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
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

    public String getCompany_boss_name() {
        return company_boss_name;
    }

    public void setCompany_boss_name(String company_boss_name) {
        this.company_boss_name = company_boss_name;
    }

    public String getCompany_license_img() {
        return company_license_img;
    }

    public void setCompany_license_img(String company_license_img) {
        this.company_license_img = company_license_img;
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
}
