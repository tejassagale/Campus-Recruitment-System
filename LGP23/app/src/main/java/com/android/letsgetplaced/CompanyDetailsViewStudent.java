package com.android.letsgetplaced;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyDetailsViewStudent extends AppCompatActivity {
    TextView tvcompanyname,tvJobDescription1,tvJobDescription2,tvJobLocation1,tvJobLocation2,tvAboutCompany1,tvAboutCompany2,
            tvcriteria,tvSalary1,tvSalary2,tvBranch1,tvBranch2,tvSSC1,tvSSC2,tvHSC1,tvHSC2,tvBEcgpi1,tvBEcgpi2,tvNoofkt1,tvNoofkt2,
            tvLink1,tvLink2,tvEligible;
    DatabaseReference mdatabase;
    FirebaseAuth mauth;
    ArrayList<Student> list;
    int counttotal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_view_student);
        tvcompanyname=(TextView)findViewById(R.id.tvcompanyname);
        tvJobDescription1=(TextView)findViewById(R.id.tvJobDescription1);
        tvJobDescription2=(TextView)findViewById(R.id.tvJobDescription2);
        tvJobLocation1=(TextView)findViewById(R.id.tvJobLocation1);
        tvJobLocation2=(TextView)findViewById(R.id.tvJobLocation2);
        tvAboutCompany1=(TextView)findViewById(R.id.tvAboutCompany1);
        tvAboutCompany2=(TextView)findViewById(R.id.tvAboutCompany2);
        tvcriteria=(TextView)findViewById(R.id.tvcriteria);
        tvSalary1=(TextView)findViewById(R.id.tvSalary1);
        tvSalary2=(TextView)findViewById(R.id.tvSalary2);
        tvBranch1=(TextView)findViewById(R.id.tvBranch1);
        tvBranch2=(TextView)findViewById(R.id.tvBranch2);
        tvSSC1=(TextView)findViewById(R.id.tvSSC1);
        tvSSC2=(TextView)findViewById(R.id.tvSSC2);
        tvHSC1=(TextView)findViewById(R.id.tvHSC1);
        tvHSC2=(TextView)findViewById(R.id.tvHSC2);
        tvBEcgpi1=(TextView)findViewById(R.id.tvBEcgpi1);
        tvBEcgpi2=(TextView)findViewById(R.id.tvBEcgpi2);
        tvNoofkt1=(TextView)findViewById(R.id.tvNoofkt1);
        tvNoofkt2=(TextView)findViewById(R.id.tvNoofkt2);
        tvLink1=(TextView)findViewById(R.id.tvLink1);
        tvLink2=(TextView)findViewById(R.id.tvLink2);
        tvEligible=(TextView)findViewById(R.id.tvEligible);

        mauth=FirebaseAuth.getInstance();
        list=new ArrayList<Student>();

        Intent i=getIntent();
        String companyname=i.getStringExtra("name");
        String jobdescription=i.getStringExtra("jobdescription");
        String joblocation=i.getStringExtra("joblocation");
        String aboutcompany=i.getStringExtra("aboutcompany");
        String salary=i.getStringExtra("salary");
        final int ssc=i.getIntExtra("ssc",0);
        String branch=i.getStringExtra("branch");
        final int hsc=i.getIntExtra("hsc",0);
        final float becgpi=i.getFloatExtra("becgpi",0);
        final int noofkt=i.getIntExtra("noofkt",0);
        String link=i.getStringExtra("link");


        tvcompanyname.setText(companyname);
        tvJobDescription2.setText(jobdescription);
        tvJobLocation2.setText(joblocation);
        tvAboutCompany2.setText(aboutcompany);
        tvSalary2.setText(salary);
        tvSSC2.setText(String.valueOf(ssc));
        tvHSC2.setText(String.valueOf(hsc));
        tvBranch2.setText(branch);
        tvBEcgpi2.setText(String.valueOf(becgpi));
        tvNoofkt2.setText(String.valueOf(noofkt));
        tvLink2.setText(link);
        tvLink2.setClickable(true);
        tvLink2.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='"+link+"'>"+link+"</a>";
        tvLink2.setText(Html.fromHtml(text));

        mdatabase= FirebaseDatabase.getInstance().getReference().child("students");

        final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();



        mdatabase.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                float BEcgpi=dataSnapshot.child(uid).child("cgpa").getValue(Float.class);
                int SSC=dataSnapshot.child(uid).child("ssc").getValue(Integer.class);
                int HSC=dataSnapshot.child(uid).child("hsc").getValue(Integer.class);
                int NOOFKT=dataSnapshot.child(uid).child("noofkt").getValue(Integer.class);


                if(BEcgpi>=becgpi && SSC>=ssc && HSC>=hsc && NOOFKT<=noofkt)
                {
                    tvEligible.setText("You are eligible to apply");
                    tvEligible.setTextColor(Color.GREEN);

                }
                else
                {
                    tvEligible.setText("You are not eligible to apply");
                    tvEligible.setTextColor(Color.RED);
                    tvLink1.setVisibility(View.GONE);
                    tvLink2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });




            }


    }


