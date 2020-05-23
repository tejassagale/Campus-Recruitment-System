package com.android.letsgetplaced;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CompanyDetailsView extends AppCompatActivity {
TextView tvcompanyname,tvJobDescription1,tvJobDescription2,tvJobLocation1,tvJobLocation2,tvAboutCompany1,tvAboutCompany2,
        tvcriteria,tvSalary1,tvSalary2,tvBranch1,tvBranch2,tvSSC1,tvSSC2,tvHSC1,tvHSC2,tvBEcgpi1,tvBEcgpi2,tvNoofkt1,tvNoofkt2,
        tvLink1,tvLink2,tvTotal1,tvTotal2,tvEligible1,tvEligible2;
 DatabaseReference mdatabase;
 FirebaseAuth mauth;
 ArrayList<Student> list;
 int counttotal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_view);
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
        tvTotal1=(TextView)findViewById(R.id.tvTotal1);
        tvTotal2=(TextView)findViewById(R.id.tvTotal2);
        tvEligible1=(TextView)findViewById(R.id.tvEligible1);
        tvEligible2=(TextView)findViewById(R.id.tvEligible2);

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
        float becgpi=i.getFloatExtra("becgpi",0);
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

        mdatabase=FirebaseDatabase.getInstance().getReference().child("students");
       mdatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               if(dataSnapshot.exists())
               {
                 counttotal=(int) dataSnapshot.getChildrenCount();
                 tvTotal2.setText(String.valueOf(counttotal));
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        Query query=FirebaseDatabase.getInstance().getReference("students")
                .orderByChild("cgpa").startAt(becgpi);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Student student = snapshot.getValue(Student.class);
                        if(student.getSSC()>=ssc && student.getHSC()>=hsc && student.getNoofkt()<=noofkt ) {
                            list.add(student);
                        }
                        tvEligible2.setText(String.valueOf(list.size()));

                    }
            }


        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

