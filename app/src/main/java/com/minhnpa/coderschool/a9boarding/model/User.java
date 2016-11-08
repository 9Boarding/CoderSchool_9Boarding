package com.minhnpa.coderschool.a9boarding.model;

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
