package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:平台咨询详情
 */

public class InfoDetailRespoonseBean {
    public String title; 	//资讯标题 	字符串(string)
    public String publishtime; 	//发布时间 	字符串(string)
    public String content; 	//内容 	字符串(string)

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
