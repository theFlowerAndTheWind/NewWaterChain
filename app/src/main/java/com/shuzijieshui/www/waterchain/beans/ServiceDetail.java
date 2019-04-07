package com.shuzijieshui.www.waterchain.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceDetail implements Parcelable {
    private long id;
    private int service_id;
    private String img;
    private String s_name;
    private String description;
    private int factory_id;
    private String intro;
    private String content;

    public long getId() {
        return id;
    }

    public int getService_id() {
        return service_id;
    }

    public String getImg() {
        return img;
    }

    public String getS_name() {
        return s_name;
    }

    public String getDescription() {
        return description;
    }

    public int getFactory_id() {
        return factory_id;
    }

    public String getIntro() {
        return intro;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.service_id);
        dest.writeString(this.img);
        dest.writeString(this.s_name);
        dest.writeString(this.description);
        dest.writeInt(this.factory_id);
        dest.writeString(this.intro);
        dest.writeString(this.content);
    }

    public ServiceDetail() {
    }

    protected ServiceDetail(Parcel in) {
        this.id = in.readLong();
        this.service_id = in.readInt();
        this.img = in.readString();
        this.s_name = in.readString();
        this.description = in.readString();
        this.factory_id = in.readInt();
        this.intro = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ServiceDetail> CREATOR = new Parcelable.Creator<ServiceDetail>() {
        @Override
        public ServiceDetail createFromParcel(Parcel source) {
            return new ServiceDetail(source);
        }

        @Override
        public ServiceDetail[] newArray(int size) {
            return new ServiceDetail[size];
        }
    };
}
