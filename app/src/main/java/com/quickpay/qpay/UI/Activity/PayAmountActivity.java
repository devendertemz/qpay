package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityPayAmountBinding;
import com.quickpay.qpay.model.ScannerModel;
import com.quickpay.qpay.models.response.ScannerRespBean;
import com.quickpay.qpay.sharedPref.MyPreferences;
import com.quickpay.qpay.sharedPref.PrefConf;

public class PayAmountActivity extends AppCompatActivity {
    ActivityPayAmountBinding activityPayAmountBinding;
    private View view;

   public ScannerRespBean scannerModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pay_amount);

        activityPayAmountBinding = DataBindingUtil.setContentView(this, R.layout.activity_pay_amount);
        view = activityPayAmountBinding.getRoot();
        scannerModel = (ScannerRespBean) getIntent().getSerializableExtra("scannerModel");
        activityPayAmountBinding.setScannerModel(scannerModel);

        activityPayAmountBinding.ownUpiid.setText(MyPreferences.getInstance(getApplication()).getString(PrefConf.KEY_MY_UPI_ID, ""));

        Toast.makeText(this, scannerModel.getFullname() + "", Toast.LENGTH_SHORT).show();

    }
}