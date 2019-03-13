package com.shuzijieshui.www.waterchain.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WanghongHe on 2018/12/13 14:32.
 */

public class BannerListResponseBean {

    public List<BannerListEntity> lists;

    public List<BannerListEntity> getLists() {
        if (lists == null) {
            return new ArrayList<>();
        }
        return lists;
    }

    public void setLists(List<BannerListEntity> lists) {
        this.lists = lists;
    }

    public class BannerListEntity{
        String name;
        String img;
        String url_id;
        String url_type;

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getUrl_id() {
            return url_id;
        }

        public String getUrl_type() {
            return url_type;
        }
    }
}
