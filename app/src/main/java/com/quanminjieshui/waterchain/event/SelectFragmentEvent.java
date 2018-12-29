package com.quanminjieshui.waterchain.event;

public class SelectFragmentEvent {
    public static String[]titles=new String[]{"首页","洗涤","交易","发现","我的"};
    private String title=titles[0];

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SelectFragmentEvent(String title) {
        this.title = title;
    }
}
