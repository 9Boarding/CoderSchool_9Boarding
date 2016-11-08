package com.minhnpa.coderschool.a9boarding.model;

import java.util.List;

public class Post {
    private long post_id;
    private User user;
    private String created_at;
    private String address;
    private String price;
    private List<Image> images;
    private long favourites_count;
    private String description;
    private Information infomation_contact;


    public long getPostId() {
        return post_id;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public List<Image> getImages() {
        return images;
    }

    public long getFavouritesCount() {
        return favourites_count;
    }

    public String getDescription() {
        return description;
    }

    public Information getInfoContact() {
        return infomation_contact;
    }
}
