package com.shuzijieshui.www.waterchain.beans;

/**
 *     │ {
 *     │     "code":1,
 *     │     "msg":"操作成功",
 *     │     "data":{
 *     │         "order_sn":"2019040710250559",
 *     │         "f_name":"工业净水分离技术有限公司",
 *     │         "status":1,
 *     │         "s_name":"污水处理合同订单",
 *     │         "count":"0",
 *     │         "total_price":"0.00",
 *     │         "pay_price":"0.00",
 *     │         "pay_jsl":"0",
 *     │         "pay_cate":"全额支付",
 *     │         "add_time":"2019-04-07 23:09:19",
 *     │         "back_jsl":"0",
 *     │         "back_time":"-",
 *     │         "fid":1,
 *     │         "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190328\/5ec43794194932ae1df39fc3578639c0.jpg",
 *     │         "status_view":"待付款"
 *     │     }
 *     │ }
 */
public class OrderDetail {
    private String order_sn;//订单编号
    private String f_name;//洗涤企业名称
    private int status;
    private String s_name;//洗涤项目名称
    private String count;
    private String total_price;//订单金额
    private String pay_price;//支付金额
    private String pay_jsl;//支付jsl
    private String pay_cate;//支付方式
    private String add_time;//下单时间
    private String back_jsl;
    private String back_time;
    private int fid;//洗涤企业id
    private String img;//洗涤项目图片
    private String status_view;//订单状态

    public String getOrder_sn() {
        return order_sn;
    }

    public String getF_name() {
        return f_name;
    }

    public int getStatus() {
        return status;
    }

    public String getS_name() {
        return s_name;
    }

    public String getCount() {
        return count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getPay_price() {
        return pay_price;
    }

    public String getPay_jsl() {
        return pay_jsl;
    }

    public String getPay_cate() {
        return pay_cate;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getBack_jsl() {
        return back_jsl;
    }

    public String getBack_time() {
        return back_time;
    }

    public int getFid() {
        return fid;
    }

    public String getImg() {
        return img;
    }

    public String getStatus_view() {
        return status_view;
    }
}
