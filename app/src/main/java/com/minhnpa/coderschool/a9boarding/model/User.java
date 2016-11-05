package com.minhnpa.coderschool.a9boarding.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable {
    private long userId;
    private List<Bookmark> bookmarks;
    private String profileImageUrl;
    private String coverImageUrl;

    private User(Parcel in) {
        userId = in.readLong();
        bookmarks = in.createTypedArrayList(Bookmark.CREATOR);
        profileImageUrl = in.readString();
        coverImageUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeTypedList(bookmarks);
        dest.writeString(profileImageUrl);
        dest.writeString(coverImageUrl);
    }

    public long getUserId() {
        return userId;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
}
