package com.quickpay.qpay.model;

import android.text.TextUtils;

public class User implements Iuser {

    private  String number,password;

    public User(String number, String password) {
        this.number = number;
        this.password = password;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if (TextUtils.isEmpty(getNumber()))
        {
            return 0;

        }
        else if (getNumber().length()<=9)
        {

            return  3;

        }
        else if (TextUtils.isEmpty(getPassword()))
        {

            return 1;

        }
        else if (getPassword().length()<=6)
        {

            return  2;

        }
        else {
            return -1;

        }
    }
}
