package com.shuzijieshui.www.waterchain.event;

import com.shuzijieshui.www.waterchain.utils.Constants;

/**
 * created by sxt
 *
 * MainActivity订阅该事件
 * 接收到事件后根据title选择显示fragment
 */
public class SelectFragmentEvent {
    private String title= Constants.TAB_TITLE[0];//选择fragment

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
