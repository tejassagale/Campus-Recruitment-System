package com.android.letsgetplaced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity implements ItemClickListener {


    RecyclerView rvCompany;
    CompanyAdapter adapter1;
    ArrayList<Company> companylist;
    DatabaseReference mdatabase;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        rvCompany=(RecyclerView)findViewById(R.id.rvCompany);
        rvCompany.setLayoutManager(new LinearLayoutManager(this));
        companylist = new ArrayList<Company>();
        pg=(ProgressBar)findViewById(R.id.pg);
        pg.setVisibility(android.view.View.VISIBLE);

        //rvCompany.setHasFixedSize(true);

        adapter1 = new CompanyAdapter(this, companylist);
        rvCompany.setAdapter(adapter1);
        adapter1.setClickListener(this);


        mdatabase= FirebaseDatabase.getInstance().getReference().child("companies");
        mdatabase.addListenerForSingleValueEvent(valueEventListener);



    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            companylist.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Company company = snapshot.getValue(Company.class);
                    companylist.add(company);

                }


                adapter1.notifyDataSetChanged();
                pg.setVisibility(android.view.View.GONE);


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    @Override
    public void onClick(android.view.View view, int position) {
        final Company company = companylist.get(position);
        Intent i = new Intent(this, ReportDetails.class);
        i.putExtra("name", company.getName());
        i.putExtra("ssc", company.getSsc() );
        i.putExtra("hsc", company.getHsc());
        i.putExtra("becgpi", company.getBEcgpi());
        i.putExtra("noofkt",company.getNoofkt());

        startActivity(i);

    }
}