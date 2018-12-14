package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class OrderListResponseBean {

    public List<OrderListEntity> lists;

    public List<OrderListEntity> getLists() {
        return lists;
    }

    public void setLists(List<OrderListEntity> lists) {
        this.lists = lists;
    }

    public class OrderListEntity{
        // TODO: 2018/12/10 里边什么东西？不知道
    }
}
