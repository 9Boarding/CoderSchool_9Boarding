package com.minhnpa.coderschool.a9boarding.model;

import java.util.List;

public class User {
    private List<String> bookmarks;
    private String profilePicUrl;
    private int userType;
    private String userId;
    private UserInformation userInformation;
    private boolean verified;

    public User() {
    }

    public User(List<String> bookmarks, String profilePicUrl, int userType,
                UserInformation userInformation, boolean verified) {
        this.bookmarks = bookmarks;
        this.profilePicUrl = profilePicUrl;
        this.userType = userType;
        this.userInformation = userInformation;
        this.verified = verified;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks = bookmarks;
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
}
