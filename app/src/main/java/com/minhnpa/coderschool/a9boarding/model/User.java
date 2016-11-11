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
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class User {
    private String userId;
    private String user_name;
    private String profileImageUrl;
    private String coverImageUrl;

    public User(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public static User fromFirebaseUser(FirebaseUser firebaseUser){
        User user = new User();
        user.setUserId(firebaseUser.getUid());
        user.setUser_name(firebaseUser.getDisplayName());
        if (firebaseUser.getPhotoUrl() != null){
            user.setProfileImageUrl(firebaseUser.getPhotoUrl().toString());
        }

        return user;
    }
}
