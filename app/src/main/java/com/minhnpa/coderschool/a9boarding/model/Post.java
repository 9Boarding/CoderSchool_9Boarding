package com.minhnpa.coderschool.a9boarding.model;

import java.util.List;

public class Post {
    private String address;
    private int bookmarksCount;
    private int commentsCount;
    private String description;
    private List<String> images;
    private PostInformation postInformation;
    private String postAt;
    private String postId;
    private User user;

    public Post() {

    }

    public Post(User user, String address, int bookmarksCount, int commentsCount,
                String description, List<String> images, PostInformation postInformation, String postAt) {
        this.user = user;
        this.address = address;
        this.bookmarksCount = bookmarksCount;
        this.commentsCount = commentsCount;
        this.description = description;
        this.images = images;
        this.postInformation = postInformation;
        this.postAt = postAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBookmarksCount() {
        return bookmarksCount;
    }

    public void setBookmarksCount(int bookmarksCount) {
        this.bookmarksCount = bookmarksCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImages(String image){
        this.images.add(image);
    }

    public PostInformation getPostInformation() {
        return postInformation;
    }

    public void setPostInformation(PostInformation postInformation) {
        this.postInformation = postInformation;
    }

    public String getPostAt() {
        return postAt;
    }

    public void setPostAt(String postAt) {
        this.postAt = postAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
