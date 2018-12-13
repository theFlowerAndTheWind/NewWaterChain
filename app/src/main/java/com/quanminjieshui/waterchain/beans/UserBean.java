package com.quanminjieshui.waterchain.beans;

/**
 * Created by WanghongHe on 2018/12/3 12:28.
 */

public class UserBean {

    private String name;
    private String password;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
