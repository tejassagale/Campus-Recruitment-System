package com.android.letsgetplaced;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity {
EditText etUsername;
TextView tvUsername;
Button btnResetpwd;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        etUsername=(EditText)findViewById(R.id.etUsername);
        tvUsername=(TextView)findViewById(R.id.tvUsername);
        btnResetpwd=(Button)findViewById(R.id.btnResetpwd);
        firebaseAuth=FirebaseAuth.getInstance();

        btnResetpwd.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View view) {

        String email = etUsername.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgotpassword.this, "Reset password link sent to registered Email id!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Forgotpassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }
});



    }
}
