package com.minhnpa.coderschool.a9boarding.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Information implements Parcelable {
    private String phone;

    public Information(){

    }
    private Information(Parcel in) {
        phone = in.readString();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
    }

    public String getPhone() {
        return phone;
    }
}
