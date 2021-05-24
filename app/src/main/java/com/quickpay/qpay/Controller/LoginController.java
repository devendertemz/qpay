package com.quickpay.qpay.Controller;


import com.quickpay.qpay.View.iLoginView;
import com.quickpay.qpay.model.User;

public class LoginController implements iLoginController {


    iLoginView loginView;

    public LoginController(iLoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void OnLogin(String name, String password) {

        User user = new User(name, password);
        int logincode = user.isValid();

        if (logincode == 0) {
            loginView.OnLoginError("Plese Enter Number");


        } else if (logincode == 1) {
            loginView.OnLoginError("Plese Enter Password");
        } else if (logincode == 2) {
            loginView.OnLoginError("Password should be more then 6 charactor");
        }else if (logincode == 3) {
            loginView.OnLoginError("Enter valid Mobile number!");
        }  else {
            loginView.OnLoginSuccs("Login Success");
        }
    }
}
