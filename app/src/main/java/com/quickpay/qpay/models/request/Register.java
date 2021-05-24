package com.quickpay.qpay.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;

    public Register(String fullname, String phone, String password) {
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
    }
}