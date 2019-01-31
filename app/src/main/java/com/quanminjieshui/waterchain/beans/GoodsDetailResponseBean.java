package com.quanminjieshui.waterchain.beans;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class GoodsDetailResponseBean {
    int id; 	//商品id 	字符串(string) 	number|4
    String cate_id; 	//商品类型 	字符串(string) 		1（实物商品）|2（活动）
    String name; 	//商品名称 	字符串(string)
    String img; 	//商品图片 	字符串(string)
    String jsl; 	//水方价格 	字符串(string)
    String price; 	//市场价 	字符串(string)
    String description; 	//描述 	字符串(string)
    String stock; 	//设定库存 	字符串(string)
    String now_stock; 	//现有库存 	字符串(string)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getNow_stock() {
        return now_stock;
    }

    public void setNow_stock(String now_stock) {
        this.now_stock = now_stock;
    }
}
