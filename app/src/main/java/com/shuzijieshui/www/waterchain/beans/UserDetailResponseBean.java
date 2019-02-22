package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class UserDetailResponseBean {
    private String avatar; 	//头像
    private String user_login; 	//用户账号
    private String create_time; 	//注册时间
    private String user_type; //账号类型
    private int user_status;//是否注册
    private int is_block;//?? 用户账号被限制？？？

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getIs_block() {
        return is_block;
    }

    public void setIs_block(int is_block) {
        this.is_block = is_block;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
