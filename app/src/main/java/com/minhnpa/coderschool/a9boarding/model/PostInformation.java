package com.minhnpa.coderschool.a9boarding.model;

public class PostInformation {
    private String address;
    private String area;
    private String description;
    private String phone;
    private String price;

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
