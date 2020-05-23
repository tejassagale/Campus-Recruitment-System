package com.android.letsgetplaced;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Event extends AppCompatActivity {
    TextView tvCompanyDetails,tvCriteria;
    EditText etCompanyName,etJobDesciption,etJobLocation,etAboutCompany,etBranch,etSSC,etHSC,etBECgpi,etKT,etSalary,etLink,etDate;
    Button btnAddCompany;
    ImageButton ibtnLink;
    private DatabaseReference mDatabase;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        context=getApplicationContext();
        FirebaseApp.initializeApp(context);

        tvCompanyDetails=(TextView)findViewById(R.id.tvCompanyDetails);
        tvCriteria=(TextView)findViewById(R.id.tvCriteria);
        etCompanyName=(EditText)findViewById(R.id.etCompanyName);
        etJobDesciption=(EditText)findViewById(R.id.etJobDescription);
        etJobLocation=(EditText)findViewById(R.id.etJobLocation);
        etAboutCompany=(EditText)findViewById(R.id.etAboutCompany);
        etBranch=(EditText)findViewById(R.id.etBranch);
        etSalary=(EditText)findViewById(R.id.etSalary);
        etSSC=(EditText)findViewById(R.id.etSSC);
        etHSC=(EditText)findViewById(R.id.etHSC);
        etBECgpi=(EditText)findViewById(R.id.etBECgpi);
        etKT=(EditText)findViewById(R.id.etKT);
        etDate=(EditText)findViewById(R.id.etDate);
        etLink=(EditText)findViewById(R.id.etLink);
        ibtnLink=(ImageButton)findViewById(R.id.ibtnLink);
        btnAddCompany=(Button)findViewById(R.id.btnAddCompany);


        ibtnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intent);


            }
        });



        btnAddCompany.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String companyname=etCompanyName.getText().toString().trim();
                if(companyname.length()==0)
                {
                    etCompanyName.setError("Enter name of the company");
                    etCompanyName.requestFocus();
                    return;
                }
                final String jobdescription=etJobDesciption.getText().toString().trim();
                if(jobdescription.length()==0)
                {
                    etJobDesciption.setError("Enter the job description");
                    etJobDesciption.requestFocus();
                    return;
                }
                final String joblocation=etJobLocation.getText().toString().trim();
                if(joblocation.length()==0)
                {
                    etJobLocation.setError("Enter the job location");
                    etJobLocation.requestFocus();
                    return;
                }
                final String aboutcompany=etAboutCompany.getText().toString().trim();
                if(aboutcompany.length()==0)
                {
                    etAboutCompany.setError("Enter company details");
                    etAboutCompany.requestFocus();
                    return;
                }
                final String salary=etSalary.getText().toString().trim();
                if(salary.length()==0)
                {
                    etSalary.setError("Enter salary details");
                    etSalary.requestFocus();
                    return;
                }
                final String branch=etBranch.getText().toString().trim();
                if(branch.length()==0)
                {
                    etBranch.setError("Enter the required branch");
                    etBranch.requestFocus();
                    return;
                }
                final String ssc=etSSC.getText().toString().trim();
                if(ssc.length()==0)
                {
                    etSSC.setError("Enter SSC percentage");
                    etSSC.requestFocus();
                    return;
                }
                final String hsc=etHSC.getText().toString().trim();
                if(hsc.length()==0)
                {
                    etHSC.setError("Enter HSC percentage");
                    etHSC.requestFocus();
                    return;
                }
                final String becgpi=etBECgpi.getText().toString().trim();
                if(becgpi.length()==0)
                {
                    etBECgpi.setError("Enter BE Cgpi");
                    etBECgpi.requestFocus();
                    return;
                }
                final String noofkt=etKT.getText().toString().trim();
                if(noofkt.length()==0)
                {
                    etKT.setError("Specify the no of live KT's allowed");
                    etKT.requestFocus();
                    return;
                }
                final String date=etDate.getText().toString().trim();
                if(date.length()==0)
                {
                    etDate.setError("Enter the drive date");
                    etDate.requestFocus();
                    return;
                }
                final String link=etLink.getText().toString().trim();
                if(link.length()==0)
                {
                    etLink.setError("Please copy and paste the registeration link");
                    etLink.requestFocus();
                    return;
                }
                try {
                    mDatabase = FirebaseDatabase.getInstance().getReference("companies");
                    DatabaseReference ref = mDatabase.child(companyname);
                    ref.child("name").setValue(companyname);
                    ref.child("jobdescription").setValue(jobdescription);
                    ref.child("joblocation").setValue(joblocation);
                    ref.child("aboutcompany").setValue(aboutcompany);
                    ref.child("salary").setValue(salary);
                    ref.child("branch").setValue(branch);
                    ref.child("date").setValue(date);
                    ref.child("ssc").setValue(Integer.parseInt(ssc));
                    ref.child("hsc").setValue(Integer.parseInt(hsc));
                    ref.child("becgpi").setValue(Float.parseFloat(becgpi));
                    ref.child("noofKT").setValue(Integer.parseInt(noofkt));
                    ref.child("link").setValue(link);
                    Toast.makeText(Event.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Event.this, AdminActivity.class);
                    startActivity(i);
                    finish();
                }
                catch(Exception e)
                {
                    Toast.makeText(Event.this, "Exception:"+e, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
