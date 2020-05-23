package com.android.letsgetplaced;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Masterdata extends AppCompatActivity {

    DatabaseReference reference;

    ArrayList<Student> list;
    TableLayout stk;
    int count = 0;
    ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdata);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        list = new ArrayList<Student>();
        stk = (TableLayout) findViewById(R.id.table_main);


        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sr.No ");
        tv0.setTextColor(Color.BLACK);
        tv0.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv0);
        TextView tv01 = new TextView(this);
        tv01.setText(" ERP ID ");
        tv01.setTextColor(Color.BLACK);
        tv01.setGravity(Gravity.CENTER);
        tv01.setBackgroundResource(R.drawable.cell_shape);
        tv01.setWidth(260);
        tbrow0.addView(tv01);
        TextView tv1 = new TextView(this);
        tv1.setText(" Name ");
        tv1.setTextColor(Color.BLACK);
        tv1.setWidth(500);
        tv1.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Contact ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tv2.setWidth(300);
        tv2.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Branch ");
        tv3.setTextColor(Color.BLACK);
        tv3.setWidth(250);
        tv3.setGravity(Gravity.CENTER);
        tv3.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" CGPI ");
        tv4.setTextColor(Color.BLACK);
        tv4.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText(" SSC ");
        tv5.setTextColor(Color.BLACK);
        tv5.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv5);
        TextView tv6 = new TextView(this);
        tv6.setText(" HSC ");
        tv6.setTextColor(Color.BLACK);
        tv6.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv6);
        stk.setPadding(50, 50, 50, 50);

        stk.addView(tbrow0);

        reference = FirebaseDatabase.getInstance().getReference().child("students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Student s = dataSnapshot1.getValue(Student.class);
                    count++;
                    init(s, count);
                    list.add(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Masterdata.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init(Student s, int count) {
        TableRow tbrow = new TableRow(this);
        TextView t1v = new TextView(this);
        t1v.setText(String.valueOf(count));

        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t1v);

        TextView t10v = new TextView(this);
        t10v.setText(String.valueOf(s.getErpid()));
        t10v.setTextColor(Color.BLACK);
        t10v.setGravity(Gravity.CENTER);
        t10v.setWidth(260);
        t10v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t10v);

        TextView t2v = new TextView(this);
        t2v.setText(s.getname());
        t2v.setPadding(6, 0, 0, 0);
        t2v.setTextColor(Color.BLACK);
        t2v.setBackgroundResource(R.drawable.cell_shape);
        t2v.setWidth(500);
        tbrow.addView(t2v);
        TextView t3v = new TextView(this);
        t3v.setText(s.getContact());
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        t3v.setBackgroundResource(R.drawable.cell_shape);
        t3v.setWidth(300);

        tbrow.addView(t3v);
        TextView t4v = new TextView(this);
        t4v.setText(s.getBranch());
        t4v.setTextColor(Color.BLACK);
        t4v.setGravity(Gravity.CENTER);
        t4v.setWidth(250);
        t4v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t4v);
        TextView t5v = new TextView(this);
        t5v.setText(String.valueOf(s.getCgpa()));
        t5v.setTextColor(Color.BLACK);
        t5v.setGravity(Gravity.CENTER);
        t5v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t5v);
        TextView t6v = new TextView(this);
        t6v.setText(String.valueOf(s.getSSC()));
        t6v.setTextColor(Color.BLACK);
        t6v.setGravity(Gravity.CENTER);
        t6v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t6v);
        TextView t7v = new TextView(this);
        t7v.setText(String.valueOf(s.getHSC()));
        t7v.setTextColor(Color.BLACK);
        t7v.setGravity(Gravity.CENTER);
        t7v.setBackgroundResource(R.drawable.cell_shape);
        tbrow.addView(t7v);
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



