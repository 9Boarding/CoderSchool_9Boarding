package com.minhnpa.coderschool.a9boarding.model;

public class PostInformation {
    private String area;
    private String price;

    public PostInformation() {
    }

    public PostInformation(String area, String price) {
        this.area = area;
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
