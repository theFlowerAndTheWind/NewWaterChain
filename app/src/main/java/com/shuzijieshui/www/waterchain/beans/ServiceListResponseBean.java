package com.shuzijieshui.www.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListResponseBean {

    public List<ServiceListEntity> lists;

    public List<ServiceListEntity> getLists() {
        return lists;
    }

    public void setLists(List<ServiceListEntity> lists) {
        this.lists = lists;
    }

    public class ServiceListEntity {
        int id;
        String s_name;
        String img;
        String intro;
        int show;



        public int getId() {
            return id;
        }

        public String getS_name() {
            return s_name;
        }

        public String getImg() {
            return img;
        }

        public String getIntro() {
            return intro;
        }

        public int getShow() {
            return show;
        }
    }

}
