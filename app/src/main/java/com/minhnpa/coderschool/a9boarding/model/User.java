package com.minhnpa.coderschool.a9boarding.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private List<String> addresses;
    private String birthDate;
    private List<String> bookmarks;
    private List<String> email;
    private String name;
    private List<String> phone;
    private String profilePicUrl;
    private int userType;
    private String userId;
    private boolean verified;
    private String gender;

    public static User newInstance(DataSnapshot dataSnapshot){
        User instance = new User();

        return instance;
    }

    public User() {
        addresses = new ArrayList<>();
        bookmarks = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();
    }

    public User(List<String> bookmarks, String profilePicUrl, int userType, List<String> addresses,
                List<String> email, String name, List<String> phone, boolean verified) {
        this.bookmarks = bookmarks;
        this.profilePicUrl = profilePicUrl;
        this.userType = userType;
        this.addresses.addAll(addresses);
        this.email.addAll(email);
        this.name = name;
        this.phone.addAll(phone);
        this.verified = verified;
    }

    public static User fromFirebaseUser(FirebaseUser firebaseUser) {
        User user = new User();

        user.setName(firebaseUser.getDisplayName());
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

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses.clear();
        this.addresses.addAll(addresses);
    }

    public void addAddress(String address) {
        if (!email.isEmpty()) {
            this.addresses.add(address);
        }
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email.clear();
        this.email.addAll(email);
    }

    public void addEmail(String email) {
        if (!email.isEmpty()) {
            this.email.add(email);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone.clear();
        this.phone.addAll(phone);
    }

    public void addPhone(String strPhone) {
        if (!email.isEmpty()) {
            this.phone.add(strPhone);
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (!email.isEmpty()) {
            this.birthDate = birthDate;
        }
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
