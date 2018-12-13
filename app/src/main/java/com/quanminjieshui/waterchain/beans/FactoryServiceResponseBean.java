package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:
 */

public class FactoryServiceResponseBean {
    public List<WashFatoryDetail> detail; 	//洗涤企业项目基本信息 	数组(array)
    public List<WashFatoryCageGory> cate_lists; 	//洗涤企业项目所含分类列表 	数组(array)

    public List<WashFatoryDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<WashFatoryDetail> detail) {
        this.detail = detail;
    }

    public List<WashFatoryCageGory> getCate_lists() {
        return cate_lists;
    }

    public void setCate_lists(List<WashFatoryCageGory> cate_lists) {
        this.cate_lists = cate_lists;
    }

    public static class WashFatoryDetail{

    }

    public static class WashFatoryCageGory{

    }
}
