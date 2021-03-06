package com.quickpay.qpay.Controller.Login;

import android.content.Context;

import com.quickpay.qpay.models.request.UserCredential;
import com.quickpay.qpay.models.response.LoginRespBean;
import com.quickpay.qpay.util.AppUtils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void loginUser(UserCredential credential) {
        view.showHideLoginProgress(true);
        Call<LoginRespBean> loginCall = AppUtils.getApi((Context) view).doLogin(credential);

        loginCall.enqueue(new Callback<LoginRespBean>() {
            @Override
            public void onResponse(Call<LoginRespBean> call, Response<LoginRespBean> response) {
                view.showHideLoginProgress(false);

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onLoginSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        String errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                        String errorMsg = jsonObject.getString("status");
                        view.onLoginError(errorMsg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        view.onLoginError(ex.getLocalizedMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginRespBean> call, Throwable t) {
                view.showHideLoginProgress(false);
                view.onLoginFailure(t);
            }
        });

    }

    public interface LoginView {
        void showHideLoginProgress(boolean isShow);

        void onLoginError(String message);

        void onLoginSuccess(LoginRespBean response, String message);

        void onLoginFailure(Throwable t);
    }
}
