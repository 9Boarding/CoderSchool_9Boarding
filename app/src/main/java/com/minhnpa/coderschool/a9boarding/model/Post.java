package com.minhnpa.coderschool.a9boarding.model;

<<<<<<< HEAD
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post implements Parcelable {
    private long postId;
    private User user;
    private String createdAt;
    private String address;
    private String price;
    private List<Image> images;
    private long favouritesCount;
    private String description;
    private Information infoContact;

    private Post(Parcel in) {
        postId = in.readLong();
        user = in.readParcelable(User.class.getClassLoader());
        createdAt = in.readString();
        address = in.readString();
        price = in.readString();
        images = in.createTypedArrayList(Image.CREATOR);
        favouritesCount = in.readLong();
        description = in.readString();
        infoContact = in.readParcelable(Information.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(postId);
        dest.writeParcelable(user, flags);
        dest.writeString(createdAt);
        dest.writeString(address);
        dest.writeString(price);
        dest.writeTypedList(images);
        dest.writeLong(favouritesCount);
        dest.writeString(description);
        dest.writeParcelable(infoContact, flags);
    }

    public long getPostId() {
        return postId;
=======
import java.util.List;

public class Post {
    private long post_id;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String post_id;
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
>>>>>>> develop
    private List<String> images;
    private long favourites_count;
    private String description;
    private String phoneNumber;


    public Post(){
        images = new ArrayList<>();
    }


    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
<<<<<<< HEAD
        return createdAt;
=======
        return created_at;
>>>>>>> develop
    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public List<Image> getImages() {
        return images;
    }

    public long getFavouritesCount() {
<<<<<<< HEAD
        return favouritesCount;
=======
        return favourites_count;
>>>>>>> develop
    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images.clear();
        this.images.addAll(images);
    }

    public void addImages(String image){
        this.images.add(image);
    }

    public long getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(long favourites_count) {
        this.favourites_count = favourites_count;
    }

    public String getDescription() {
        return description;
    }

    public Information getInfoContact() {
<<<<<<< HEAD
        return infoContact;
=======
        return infomation_contact;
>>>>>>> develop
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
