package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.quickpay.qpay.Controller.Register.RigesterPresenter;
import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityRegisterationBinding;
import com.quickpay.qpay.databinding.ActivityWelcomeBinding;
import com.quickpay.qpay.models.request.Register;
import com.quickpay.qpay.util.AppUtils;
import com.quickpay.qpay.util.Validation;

public class RegisterationActivity extends AppCompatActivity implements View.OnClickListener, RigesterPresenter.RigesterView {

    ActivityRegisterationBinding activityRegisterationBinding;

    private View view;
    private Dialog dialog;
    private RigesterPresenter presenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //setContentView(R.layout.activity_registeration);
        initView();

    }

    private void initView() {

        activityRegisterationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registeration);
        view = activityRegisterationBinding.getRoot();
        context = RegisterationActivity.this;
        dialog = AppUtils.hideShowProgress(context);
        presenter = new RigesterPresenter(this);

        activityRegisterationBinding.BTNsignIn.setOnClickListener(this);
        activityRegisterationBinding.TVSignup.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.BTNsign_in:
                DoRegisteration();

                ///  doLogin();
                break;
            case R.id.TV_Signup:

                intent = new Intent(RegisterationActivity.this, WelcomeActivity.class);
                break;


        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void DoRegisteration() {

        String user_name = activityRegisterationBinding.ETUserName.getText().toString().trim();
        String user_number = activityRegisterationBinding.ETNumber.getText().toString().trim();
        String user_Password = activityRegisterationBinding.ETPassword.getText().toString().trim();
        String confirmPassword = activityRegisterationBinding.ETConfirmPassword.getText().toString().trim();


        if (user_name.isEmpty()) {
            activityRegisterationBinding.ETUserName.setError("Empty Field!");
            Snackbar.make(view, "Empty Field!", Snackbar.LENGTH_SHORT).show();

        } else if (!Validation.isValidPhoneNumber(user_number)) {

            Snackbar.make(view, "Enter valid Mobile number!", Snackbar.LENGTH_SHORT).show();

            activityRegisterationBinding.ETNumber.setError("Enter valid Mobile number!");
        } else if (user_Password.isEmpty()) {
            Snackbar.make(view, "Password is empty!", Snackbar.LENGTH_SHORT).show();

            activityRegisterationBinding.ETPassword.setError("Password is empty!");
        } else if (user_Password.length() < 7) {
            Snackbar.make(view, "Password should be more then 6 charactor!", Snackbar.LENGTH_SHORT).show();

            activityRegisterationBinding.ETPassword.setError("Password should be more then 6 charactor!");

        } else if (user_Password.isEmpty()) {
            Snackbar.make(view, "Empty Field!", Snackbar.LENGTH_SHORT).show();

            activityRegisterationBinding.ETConfirmPassword.setError("Empty Field!");

        } else if (!user_Password.equals(confirmPassword)) {
            Snackbar.make(view, "Password is not match!", Snackbar.LENGTH_SHORT).show();

            activityRegisterationBinding.ETConfirmPassword.setError("Password is not match!");

        } else {

            Register user = new Register(user_name, user_number, user_Password);
            presenter.createUser(user);
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


    }

    @Override
    public void onSuccess(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();


        startActivity(new Intent(RegisterationActivity.this, WelcomeActivity.class));

    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_SHORT).show();

    }
}
