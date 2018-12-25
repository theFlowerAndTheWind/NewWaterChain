package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:
 */

public class FactoryServiceResponseBean {
    public WashFatoryDetail detail; 	//洗涤企业项目基本信息 	数组(array)
    public List<WashFatoryCageGory> cate_lists; 	//洗涤企业项目所含分类列表 	数组(array)

    public WashFatoryDetail getDetail() {
        return detail;
    }

    public void setDetail(WashFatoryDetail detail) {
        this.detail = detail;
    }

    public List<WashFatoryCageGory> getCate_lists() {
        return cate_lists;
    }

    public void setCate_lists(List<WashFatoryCageGory> cate_lists) {
        this.cate_lists = cate_lists;
    }

    public static class WashFatoryDetail{
        int id;
        int service_id;
        String img;
        String s_name;
        String description;
        int factory_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFactory_id() {
            return factory_id;
        }

        public void setFactory_id(int factory_id) {
            this.factory_id = factory_id;
        }
    }

    public static class WashFatoryCageGory{
        String c_name;
        String unit_price;
        String piece;
        String standard;
        int fscid;

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getPiece() {
            return piece;
        }

        public void setPiece(String piece) {
            this.piece = piece;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public int getFscid() {
            return fscid;
        }

        public void setFscid(int fscid) {
            this.fscid = fscid;
        }
    }
}
