package com.elevate.app.takeaway.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserAddressModel {

    @NotNull(message = "User Id is required")
    private long userId;

    @NotEmpty(message = "Address line cannot be empty")
    private String line;

    private String landmark;

    @NotNull(message = "Pincode is required")
    private int pincode;

    private String state;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
