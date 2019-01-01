package com.quanminjieshui.waterchain.event;

/**
 * created by sxt
 *
 * MainActivity订阅该事件
 * 接收到事件后根据title选择显示fragment
 */
public class SelectFragmentEvent {
    public static String[]titles=new String[]{"首页","洗涤","交易","发现","我的"};
    private String title=titles[0];//选择fragment

    public SelectFragmentEvent(String title) {
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
