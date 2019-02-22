/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegisterResponseBean
 * Author: sxt
 * Date: 2018/12/8 5:05 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.shuzijieshui.www.waterchain.beans;

/**
 * @ClassName: RegisterResponseBean
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/8 5:05 PM
 */
public class RegisterResponseBean {
    private String uid;
    private String user_login;
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
