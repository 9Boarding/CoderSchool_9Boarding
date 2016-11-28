package com.minhnpa.coderschool.a9boarding.model;

import com.google.firebase.database.DataSnapshot;

import java.util.Map;
import java.util.Objects;

public class PostInformation {
    //PostInformation Keys
    public static final String ADDRESS_KEY = "address";
    public static final String AREA_KEY = "area";
    public static final String DESCRIPTION_KEY = "description";
    public static final String PHONE_KEY = "phone";
    public static final String PRICE_KEY = "price";

    private String address;
    private String area;
    private String description;
    private String phone;
    private String price;

    public static PostInformation newIntance(DataSnapshot dataSnapshot){
        PostInformation instance = new PostInformation();
        Map<String, Objects> infor = ((Map<String, Objects>) dataSnapshot.getValue());

        instance.setAddress(String.valueOf(infor.get(ADDRESS_KEY)));
        instance.setArea(String.valueOf(infor.get(AREA_KEY)));
        instance.setDescription(String.valueOf(infor.get(DESCRIPTION_KEY)));
        instance.setPhone(String.valueOf(infor.get(PHONE_KEY)));
        instance.setPrice(String.valueOf(infor.get(PRICE_KEY)));
        return instance;
    }

    public PostInformation() {
    }

    public PostInformation(String address, String area, String description, String phone, String price) {
        this.address = address;
        this.area = area;
        this.description = description;
        this.phone = phone;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
