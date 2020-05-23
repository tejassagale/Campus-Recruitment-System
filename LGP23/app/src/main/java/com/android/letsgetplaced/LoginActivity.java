package com.android.letsgetplaced;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
ImageView ivLogo;
Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivLogo=(ImageView)findViewById(R.id.ivLogo);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnSignup=(Button)findViewById(R.id.btnSignup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,LoginpageActivity.class);
                startActivity(i);
            }
        });

btnSignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(LoginActivity.this,SignuppageActivity.class);
        startActivity(i);


    }
});
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.setTitle("Exit");
        a.show();
    }

}
