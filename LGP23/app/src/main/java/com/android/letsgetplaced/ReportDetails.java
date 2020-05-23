package com.android.letsgetplaced;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReportDetails extends AppCompatActivity {
    TextView tvcname,tvTotal,tvEligible,tvPlaced,tvPlacedStudents;
    DatabaseReference mdatabase;
    FirebaseAuth mauth;
    ArrayList<Student> list;
    ArrayList<Placed> list1;
    int counttotal=0;
    int count=0;
    TableLayout stk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);

        tvcname=(TextView)findViewById(R.id.tvcname);
        tvTotal=(TextView)findViewById(R.id.tvTotal);
        tvEligible=(TextView)findViewById(R.id.tvEligible);
        tvPlaced=(TextView)findViewById(R.id.tvPlaced);
        tvPlacedStudents=(TextView)findViewById(R.id.tvPlacedStudents);


        mauth=FirebaseAuth.getInstance();
        list=new ArrayList<Student>();
        list1=new ArrayList<Placed>();

        Intent i=getIntent();
        final String companyname=i.getStringExtra("name");
        tvcname.setText(companyname);
        final int ssc=i.getIntExtra("ssc",0);
        final int hsc=i.getIntExtra("hsc",0);
        float becgpi=i.getFloatExtra("becgpi",0);
        final int noofkt=i.getIntExtra("noofkt",0);


        mdatabase= FirebaseDatabase.getInstance().getReference().child("students");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    counttotal=(int) dataSnapshot.getChildrenCount();
                    tvTotal.setText(String.valueOf(counttotal));
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
                        tvEligible.setText(String.valueOf(list.size()));

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query1=FirebaseDatabase.getInstance().getReference("placed")
                .orderByChild("cname").equalTo(companyname);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Placed p = snapshot.getValue(Placed.class);
                        if(p.getCname().equals(companyname) ) {

                            count++;
                            init(p, count);
                            list1.add(p);

                        }


                    }

                }
                tvPlaced.setText(String.valueOf(list1.size()));
                if(list1.size()==0)
                {
                    tvPlacedStudents.setText("No students Placed");
                    stk.setVisibility(View.GONE);
                }
                else
                {
                    tvPlacedStudents.setText("Placed Students");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


            tvPlacedStudents.setText("Placed Students :");
            stk = (TableLayout) findViewById(R.id.table_main);
            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText(" Sr.No ");
            tv0.setTextColor(Color.BLACK);
            tv0.setBackgroundResource(R.drawable.cell_shape);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText(" Name ");
            tv1.setTextColor(Color.BLACK);
            tv1.setWidth(500);
            tv1.setBackgroundResource(R.drawable.cell_shape);
            tbrow0.addView(tv1);

            stk.addView(tbrow0);




    }
    public void init(Placed s, int count) {
        TableRow tbrow = new TableRow(this);
        TextView t1v = new TextView(this);
        t1v.setText(String.valueOf(count));

        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t1v);



        TextView t2v = new TextView(this);
        t2v.setText(s.getSname());
        t2v.setPadding(6, 0, 0, 0);
        t2v.setTextColor(Color.BLACK);
        t2v.setBackgroundResource(R.drawable.cell_shape);
        t2v.setWidth(500);
        tbrow.addView(t2v);

        tbrow.setBackgroundColor(Color.WHITE);

        stk.addView(tbrow);
        stk.setHorizontalScrollBarEnabled(true);
    }
}
