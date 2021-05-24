package com.quickpay.qpay.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCredential {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("phone")
    @Expose
    private String phone;

    public UserCredential (String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    @SerializedName("password")
    @Expose
    private String password;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

