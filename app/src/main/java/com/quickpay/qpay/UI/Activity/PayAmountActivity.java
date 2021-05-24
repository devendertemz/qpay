package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.quickpay.qpay.R;
import com.quickpay.qpay.databinding.ActivityPayAmountBinding;
import com.quickpay.qpay.model.ScannerModel;

public class PayAmountActivity extends AppCompatActivity {
    ActivityPayAmountBinding activityPayAmountBinding;
    private View view;

   public ScannerModel scannerModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pay_amount);

        activityPayAmountBinding = DataBindingUtil.setContentView(this, R.layout.activity_pay_amount);
        view = activityPayAmountBinding.getRoot();
        scannerModel = (ScannerModel) getIntent().getSerializableExtra("scannerModel");
        activityPayAmountBinding.setScannerModel(scannerModel);


        Toast.makeText(this, scannerModel.fullname + "", Toast.LENGTH_SHORT).show();

    }
}