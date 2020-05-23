package com.android.letsgetplaced;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Help extends AppCompatActivity {
    FirebaseAuth mauth;
    DatabaseReference mdatabase;
    EditText etques;
    TextView help1,ques;
    FirebaseUser user;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        submit = (Button) findViewById(R.id.submit);
        help1=(TextView)findViewById(R.id.help1);
        ques=(TextView)findViewById(R.id.ques);
        etques = (EditText) findViewById(R.id.etques);
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("students");





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Help.this, "Query Noted Successfully ", Toast.LENGTH_LONG).show();
                String e="tkyproject19@gmail.com";
                String query=etques.getText().toString();
                Intent i=new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:" + e));

                i.putExtra(Intent.EXTRA_SUBJECT,"Student Query");
                i.putExtra(Intent.EXTRA_TEXT,query);
                startActivity(i);

            }



        });
    }
}
