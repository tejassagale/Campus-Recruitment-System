package com.android.letsgetplaced;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

@SuppressLint("Registered")
public class Splash extends Activity {

    private static final int SPLASH_DISPLAY_TIME = 2500;


    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);



        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(Splash.this,
                        LoginActivity.class);

                Splash.this.startActivity(intent);
                Splash.this.finish();

            }
        }, SPLASH_DISPLAY_TIME);

    }}