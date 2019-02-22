package com.shuzijieshui.www.waterchain.beans.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songxiaotao on 2019/2/2.
 * Class Note:创建订单--兑换商城
 */

public class CreateOrderExchangeReqBean implements Parcelable {

    int gid; 	//商品id 	数字(number) 	是 	placeholder="非必填"
    int count; 	//购买数量 	数字(number) 	是 	placeholder="非必填" 	活动商品传1
    String receiver; 	//联系人 	字符串(string) 	否 	placeholder="非必填"
    String tel; 	//联系电话 	字符串(string) 	否 	placeholder="非必填"
    String province; 	//省份 	字符串(string) 	否 	placeholder="非必填"
    String city; 	//城市 	字符串(string) 	否 	placeholder="非必填"
    String address; 	//详细地址 	字符串(string) 	否 	placeholder="非必填"

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gid);
        dest.writeInt(this.count);
        dest.writeString(this.receiver);
        dest.writeString(this.tel);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.address);
    }

    public CreateOrderExchangeReqBean() {
    }

    protected CreateOrderExchangeReqBean(Parcel in) {
        this.gid = in.readInt();
        this.count = in.readInt();
        this.receiver = in.readString();
        this.tel = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<CreateOrderExchangeReqBean> CREATOR = new Parcelable.Creator<CreateOrderExchangeReqBean>() {
        @Override
        public CreateOrderExchangeReqBean createFromParcel(Parcel source) {
            return new CreateOrderExchangeReqBean(source);
        }

        @Override
        public CreateOrderExchangeReqBean[] newArray(int size) {
            return new CreateOrderExchangeReqBean[size];
        }
    };
}
