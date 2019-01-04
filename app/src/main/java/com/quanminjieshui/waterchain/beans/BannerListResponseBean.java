package com.quanminjieshui.waterchain.beans;

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
        String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
