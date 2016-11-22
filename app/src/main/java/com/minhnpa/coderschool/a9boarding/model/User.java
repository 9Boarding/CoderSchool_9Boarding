package com.minhnpa.coderschool.a9boarding.model;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    private List<String> bookmarks;
    private String profilePicUrl;
    private int userType;
    private String userId;
    private UserInformation userInformation;
    private boolean verified;
    private String gender;

    public User() {
        userInformation = new UserInformation();
        bookmarks = new ArrayList<>();
    }

    public User(List<String> bookmarks, String profilePicUrl, int userType,
                UserInformation userInformation, boolean verified) {
        this.bookmarks = bookmarks;
        this.profilePicUrl = profilePicUrl;
        this.userType = userType;
        this.userInformation = userInformation;
        this.verified = verified;
    }

    public static User fromFirebaseUser(FirebaseUser firebaseUser) {
        UserInformation userInformation = new UserInformation();
        User user = new User();

        userInformation.setName(firebaseUser.getDisplayName());
        user.setUserInformation(userInformation);
        user.setUserId(firebaseUser.getUid());
        if (firebaseUser.getPhotoUrl() != null) {
            user.setProfilePicUrl(firebaseUser.getPhotoUrl().toString());
        }

        return user;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks.clear();
        this.bookmarks.addAll(bookmarks);
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
