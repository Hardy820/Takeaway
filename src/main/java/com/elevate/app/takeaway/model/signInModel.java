package com.elevate.app.takeaway.model;

import javax.validation.constraints.NotEmpty;

public class signInModel {

    @NotEmpty(message = "userName cannot be empty")
    private String name;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
