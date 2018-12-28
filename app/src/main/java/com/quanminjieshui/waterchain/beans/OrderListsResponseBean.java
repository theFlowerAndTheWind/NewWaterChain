package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */
//    │ {
//    │     "code":1,
//    │     "msg":"操作成功",
//    │     "data":{
//    │         "lists":[
//    │             {
//    │                 "fid":1,
//    │                 "f_name":"洗涤工厂",
//    │                 "s_name":"餐具消毒类",
//    │                 "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
//    │                 "total_price":"2.00000",
//    │                 "status":"已取消",
//    │                 "id":48
//    │             }
//    │         ]
//    │     }
//    │ }
public class OrderListsResponseBean {

    public List<OrderListEntity> lists;

    public List<OrderListEntity> getLists() {
        return lists;
    }

    public void setLists(List<OrderListEntity> lists) {
        this.lists = lists;
    }

    public static class OrderListEntity {
        private int fid;
        private String f_name;
        private String s_name;
        private String img;
        private String total_price;
        private String status;
        private int id;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}