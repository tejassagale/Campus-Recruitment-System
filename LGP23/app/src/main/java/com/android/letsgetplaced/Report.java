package com.android.letsgetplaced;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity {
    EditText etcompanyname,eterpid;
    DatabaseReference ref1,ref2,mdatabase;
    Button btnsubmit,btnViewreport,btnCompanyReport;
    String sname,sbranch,cname,salary;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        etcompanyname=(EditText)findViewById(R.id.etcompanyname);
        eterpid=(EditText)findViewById(R.id.eterpid);
        btnsubmit=(Button)findViewById(R.id.btnsubmit);
        btnViewreport=(Button)findViewById(R.id.btnViewreport);
        btnCompanyReport=(Button)findViewById(R.id.btnCompanyReport);
        pg=(ProgressBar)findViewById(R.id.pg);
        pg.setVisibility(View.GONE);

        mdatabase=FirebaseDatabase.getInstance().getReference("placed");
        ref1= FirebaseDatabase.getInstance().getReference().child("students");
        ref2= FirebaseDatabase.getInstance().getReference().child("companies");

        btnCompanyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Report.this,ReportActivity.class);
                startActivity(i);
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                String erpid=eterpid.getText().toString().trim();
                if(erpid.length()==0)
                {
                    eterpid.setError("Please enter student ERP Id");
                    eterpid.requestFocus();
                    return;
                }
                 cname=etcompanyname.getText().toString().trim();
                if(cname.length()==0)
                {
                    etcompanyname.setError("Enter company name");
                    etcompanyname.requestFocus();
                    return;
                }
                int studerp=Integer.parseInt(erpid);

                Query query=ref1.orderByChild("erpid").equalTo(studerp);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Student s = snapshot.getValue(Student.class);
                                sname=s.getname();
                                sbranch=s.getBranch();

                            }
                        }
                        else
                        {
                            Toast.makeText(Report.this, "Erpid does not exits", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                     Query query1 = ref2.orderByChild("name").equalTo(cname);
                     query1.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                             if (dataSnapshot.exists()) {
                                 for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                     Company company = snapshot.getValue(Company.class);
                                     salary = company.getSalary();

                                 }
                             } else {
                                 Toast.makeText(Report.this, "Company name does not exists", Toast.LENGTH_SHORT).show();
                                 pg.setVisibility(View.GONE);
                             }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });






                    mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (!dataSnapshot.hasChild(sname)) {
                                    DatabaseReference currentuser=mdatabase.child(sname);
                                    currentuser.child("sname").setValue(sname);
                                    currentuser.child("cname").setValue(cname);
                                    currentuser.child("branch").setValue(sbranch);
                                    currentuser.child("salary").setValue(salary);
                                    Toast.makeText(Report.this, "Added successfully", Toast.LENGTH_SHORT).show();

                                    pg.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(Report.this, "Student Already exists", Toast.LENGTH_SHORT).show();
                                    pg.setVisibility(View.GONE);

                                }
                            }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

        });

       btnViewreport.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(Report.this,PrintReport.class);
               startActivity(i);


           }
       });


    }


}
