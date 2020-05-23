package com.android.letsgetplaced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LetsGetPlaced extends AppCompatActivity {

    TextView aboutus,body;
    View view;
    RelativeLayout relativeLayout;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placed);

        aboutus=(TextView)findViewById(R.id.aboutus);
        body=(TextView)findViewById(R.id.body);

        int opacity=1000;

    }
}
