package com.quanminjieshui.waterchain.http.bean;

/**
 * Created by WanghongHe on 2018/12/3 11:39.
 */

public class BaseEntity <T> {

    private static String SUCCESS_CODE = "200";//成功的code
    private String code;
    private String msg;
    private T data;


    public boolean isSuccess() {
        return getCode().equals(SUCCESS_CODE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}