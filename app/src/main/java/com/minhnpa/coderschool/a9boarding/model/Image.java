package com.minhnpa.coderschool.a9boarding.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private String imageUrl;

    private Image(Parcel in) {
        imageUrl = in.readString();
    }

    public Image(){

    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
