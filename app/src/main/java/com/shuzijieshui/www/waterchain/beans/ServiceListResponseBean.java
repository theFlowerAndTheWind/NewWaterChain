package com.shuzijieshui.www.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListResponseBean {

    public List<serviceListEntity> lists;

    public List<serviceListEntity> getLists() {
        return lists;
    }

    public void setLists(List<serviceListEntity> lists) {
        this.lists = lists;
    }

    public class serviceListEntity{
        int id;
        String s_name;
        String img;
        String s_desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getS_desc() {
            return s_desc;
        }

        public void setS_desc(String s_desc) {
            this.s_desc = s_desc;
        }
    }

}
