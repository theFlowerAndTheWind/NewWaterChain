package com.shuzijieshui.www.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:
 */

public class FactoryDetailResponseBean {
    public WashFatoryDetail detail; 	//洗涤企业基本信息 	数组(array)
    public List<WashFatoryCageGory> service_lists; 	//洗涤企业所含服务项目列表 	数组(array)

    public WashFatoryDetail getDetail() {
        return detail;
    }

    public void setDetail(WashFatoryDetail detail) {
        this.detail = detail;
    }

    public List<WashFatoryCageGory> getService_lists() {
        return service_lists;
    }

    public void setService_lists(List<WashFatoryCageGory> service_lists) {
        this.service_lists = service_lists;
    }

    public static class WashFatoryDetail{
        int id;
        String logo;
        String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class WashFatoryCageGory{
        int id;
        String s_name;
        String img;
        int service_id;
        String description;


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

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
