package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:平台咨讯详情
 */

public class InfoDetailRespoonseBean {
    public int id; 	//资讯id 	字符串(string)
    public String title; 	//标题 	字符串(string)
    public String img; 	//图片 	字符串(string)
    public String publishtime; 	//发布时间 	字符串(string)
    public String content; 	//内容 	字符串(string)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
