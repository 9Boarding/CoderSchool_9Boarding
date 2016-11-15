package com.minhnpa.coderschool.a9boarding.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private int bookmarksCount;
    private int commentsCount;
    private List<String> images;
    private PostInformation postInformation;
    private String postAt;
    private String postId;
    private User user;

    public Post() {
        user = new User();
        postInformation = new PostInformation();
        images = new ArrayList<>();

    }

    public Post(int bookmarksCount, int commentsCount, List<String> images,
                PostInformation postInformation, String postAt, User user) {
        this.bookmarksCount = bookmarksCount;
        this.commentsCount = commentsCount;
        this.images = images;
        this.postInformation = postInformation;
        this.postAt = postAt;
        this.user = user;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images.clear();
        this.images.addAll(images);
    }

    public void addImages(List<String> images){
        this.images.addAll(images);
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
