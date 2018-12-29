package com.quanminjieshui.waterchain.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/11.
 * Class Note:
 */

public class FactoryServiceResponseBean {
    public WashFatoryDetail detail; 	//洗涤企业项目基本信息 	数组(array)
    public List<WashFatoryCageGory> cate_lists; 	//洗涤企业项目所含分类列表 	数组(array)

    public WashFatoryDetail getDetail() {
        return detail;
    }

    public void setDetail(WashFatoryDetail detail) {
        this.detail = detail;
    }

    public List<WashFatoryCageGory> getCate_lists() {
        return cate_lists;
    }

    public void setCate_lists(List<WashFatoryCageGory> cate_lists) {
        this.cate_lists = cate_lists;
    }

    public static class WashFatoryDetail implements Parcelable {
        int id;
        int service_id;
        String img;
        String s_name;
        String description;
        int factory_id;

        protected WashFatoryDetail(Parcel in) {
            id = in.readInt();
            service_id = in.readInt();
            img = in.readString();
            s_name = in.readString();
            description = in.readString();
            factory_id = in.readInt();
        }

        public static final Creator<WashFatoryDetail> CREATOR = new Creator<WashFatoryDetail>() {
            @Override
            public WashFatoryDetail createFromParcel(Parcel in) {
                return new WashFatoryDetail(in);
            }

            @Override
            public WashFatoryDetail[] newArray(int size) {
                return new WashFatoryDetail[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getService_id() {
            return service_id;
        }

        public void setService_id(int service_id) {
            this.service_id = service_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFactory_id() {
            return factory_id;
        }

        public void setFactory_id(int factory_id) {
            this.factory_id = factory_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeInt(service_id);
            parcel.writeString(img);
            parcel.writeString(s_name);
            parcel.writeString(description);
            parcel.writeInt(factory_id);
        }
    }

    public static class WashFatoryCageGory implements Parcelable{
        String c_name;
        String unit_price;
        String piece;
        String standard;
        int fscid;
        int piceCount = 0;

        protected WashFatoryCageGory(Parcel in) {
            c_name = in.readString();
            unit_price = in.readString();
            piece = in.readString();
            standard = in.readString();
            fscid = in.readInt();
        }

        public static final Creator<WashFatoryCageGory> CREATOR = new Creator<WashFatoryCageGory>() {
            @Override
            public WashFatoryCageGory createFromParcel(Parcel in) {
                return new WashFatoryCageGory(in);
            }

            @Override
            public WashFatoryCageGory[] newArray(int size) {
                return new WashFatoryCageGory[size];
            }
        };

        public int getPiceCount() {
            return piceCount;
        }

        public void setPiceCount(int piceCount) {
            this.piceCount = piceCount;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getPiece() {
            return piece;
        }

        public void setPiece(String piece) {
            this.piece = piece;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public int getFscid() {
            return fscid;
        }

        public void setFscid(int fscid) {
            this.fscid = fscid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(c_name);
            parcel.writeString(unit_price);
            parcel.writeString(piece);
            parcel.writeString(standard);
            parcel.writeInt(fscid);
        }
    }
}
