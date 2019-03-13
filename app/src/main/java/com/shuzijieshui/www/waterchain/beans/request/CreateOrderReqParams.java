package com.shuzijieshui.www.waterchain.beans.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:创建订单
 */

public class CreateOrderReqParams implements Parcelable {

//    int uid; 	//用户id 	数字(number) 	是 	placeholder="非必填"
    int fsid=0; 	//洗涤工厂服务项目id 	数字(number) 	是 	placeholder="非必填"
    String  trade_detail=""; 	//洗涤类别信息 	数组(array) 	是 	placeholder="非必填" 	array( 0 => fscid_数量 )
    int express=1; 	//取件方式 	数字(number) 	是 	placeholder="非必填" 	1洗涤企业配送 2自取
    String contact_name=""; 	//联系人姓名 	字符串(string) 	是 	placeholder="非必填"
    String contact_tel=""; 	//联系人手机号 	字符串(string) 	是 	placeholder="非必填"
    String province=""; 	//省份 	字符串(string) 	是 	placeholder="非必填"
    String city=""; 	//城市 	字符串(string) 	是 	placeholder="非必填"
    String address=""; 	//详细地址 	字符串(string) 	是 	placeholder="非必填"
    int pay_cate=1; 	//支付类型 	数字(number) 	是 	placeholder="非必填" 	1全额支付 2组合支付
    String pickup_time=""; 	//配送时间 	字符串(string) 	否 	placeholder="非必填"
    String pay_type="1";//支付渠道	字符串(string)	是		1支付宝 2微信 3线下支付



    public int getFsid() {
        return fsid;
    }

    public void setFsid(int fsid) {
        this.fsid = fsid;
    }

    public String getTrade_detail() {
        return trade_detail;
    }

    public void setTrade_detail(String trade_detail) {
        this.trade_detail = trade_detail;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
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

    public int getPay_cate() {
        return pay_cate;
    }

    public void setPay_cate(int pay_cate) {
        this.pay_cate = pay_cate;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.fsid);
        dest.writeString(this.trade_detail);
        dest.writeInt(this.express);
        dest.writeString(this.contact_name);
        dest.writeString(this.contact_tel);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.address);
        dest.writeInt(this.pay_cate);
        dest.writeString(this.pickup_time);
        dest.writeString(this.pay_type);
    }

    public CreateOrderReqParams() {
    }

    protected CreateOrderReqParams(Parcel in) {
        this.fsid = in.readInt();
        this.trade_detail = in.readString();
        this.express = in.readInt();
        this.contact_name = in.readString();
        this.contact_tel = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.address = in.readString();
        this.pay_cate = in.readInt();
        this.pickup_time = in.readString();
        this.pay_type = in.readString();
    }

    public static final Parcelable.Creator<CreateOrderReqParams> CREATOR = new Parcelable.Creator<CreateOrderReqParams>() {
        @Override
        public CreateOrderReqParams createFromParcel(Parcel source) {
            return new CreateOrderReqParams(source);
        }

        @Override
        public CreateOrderReqParams[] newArray(int size) {
            return new CreateOrderReqParams[size];
        }
    };
}
