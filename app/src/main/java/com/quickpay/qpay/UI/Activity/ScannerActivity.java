package com.quickpay.qpay.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.quickpay.qpay.Controller.ScannerPresenter;
import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityScannerBinding;
import com.quickpay.qpay.model.ScannerModel;
import com.quickpay.qpay.models.request.ScannerCredential;
import com.quickpay.qpay.models.response.ScannerRespBean;
import com.quickpay.qpay.util.AppUtils;

import java.util.List;

public class ScannerActivity extends AppCompatActivity implements View.OnClickListener, ScannerPresenter.ScannerView {
    ActivityScannerBinding activityScannerBinding;
    private View view;
    private Context context;
    private Dialog dialog;

    CodeScanner codeScanner;
    private ScannerPresenter scannerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_scanner);
        activityScannerBinding = DataBindingUtil.setContentView(this, R.layout.activity_scanner);
        view = activityScannerBinding.getRoot();

        context = ScannerActivity.this;
        dialog = AppUtils.hideShowProgress(context);
        scannerPresenter = new ScannerPresenter(this);

        codeScanner = new CodeScanner(this, activityScannerBinding.scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        ScannerCredential user = new ScannerCredential(String.valueOf(result.getText()));
                        scannerPresenter.DoScanning(user);
                        Toast.makeText(context, result.getText() + "", Toast.LENGTH_SHORT).show();

                        //  activityScannerBinding.getbarCodeAddress.setText(result.getText());
                        //username= result.getText();

                     /*  SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("id",id);
                        editor.putString("username",username);
                        editor.commit();
*//*
                        userDetails();*/
                    }
                });

            }
        });
        activityScannerBinding.scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codeScanner.startPreview();
            }
        });
        activityScannerBinding.onBackpress.setOnClickListener(this);
        activityScannerBinding.openQRCode.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();

    }

    public void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(ScannerActivity.this, "Camera Permission is Required.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();

            }
        }).check();
    }


    @Override
    public void onClick(View v) {
        // onBackpress
        Intent intent = null;
        switch (v.getId()) {

            case R.id.onBackpress:
                onBackPressed();

                ///  doLogin();
                break;
            case R.id.openQR_code:

                intent = new Intent(ScannerActivity.this, WalletReceiveActivity.class);
                break;


        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

        Log.e("message", message);

        // Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(List<ScannerRespBean> scannerRespBeanList, String message) {

        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();

        if (scannerRespBeanList != null && scannerRespBeanList.size() > 0) {
            for (int i = 0; i < scannerRespBeanList.size(); i++) {
                Toast.makeText(context, scannerRespBeanList.get(0).getCreditAmt(), Toast.LENGTH_SHORT).show();
                ScannerModel scannerModel = new ScannerModel(scannerRespBeanList.get(0).getPaymentId(),
                        scannerRespBeanList.get(0).getUserId(),
                        scannerRespBeanList.get(0).getId(),
                        scannerRespBeanList.get(0).getFullname(),
                        scannerRespBeanList.get(0).getEmail(),
                        scannerRespBeanList.get(0).getPhone());

                ScannerRespBean scannerRespBean=new ScannerRespBean();
                scannerRespBean.setPaymentId(scannerRespBeanList.get(0).getPaymentId());

                Intent in = new Intent(ScannerActivity.this, PayAmountActivity.class);
                in.putExtra("scannerModel", scannerModel);

                startActivity(in);


            }

        }

        /*if (message.equalsIgnoreCase("ok")){
            Snackbar.make(view, response.getCreditAmt(), Snackbar.LENGTH_SHORT).show();
          *//*  startActivity(new Intent(this, MainActivity.class));
            finish();*//*
        }*/
    }


    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_SHORT).show();
        Log.e("onFailure", t.getMessage());

    }


}