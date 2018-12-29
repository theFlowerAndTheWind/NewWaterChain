package com.quanminjieshui.waterchain.event;

import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;

import java.util.List;

public class OrderListsTabScrollEvent {
    private String status;
    private List<OrderListsResponseBean.OrderListEntity>list;

    public OrderListsTabScrollEvent(String status, List<OrderListsResponseBean.OrderListEntity>list) {
        this.status = status;
        this.list=list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListsResponseBean.OrderListEntity> getList() {
        return list;
    }

    public void setList(List<OrderListsResponseBean.OrderListEntity> list) {
        this.list = list;
    }
}
