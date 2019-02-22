package com.shuzijieshui.www.waterchain.beans;

/**
 * Created by songxiaotao on 2019/1/30.
 * Class Note:兑换商城列表
 */

public class GoodsListsResponseBean {

    String name; 	//商品名称 	字符串(string)
    String img; 	//商品图片 	字符串(string)
    String jsl; 	//水方价格 	字符串(string)
    String price; 	//市场价 	字符串(string)
    int id; 	//商品id 	字符串(string)

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
