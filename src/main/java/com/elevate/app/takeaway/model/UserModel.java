package com.elevate.app.takeaway.model;

import javax.validation.constraints.NotEmpty;

public class UserModel {

    @NotEmpty(message = "User name cannot be empty")
    private String name;

    @NotEmpty(message = "User mobile cannot be empty")
    private String mobileNumber;

    @NotEmpty(message = "User email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
