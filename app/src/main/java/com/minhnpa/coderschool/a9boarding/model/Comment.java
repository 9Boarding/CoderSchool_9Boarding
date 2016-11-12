package com.minhnpa.coderschool.a9boarding.model;

public class Comment {
    private String comment_at;
    private String comment_id;
    private String content;
    private User user;

    public Comment() {

    }

    public Comment(String comment_at, String content, User user) {
        this.comment_at = comment_at;
        this.content = content;
        this.user = user;
    }

    public String getComment_at() {
        return comment_at;
    }

    public void setComment_at(String comment_at) {
        this.comment_at = comment_at;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
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
