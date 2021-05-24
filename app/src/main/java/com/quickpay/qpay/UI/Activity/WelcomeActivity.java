package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.quickpay.qpay.Controller.Login.LoginPresenter;
import com.quickpay.qpay.Controller.LoginController;
import com.quickpay.qpay.Controller.iLoginController;
import com.quickpay.qpay.MainActivity;
import com.quickpay.qpay.R;
import com.quickpay.qpay.View.iLoginView;
import com.quickpay.qpay.databinding.ActivityWelcomeBinding;
import com.quickpay.qpay.models.request.UserCredential;
import com.quickpay.qpay.models.response.LoginRespBean;
import com.quickpay.qpay.sharedPref.MyPreferences;
import com.quickpay.qpay.sharedPref.PrefConf;
import com.quickpay.qpay.util.AppUtils;

import soup.neumorphism.NeumorphButton;

public class WelcomeActivity extends AppCompatActivity implements iLoginView, LoginPresenter.LoginView {
    EditText ET_Number, ET_Password;
    iLoginController loginController;
    String number, password;

    private Context context;
    private Dialog dialog;
    private View view;
    private LoginPresenter presenter;
    private String user_name;
    ActivityWelcomeBinding binding;
    //private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_welcome);
        InitView();


    }

    private void InitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        view = binding.getRoot();

        ET_Number = findViewById(R.id.ET_Number);
        ET_Password = findViewById(R.id.ET_Password);
        loginController = new LoginController(this);
        context = WelcomeActivity.this;

        // setTwoTextColor();
        dialog = AppUtils.hideShowProgress(context);
        presenter = new LoginPresenter(this);

    }

    public void onClickSignIN(View view) {
        // Toast.makeText(this, "hwey", Toast.LENGTH_SHORT).show();
        number = ET_Number.getText().toString().trim();
        password = ET_Password.getText().toString().trim();


        loginController.OnLogin(number, password);

    }

    public void onClickTV_ForgetPassword(View view) {
    }

    public void onClickTV_SignUp(View view) {


        startActivity(new Intent(WelcomeActivity.this, RegisterationActivity.class));
    }

    @Override
    public void OnLoginSuccs(String msg) {
        //Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
      /*  startActivity(new Intent(WelcomeActivity.this, MainActivity.class));*/

   UserCredential credential = new UserCredential(number, password);
        presenter.loginUser(credential);

    }

    @Override
    public void OnLoginError(String msg) {
        Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showHideLoginProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onLoginError(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onLoginSuccess(LoginRespBean response, String message) {
        Snackbar.make(view,response.getStatus() , Snackbar.LENGTH_SHORT).show();

        MyPreferences.getInstance(context).putBoolean(PrefConf.KEY_IS_LOGGED_IN,true);
        MyPreferences.getInstance(context).putInteger(PrefConf.KEY_USER_ID,response.getData().getId());
        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_NAME,response.getData().getFullname());
//        MyPreferences.getInstance(context).putString(PrefConf.KEY_MY_REFERRAL_CODE,response.getResult().getReferalCode());

        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_PHONE,response.getData().getPhone());

//        Snackbar.make(mView,mesage,Snackbar.LENGTH_SHORT).show();
        if (message.equalsIgnoreCase("ok")){
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }

    Toast.makeText(context, response.getData().getId() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(Throwable t) {
        Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
       /* if (SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }*/

        if(MyPreferences.getInstance(this).getBoolean(PrefConf.KEY_IS_LOGGED_IN,false)){
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }/*else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }*/
    }
}