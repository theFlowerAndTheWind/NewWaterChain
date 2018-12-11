package com.quanminjieshui.waterchain.beans;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:
 */

public class FactoryDetailResponseBean {
    public List<WashFatoryDetail> detail; 	//洗涤企业基本信息 	数组(array)
    public List<WashFatoryCageGory> service_lists; 	//洗涤企业所含服务项目列表 	数组(array)

    public List<WashFatoryDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<WashFatoryDetail> detail) {
        this.detail = detail;
    }

    public List<WashFatoryCageGory> getService_lists() {
        return service_lists;
    }

    public void setService_lists(List<WashFatoryCageGory> service_lists) {
        this.service_lists = service_lists;
    }

    public static class WashFatoryDetail{

    }

    public static class WashFatoryCageGory{

    }

}
