package com.minhnpa.coderschool.a9boarding.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.http.POST;

public class Post {
    //Post instance key
    public static final String bookMarkCountKey = "bookmarks_count";
    public static final String commentCountKey = "comments_count";
    public static final String imageKey = "images";
    public static final String postAtKey = "post_at";
    public static final String postIdKet = "post_id";
    public static final String postInformationKey = "post_information";
    public static final String userKey = "user";

    private int bookmarksCount;
    private int commentsCount;
    private List<String> images;
    private PostInformation postInformation;
    private String post_at;
    private String postId;
    private User user;

    public static Post newInstance(DataSnapshot dataSnapshot){
        Post instance =  new Post();

        Map<String, Objects> post = ((Map<String, Objects>) dataSnapshot.getValue());
        instance.setBookmarksCount(Integer.parseInt(String.valueOf(post.get(bookMarkCountKey))));
        instance.setCommentsCount(Integer.parseInt(String.valueOf(post.get(commentCountKey))));

        for (DataSnapshot d : dataSnapshot.getChildren()) {
            if (d.getKey().equalsIgnoreCase(imageKey)) {
                Iterator<DataSnapshot> tmp = d.getChildren().iterator();
                while (tmp.hasNext()) {
                    String url = String.valueOf(tmp.next().getValue());
                }
            }
        }


//        List<String> i = new ArrayList<>(imagesObj);
//        Log.d("images", "newInstance: " + i.size());
        return instance;
    }

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