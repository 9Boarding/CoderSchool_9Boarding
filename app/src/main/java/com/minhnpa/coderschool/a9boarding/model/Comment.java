package com.minhnpa.coderschool.a9boarding.model;

public class Comment {
    private String commentAt;
    private String commentId;
    private String content;
    private User user;

    public Comment() {

    }

    public Comment(String comment_at, String content, User user) {
        this.commentAt = comment_at;
        this.content = content;
        this.user = user;
    }

    public String getCommentAt() {
        return commentAt;
    }

    public void setCommentAt(String commentAt) {
        this.commentAt = commentAt;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}