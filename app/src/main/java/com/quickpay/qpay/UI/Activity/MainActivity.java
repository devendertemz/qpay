package com.quickpay.qpay.UI.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityMainBinding;
import com.quickpay.qpay.models.response.AvlBalance;
import com.quickpay.qpay.sharedPref.MyPreferences;
import com.quickpay.qpay.sharedPref.PrefConf;
import com.quickpay.qpay.util.AppUtils;
import com.quickpay.qpay.viewmodel.AvlBalanceViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;
    private View view;
    private Context context;
    private Dialog dialog;



    float translateY = 100f;
    Boolean ismenuopen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    private int REQUEST_CODE = 11;


    private AvlBalanceViewModel viewModel;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        InitView();
        GetAvlbalance();



    }

    private void InitView() {

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        view = activityMainBinding.getRoot();
        context = MainActivity.this;
        dialog = AppUtils.hideShowProgress(context);
        dialog.show();


        activityMainBinding.customfloatingactionbutton.fabtwo.setAlpha(0f);
        activityMainBinding.customfloatingactionbutton.fabOne.setAlpha(0f);
        activityMainBinding.customfloatingactionbutton.fabReceive.setAlpha(0f);


        activityMainBinding.customfloatingactionbutton.fabtwo.setTranslationY(translateY);
        activityMainBinding.customfloatingactionbutton.fabOne.setTranslationY(translateY);
        activityMainBinding.customfloatingactionbutton.fabReceive.setTranslationY(translateY);
        activityMainBinding.customfloatingactionbutton.fabtwo.setOnClickListener(this);
        activityMainBinding.customfloatingactionbutton.fabOne.setOnClickListener(this);
        activityMainBinding.customfloatingactionbutton.fabReceive.setOnClickListener(this);
        activityMainBinding.customfloatingactionbutton.fabmenu.setOnClickListener(this);


        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if (
                        result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                                && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        id  = String.valueOf(MyPreferences.getInstance(getApplication()).getString(PrefConf.KEY_USER_ID, "0"));

    }

    private void openmenu() {
        ismenuopen = !ismenuopen;
        activityMainBinding.customfloatingactionbutton.fabmenu.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.fabtwo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.fabOne.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.fabReceive.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.cardview1.setVisibility(View.VISIBLE);
        activityMainBinding.customfloatingactionbutton.cardReceive.setVisibility(View.VISIBLE);
        activityMainBinding.customfloatingactionbutton.cardview2.setVisibility(View.VISIBLE);


    }

    private void closemenu() {
        ismenuopen = !ismenuopen;
        activityMainBinding.customfloatingactionbutton.fabmenu.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        activityMainBinding.customfloatingactionbutton.fabtwo.animate().translationY(translateY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.fabOne.animate().translationY(translateY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        activityMainBinding.customfloatingactionbutton.fabReceive.animate().translationY(translateY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

        activityMainBinding.customfloatingactionbutton.cardview1.setVisibility(View.GONE);
        activityMainBinding.customfloatingactionbutton.cardReceive.setVisibility(View.GONE);
        activityMainBinding.customfloatingactionbutton.cardview2.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.fabmenu:

                if (ismenuopen) {
                    closemenu();
                } else {
                    openmenu();
                }
                break;
            case R.id.fabOne:
                Toast.makeText(this, "Fab One", Toast.LENGTH_SHORT).show();

                if (ismenuopen) {
                    closemenu();
                } else {
                    openmenu();
                }

                break;

            case R.id.fabtwo:

                if (ismenuopen) {
                    closemenu();
                } else {
                    openmenu();
                }
                Intent in = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(in);
                Toast.makeText(this, "Fab two", Toast.LENGTH_SHORT).show();


                break;

            case R.id.fabReceive:

                if (ismenuopen) {
                    closemenu();
                } else {
                    openmenu();
                }
                startActivity(new Intent(MainActivity.this, WalletReceiveActivity.class));



                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==REQUEST_CODE)
        {
            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show();

            if (resultCode!= RESULT_OK)
            {

            }
        }
    }

    private void GetAvlbalance()
    {

        viewModel= ViewModelProviders.of(MainActivity.this).get(AvlBalanceViewModel.class);
        viewModel.getavlbalanceObserve().observe(this, new Observer<AvlBalance>() {
            @Override
            public void onChanged(AvlBalance avlBalance) {
                activityMainBinding.creditAmount.setText(avlBalance.getCreditAmt());
                Toast.makeText(context, avlBalance.getPaymentId()+"", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        viewModel.Error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                activityMainBinding.creditAmount.setText("0.00");

                dialog.dismiss();

            }
        });
        viewModel.makeApiCall(id);
    }


}