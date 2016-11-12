package com.minhnpa.coderschool.a9boarding.model;

import java.util.List;

public class UserInformation {
    private List<String> addresses;
    private List<String> email;
    private String name;
    private List<String> phone;

    public UserInformation() {
    }

    public UserInformation(List<String> addresses, List<String> email, String name, List<String> phone) {
        this.addresses = addresses;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
