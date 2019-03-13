package com.shuzijieshui.www.waterchain.beans;

import com.google.gson.annotations.SerializedName;

public class WechatBean {
    @SerializedName("appid")
    private String appId;
    @SerializedName("partnerid")
    private String partnerId;
    @SerializedName("prepayid")
    private String prepayId;
    @SerializedName("timestamp")
    private String timeStamp;
    @SerializedName("noncestr")
    private String noncestr;
    @SerializedName("package")
    private String _package;
    @SerializedName("sign")
    private String sign;

    public String getAppId() {
        return appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String get_package() {
        return _package;
    }

    public String getSign() {
        return sign;
    }
}
