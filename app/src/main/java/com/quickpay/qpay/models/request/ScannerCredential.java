package com.quickpay.qpay.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScannerCredential {

    @SerializedName("status")
    @Expose

    private String status;

    @SerializedName("user_id")
    @Expose
    private String userId;
    public ScannerCredential(String userId) {
        this.userId = userId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




}