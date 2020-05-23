package com.android.letsgetplaced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {
    TextView tvupdate;
    EditText etname,etcontact,etaddress,etage,etcgpi,etskills;
    Button btnupdate;
    DatabaseReference mdatabase;
    FirebaseAuth mauth;

    public String Name,Username,Address,Contact,SkillsKnown,Cgpi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tvupdate=(TextView)findViewById(R.id.tvupdate);
        etname=(EditText)findViewById(R.id.etname);
        etage=(EditText)findViewById(R.id.etage);
        etaddress=(EditText)findViewById(R.id.etaddress);
        etcontact=(EditText)findViewById(R.id.etcontact);
        etcgpi=(EditText)findViewById(R.id.etcgpi);
        etskills=(EditText)findViewById(R.id.etskills);
        btnupdate=(Button)findViewById(R.id.btnupdate);

        mauth=FirebaseAuth.getInstance();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("students").child(mauth.getCurrentUser().getUid());
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name=etname.getText().toString().trim();
                if(!name.isEmpty())
                {
                    mdatabase.child("name").setValue(name);
                }
                String age=etage.getText().toString().trim();
                if(!age.isEmpty())
                {
                    mdatabase.child("age").setValue(Integer.parseInt(age));
                }
                String address=etaddress.getText().toString().trim();
                if(!address.isEmpty())
                {
                    mdatabase.child("address").setValue(address);
                }
                String contact=etcontact.getText().toString().trim();
                if(!contact.isEmpty())
                {
                    if(contact.length()>=11 || contact.length()<=9)
                    {
                        etcontact.setError("Invalid contact");
                        return;
                    }
                    else
                    {
                        mdatabase.child("contact").setValue(contact);
                    }
                }
                String cgpi=etcgpi.getText().toString().trim();
                if(!cgpi.isEmpty())
                {
                    mdatabase.child("cgpa").setValue(Float.parseFloat(cgpi));
                }
                String skills=etskills.getText().toString().trim();
                if(!skills.isEmpty())
                {
                    mdatabase.child("skills").setValue(skills);
                }

                if((!name.isEmpty()) || (!age.isEmpty()) || (!address.isEmpty()) || (!contact.isEmpty()) || (!cgpi.isEmpty()) || (!skills.isEmpty()) )
                {
                    Toast.makeText(UpdateProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpdateProfile.this, "Enter details to update", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


}