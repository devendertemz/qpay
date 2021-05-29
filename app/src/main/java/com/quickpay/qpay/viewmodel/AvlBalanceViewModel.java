package com.quickpay.qpay.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quickpay.qpay.api.ApiManager;
import com.quickpay.qpay.api.ApiService;
import com.quickpay.qpay.models.response.AvlBalance;
import com.quickpay.qpay.models.response.LoginRespBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvlBalanceViewModel extends ViewModel {


    Context context;
    private MutableLiveData<AvlBalance>avlBalance;
    private MutableLiveData<String>Errors;


    public AvlBalanceViewModel() {
        avlBalance=new MutableLiveData<>();
        Errors=new MutableLiveData<>();
    }

    public MutableLiveData<AvlBalance> getavlbalanceObserve()
    {
        return avlBalance;
    }
    public MutableLiveData<String> Error()
    {
        return Errors;
    }
    public void makeApiCall(String userid)
    {

        ApiService apiService= ApiManager.getRetrofit(context).create(ApiService.class);
        Call<AvlBalance> call=apiService.getAvlBalance(userid);
        call.enqueue(new Callback<AvlBalance>() {
            @Override
            public void onResponse(Call<AvlBalance> call, Response<AvlBalance> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        avlBalance.postValue(response.body());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else
                {
                    String errorStr = null;
                    try {
                        errorStr = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorStr);
                         Errors.postValue( jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }



                }


            }

            @Override
            public void onFailure(Call<AvlBalance> call, Throwable t) {

            }
        });
    }

}
