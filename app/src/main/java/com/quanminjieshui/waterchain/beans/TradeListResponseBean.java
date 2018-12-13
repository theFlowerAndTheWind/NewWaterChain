package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:
 */

public class TradeListResponseBean {

    public List<TradeListEntity> lists;

    public List<TradeListEntity> getLists() {
        return lists;
    }

    public void setLists(List<TradeListEntity> lists) {
        this.lists = lists;
    }

    public class TradeListEntity{
        // TODO: 2018/12/10 里边什么东西？不知道
    }
}
