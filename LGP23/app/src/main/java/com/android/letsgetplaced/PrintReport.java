package com.android.letsgetplaced;

import android.graphics.Color;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrintReport extends AppCompatActivity {
    DatabaseReference reference;

    ArrayList<Placed> list;

    TableLayout stk;
    int count = 0;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_report);

        scrollView=(ScrollView)findViewById(R.id.scrollView);
        list = new ArrayList<Placed>();
        stk = (TableLayout) findViewById(R.id.table_main);


        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sr.No ");
        tv0.setHeight(50);
        tv0.setTextColor(Color.BLACK);
        tv0.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv0);
        TextView tv01 = new TextView(this);
        tv01.setText(" Name ");
        tv01.setTextColor(Color.BLACK);
        tv01.setGravity(Gravity.LEFT);
        tv01.setHeight(50);
        tv01.setBackgroundResource(R.drawable.cell_shape);
        tv01.setWidth(500);
        tbrow0.addView(tv01);
        TextView tv1 = new TextView(this);
        tv1.setText(" Organization ");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.BLACK);
        tv1.setHeight(50);
        tv1.setWidth(300);
        tv1.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Branch ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tv2.setHeight(50);
        tv2.setWidth(250);
        tv2.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Package ");
        tv3.setTextColor(Color.BLACK);
        tv3.setHeight(50);
        tv3.setWidth(300);
        tv3.setGravity(Gravity.CENTER);
        tv3.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv3);
        stk.setPadding(50, 50, 50, 50);

        stk.addView(tbrow0);
        stk.setHorizontalScrollBarEnabled(true);


        reference = FirebaseDatabase.getInstance().getReference().child("placed");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Placed p = dataSnapshot1.getValue(Placed.class);
                    count++;
                    init(p, count);
                    list.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PrintReport.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init(Placed p, int count) {
        TableRow tbrow = new TableRow(this);

        TextView t1v = new TextView(this);
        t1v.setText(String.valueOf(count));
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t1v);

        TextView t2v = new TextView(this);
        t2v.setText(p.getSname());
        t2v.setPadding(6, 0, 0, 0);
        t2v.setTextColor(Color.BLACK);
        t2v.setBackgroundResource(R.drawable.cell_shape);
        t2v.setWidth(500);
        tbrow.addView(t2v);

        TextView t3v = new TextView(this);
        t3v.setText(p.getCname());
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        t3v.setBackgroundResource(R.drawable.cell_shape);
        t3v.setWidth(300);
        tbrow.addView(t3v);

        TextView t4v = new TextView(this);
        t4v.setText(p.getBranch());
        t4v.setTextColor(Color.BLACK);
        t4v.setGravity(Gravity.CENTER);
        t4v.setWidth(250);
        t4v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t4v);

        TextView t5v = new TextView(this);
        t5v.setText(p.getSalary());
        t5v.setTextColor(Color.BLACK);
        t5v.setGravity(Gravity.CENTER);
        t5v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t5v);

        tbrow.setBackgroundColor(Color.WHITE);

        stk.addView(tbrow);
        stk.setHorizontalScrollBarEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.print, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_print) {
            pdf();
            stk.setHorizontalScrollBarEnabled(true);




        }

        return super.onOptionsItemSelected(item);
    }
    public void pdf() {

        try {

            scrollView.setDrawingCacheEnabled(true);
            scrollView.measure(android.view.View.MeasureSpec.makeMeasureSpec(0, android.view.View.MeasureSpec.UNSPECIFIED),
                    android.view.View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            scrollView.layout(0, 0, scrollView.getMeasuredWidth(), scrollView.getMeasuredHeight());
            scrollView.buildDrawingCache();
            scrollView.getDrawingCache();


            PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
            printManager.print("print_any_view_job_name", new ViewPrintAdapter(this,
                    findViewById(R.id.scrollView)), null);



        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


}

