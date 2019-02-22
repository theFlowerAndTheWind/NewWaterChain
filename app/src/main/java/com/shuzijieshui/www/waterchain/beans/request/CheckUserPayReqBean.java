package com.shuzijieshui.www.waterchain.beans.request;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public class CheckUserPayReqBean {

    int id; 	//商品id 	数字(number) 	是 	placeholder="非必填"
    int count; 	//兑换数量 	数字(number) 	是 	placeholder="非必填" 	活动类商品传1

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
