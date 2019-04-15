package com.shuzijieshui.www.waterchain.beans;

public class SysMsg {
    private String event_text;//消息内容
    private String add_time;//创建时间
    private int id;
    private int type;
    private String type_name;

    public String getEvent_text() {
        return event_text;
    }

    public String getAdd_time() {
        return add_time;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getType_name() {
        return type_name;
    }
}
