package com.minhnpa.coderschool.a9boarding.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    @SerializedName("bookmarks_count")
    private int bookmarks_count;

    @SerializedName("comments_count")
    private int comments_count;

    private List<String> images;
    private PostInformation postInformation;
    private String post_at;
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
        this.post_at = post_at;
        this.user = user;
    }

    public int getBookmarksCount() {
        return bookmarksCount;
    }

    public void setBookmarksCount(int bookmarksCount) {
        this.bookmarks_count = bookmarksCount;
    }

    public int getCommentsCount() {
        return comments_count;
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

    public void addImages(List<String> images) {
        this.images.addAll(images);
    }

    public void addImages(String image) {
        this.images.add(image);
    }

    public void setPostInformation(PostInformation postInformation) {
        this.postInformation = postInformation;
    }

    public String getPostAt() {
        return post_at;
    }

    public void setPostAt(String postAt) {
        this.post_at = postAt;
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