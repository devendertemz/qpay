package com.quickpay.qpay.Controller;

import android.content.Context;

import com.quickpay.qpay.models.request.ScannerCredential;
import com.quickpay.qpay.models.response.LoginRespBean;
import com.quickpay.qpay.models.response.ScannerRespBean;
import com.quickpay.qpay.util.AppUtils;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerPresenter {
    private ScannerView view;

    public ScannerPresenter(ScannerView view) {
        this.view = view;
    }
    public void DoScanning(ScannerCredential user){

        view.showHideProgress(true);
        Call<List<ScannerRespBean>>userCall = AppUtils.getApi((Context)view).doScanner(user);
        userCall.enqueue(new Callback<List<ScannerRespBean>>() {
            @Override
            public void onResponse(Call<List<ScannerRespBean>> call, Response<List<ScannerRespBean>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {

                    view.onSuccess(response.body(), response.message());

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
            }   @Override
            public void onFailure(Call<List<ScannerRespBean>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);

            }
        });
       /* userCall.enqueue(new Callback<ScannerRespBean>() {
            @Override
            public void onResponse(Call<ScannerRespBean> call, Response<ScannerRespBean> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {

                    view.onSuccess(response.body(), response.message());

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
            public void onFailure(Call<ScannerRespBean> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });*/
    }
    public interface ScannerView {
        void showHideProgress(boolean isShow);

        void onError(String message);
        void onSuccess(List<ScannerRespBean> scannerRespBeanList,String message);

        void onFailure(Throwable t);
    }
}
