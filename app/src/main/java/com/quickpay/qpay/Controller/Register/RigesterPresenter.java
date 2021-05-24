package com.quickpay.qpay.Controller.Register;

import android.content.Context;

import com.quickpay.qpay.models.request.Register;
import com.quickpay.qpay.util.AppUtils;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RigesterPresenter {
    private RigesterView view;

    public RigesterPresenter(RigesterView view) {
        this.view = view;
    }
    public void createUser(Register user){

        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi((Context)view).createUser(user);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSuccess(response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject =new JSONObject(errorRes);
                        String err_msg  = jsonObject.getString("error");
                        int status= jsonObject.getInt("status");
                        view.onError(err_msg);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public interface RigesterView {
        void showHideProgress(boolean isShow);

        void onError(String message);

        void onSuccess(String message);

        void onFailure(Throwable t);
    }
}
