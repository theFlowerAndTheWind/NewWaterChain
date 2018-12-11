package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/10.
 * Class Note:平台咨询列表
 */

public class InfoListResponseBean {

    public List<InfoListEntity> lists;

    public List<InfoListEntity> getLists() {
        return lists;
    }

    public void setLists(List<InfoListEntity> lists) {
        this.lists = lists;
    }

    public class InfoListEntity{
        // TODO: 2018/12/10
    }

}
