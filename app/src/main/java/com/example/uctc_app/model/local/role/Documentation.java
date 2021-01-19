package com.example.uctc_app.model.local.role;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Documentation implements Parcelable {
    @SerializedName("documentation_id")
    int id;

    @SerializedName("documentation_pic")
    String picPath;

    public Documentation(){};

    public Documentation(int id, String picPath) {
        this.id = id;
        this.picPath = picPath;
    }

    protected Documentation(Parcel in) {
        id = in.readInt();
        picPath = in.readString();
    }

    public static final Creator<Documentation> CREATOR = new Creator<Documentation>() {
        @Override
        public Documentation createFromParcel(Parcel in) {
            return new Documentation(in);
        }

        @Override
        public Documentation[] newArray(int size) {
            return new Documentation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(picPath);
    }
}
