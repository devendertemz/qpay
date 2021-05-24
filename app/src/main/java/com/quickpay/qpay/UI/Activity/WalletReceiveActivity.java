package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.quickpay.qpay.Controller.Register.RigesterPresenter;
import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityWalletReceiveBinding;
import com.quickpay.qpay.models.response.LoginRespBean;
import com.quickpay.qpay.sharedPref.MyPreferences;
import com.quickpay.qpay.sharedPref.PrefConf;
import com.quickpay.qpay.util.AppUtils;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class WalletReceiveActivity extends AppCompatActivity implements View.OnClickListener {


    Animation slide_right;
    ActivityWalletReceiveBinding activityWalletReceiveBinding;
    private View view;
    private Dialog dialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_wallet_receive);
        activityWalletReceiveBinding = DataBindingUtil.setContentView(this, R.layout.activity_wallet_receive);
        view = activityWalletReceiveBinding.getRoot();
        context = WalletReceiveActivity.this;
        dialog = AppUtils.hideShowProgress(context);


        slide_right = AnimationUtils.loadAnimation(WalletReceiveActivity.this, R.anim.slide_in_right);
        activityWalletReceiveBinding.recbar.startAnimation(slide_right);
        //  LoginRespBean userData= MyPreferences.getInstance(this).get();

        int KEY_USER_ID = MyPreferences.getInstance(getApplication()).getInteger(PrefConf.KEY_USER_ID, 0);
        activityWalletReceiveBinding.Name.setText(MyPreferences.getInstance(getApplication()).getString(PrefConf.KEY_USER_NAME, ""));

        activityWalletReceiveBinding.id.setText(String.valueOf(KEY_USER_ID) + MyPreferences.getInstance(getApplication()).getString(PrefConf.KEY_USER_NAME, "") + "@okicici");


        activityWalletReceiveBinding.number.setText(MyPreferences.getInstance(getApplication()).getString(PrefConf.KEY_USER_PHONE, ""));
        QRGEncoder qrgEncoder = new QRGEncoder(String.valueOf(KEY_USER_ID), null, QRGContents.Type.TEXT, 500);
        try {
            Bitmap qrBits = qrgEncoder.encodeAsBitmap();
            activityWalletReceiveBinding.qrPlaceHolder.setImageBitmap(qrBits);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        activityWalletReceiveBinding.onBackpress.setOnClickListener(this);
        activityWalletReceiveBinding.openSacnner.setOnClickListener(this);

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
            case R.id.openSacnner:

                intent = new Intent(WalletReceiveActivity.this, ScannerActivity.class);
                break;


        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

