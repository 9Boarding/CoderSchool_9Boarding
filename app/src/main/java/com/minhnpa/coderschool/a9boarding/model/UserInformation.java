package com.minhnpa.coderschool.a9boarding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInformation implements Serializable {
    private List<String> addresses;
    private List<String> email;
    private String name;
    private List<String> phone;
    private String birthDate;

    public UserInformation() {
        addresses = new ArrayList<>();
        phone = new ArrayList<>();
        email = new ArrayList<>();
    }

    public UserInformation(List<String> addresses, List<String> email, String name, List<String> phone) {
        this();
        this.addresses.addAll(addresses);
        this.email.addAll(email);
        this.name = name;
        this.phone.addAll(phone);
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses.clear();
        this.addresses.addAll(addresses);
    }

    public void addAddress(String address) {
        if (!email.isEmpty()){
            this.addresses.add(address);
        }
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email.clear();
        this.email.addAll(email);
    }

    public void addEmail(String email){
        if (!email.isEmpty()){
            this.email.add(email);
        }
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
        this.phone.clear();
        this.phone.addAll(phone);
    }

    public void addPhone(String strPhone) {
        if (!email.isEmpty()){
            this.phone.add(strPhone);
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (!email.isEmpty()){
            this.birthDate = birthDate;
        }
    }
}
