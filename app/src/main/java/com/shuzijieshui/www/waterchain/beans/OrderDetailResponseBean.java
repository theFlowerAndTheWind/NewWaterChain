package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

import java.util.List;

/**
 * 订单详情
 *     │ {
 *     │     "code":1,
 *     │     "msg":"操作成功",
 *     │     "data":{
 *     │         "f_name":"洗涤工厂",
 *     │         "fid":1,
 *     │         "s_name":"餐具消毒类",
 *     │         "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
 *     │         "total_price":"2.00000",
 *     │         "service_cate":[
 *     │             {
 *     │                 "c_name":"方巾",
 *     │                 "total":"1",
 *     │                 "price":"2.00"
 *     │             }
 *     │         ],
 *     │         "order_sn":"2018122757979754",
 *     │         "status":"已取消",
 *     │         "createtime":"2018-12-27 21:47:37",
 *     │         "pay_cate":"全额支付",
 *     │         "pay_type":"支付宝支付",
 *     │         "pay_price":"2.00000",
 *     │         "pay_jsl":"0.00000",
 *     │         "contact_name":"傅基本",
 *     │         "contact_tel":"15110067060",
 *     │         "express":"洗涤企业配送",
 *     │         "province":"湖北",
 *     │         "city":"武汉",
 *     │         "address":"湖北武汉雨花石",
 *     │         "pickup_time":"2018-12-12",
 *     │         "pay_sn":"",
 *     │         "id":48
 *     │     }
 *     │ }
 */


public class OrderDetailResponseBean {
    public String f_name=""; //洗涤企业名称
    public int fid;// 	洗涤企业id
    public String s_name="";// 	洗涤项目名称
    public String img="";// 	洗涤项目图片
    public String total_price="";// 	订单金额
    public List<ServiceCateEntry> service_cate;//
    public String order_sn="";// 	订单编号
    public String status="";// 	订单状态
    public String createtime="";// 	下单时间
    public String pay_cate="";// 	支付方式  1全额支付   2组合支付
    public String pay_type="";// 	支付通道
    public String pay_price="";//	支付金额
    public String pay_jsl="";// 	支付jsl
    public String updatetime="";// 	支付时间....
    public String contact_name="";// 	发件人
    public String contact_tel="";// 	发件人手机号码
    public String express="";// 	取件方式
    public String province="";// 	省
    public String city="";// 	市
    public String address="";// 	详细地址
    public String pickup_time="";// 取件时间
    public String pay_sn="";
    public int id;//订单id 重要！！！  不等同于订单编号
    private String orderStr;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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

    public List<ServiceCateEntry> getService_cate() {
        return service_cate;
    }

    public void setService_cate(List<ServiceCateEntry> service_cate) {
        this.service_cate = service_cate;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPay_cate() {
        return pay_cate;
    }

    public void setPay_cate(String pay_cate) {
        this.pay_cate = pay_cate;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public String getPay_jsl() {
        return pay_jsl;
    }

    public void setPay_jsl(String pay_jsl) {
        this.pay_jsl = pay_jsl;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getPay_sn() {
        return pay_sn;
    }

    public void setPay_sn(String pay_sn) {
        this.pay_sn = pay_sn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public class ServiceCateEntry{
        private String c_name="";
        private String total="";
        private String price="";

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

}
