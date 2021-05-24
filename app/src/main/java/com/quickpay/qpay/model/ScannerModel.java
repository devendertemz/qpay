package com.quickpay.qpay.model;

import java.io.Serializable;

public class ScannerModel implements Serializable {


        public   String paymentId;

      public String userId, id, fullname, email, phone;

    public ScannerModel(String paymentId, String userId, String id, String fullname, String email, String phone) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
