package com.minhnpa.coderschool.a9boarding.model;

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
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
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
        return favouritesCount;
    }

    public String getDescription() {
        return description;
    }

    public Information getInfoContact() {
        return infoContact;
    }
}
