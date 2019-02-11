package com.quanminjieshui.waterchain.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class GoodsDetailResponseBean implements Parcelable {
    int id; 	//商品id 	字符串(string) 	number|4
    int cate_id; 	//商品类型 	字符串(string) 		1（实物商品）|2（活动）
    String name; 	//商品名称 	字符串(string)
    String img; 	//商品图片 	字符串(string)
    String jsl; 	//水方价格 	字符串(string)
    String price; 	//市场价 	字符串(string)
    String description; 	//描述 	字符串(string)
    int stock; 	//设定库存 	字符串(string)
    int now_stock; 	//现有库存 	字符串(string)
    String intro;
    int status;
    Long createtime;
    Long updatetime;
    int is_sort;
    String status_view;
    String cate;
    int count;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getJsl() {
        return jsl;
    }

    public void setJsl(String jsl) {
        this.jsl = jsl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getNow_stock() {
        return now_stock;
    }

    public void setNow_stock(int now_stock) {
        this.now_stock = now_stock;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public int getIs_sort() {
        return is_sort;
    }

    public void setIs_sort(int is_sort) {
        this.is_sort = is_sort;
    }

    public String getStatus_view() {
        return status_view;
    }

    public void setStatus_view(String status_view) {
        this.status_view = status_view;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.cate_id);
        dest.writeString(this.name);
        dest.writeString(this.img);
        dest.writeString(this.jsl);
        dest.writeString(this.price);
        dest.writeString(this.description);
        dest.writeInt(this.stock);
        dest.writeInt(this.now_stock);
        dest.writeString(this.intro);
        dest.writeInt(this.status);
        dest.writeValue(this.createtime);
        dest.writeValue(this.updatetime);
        dest.writeInt(this.is_sort);
        dest.writeString(this.status_view);
        dest.writeString(this.cate);
        dest.writeInt(this.count);
    }

    public GoodsDetailResponseBean() {
    }

    protected GoodsDetailResponseBean(Parcel in) {
        this.id = in.readInt();
        this.cate_id = in.readInt();
        this.name = in.readString();
        this.img = in.readString();
        this.jsl = in.readString();
        this.price = in.readString();
        this.description = in.readString();
        this.stock = in.readInt();
        this.now_stock = in.readInt();
        this.intro = in.readString();
        this.status = in.readInt();
        this.createtime = (Long) in.readValue(Long.class.getClassLoader());
        this.updatetime = (Long) in.readValue(Long.class.getClassLoader());
        this.is_sort = in.readInt();
        this.status_view = in.readString();
        this.cate = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<GoodsDetailResponseBean> CREATOR = new Parcelable.Creator<GoodsDetailResponseBean>() {
        @Override
        public GoodsDetailResponseBean createFromParcel(Parcel source) {
            return new GoodsDetailResponseBean(source);
        }

        @Override
        public GoodsDetailResponseBean[] newArray(int size) {
            return new GoodsDetailResponseBean[size];
        }
    };
}
