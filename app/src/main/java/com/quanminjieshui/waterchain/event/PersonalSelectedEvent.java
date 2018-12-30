package com.quanminjieshui.waterchain.event;

public class PersonalSelectedEvent {
    private String user_login;

    public PersonalSelectedEvent(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }
}
