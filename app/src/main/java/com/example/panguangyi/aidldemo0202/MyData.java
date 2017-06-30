package com.example.panguangyi.aidldemo0202;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ************************
 * $claass
 * <p>
 * ${date} $Created by panguangyi on 2017/6/30.
 */

public class MyData implements Parcelable {
    private int iData;
    private String strData;

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iData);
        dest.writeString(this.strData);
    }

    public MyData() {
    }

    protected MyData(Parcel in) {
        this.iData = in.readInt();
        this.strData = in.readString();
    }

    public static final Parcelable.Creator<MyData> CREATOR = new Parcelable.Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel source) {
            return new MyData(source);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public String toString() {
        return "iData=" + iData + " strData=" + strData;
    }
}
