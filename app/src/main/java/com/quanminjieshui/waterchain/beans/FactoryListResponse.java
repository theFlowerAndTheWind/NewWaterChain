package com.quanminjieshui.waterchain.beans;

import com.quanminjieshui.waterchain.beans.Factory;

import java.util.List;

public class FactoryListResponse {
    private List<Factory>lists;
    private String tech_desc;

    public List<Factory> getLists() {
        return lists;
    }

    public String getTech_desc() {
        return tech_desc;
    }
}
