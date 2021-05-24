package com.quickpay.qpay.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.quickpay.qpay.MainActivity;
import com.quickpay.qpay.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SpleshActivity extends AwesomeSplash {


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
    }*/
/*

    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.background); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP


        configSplash.setLogoSplash(R.drawable.quickpay_logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);


        configSplash.setTitleSplash("Connecting Payment Dots");
        configSplash.setTitleTextColor(R.color.icon_color);
        configSplash.setTitleTextSize(15f); //float value
        configSplash.setAnimTitleDuration(2000);
  //configSplash.setTitleFont("@fonts/blacklist.ttf");
 //configSplash.setTitleFont(String.valueOf(R.font.blacklist.ttf));

        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
       //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();

    }
*/

    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.background); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP


        configSplash.setLogoSplash(R.drawable.quickpay_logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);


        configSplash.setTitleSplash("Connecting Payment Dots");
        configSplash.setTitleTextColor(R.color.icon_color);
        configSplash.setTitleTextSize(15f); //float value
        configSplash.setAnimTitleDuration(2000);
        //configSplash.setTitleFont("@fonts/blacklist.ttf");
        //configSplash.setTitleFont(String.valueOf(R.font.blacklist.ttf));

        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();

    }

}