package com.android.letsgetplaced;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Delete extends AppCompatActivity implements ItemLongClickListener {
    RecyclerView rvCompany;
    CompanyAdapter adapter1;
    ArrayList<Company> companylist;
    DatabaseReference mdatabase;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        rvCompany=(RecyclerView)findViewById(R.id.rvCompany);
        rvCompany.setLayoutManager(new LinearLayoutManager(this));
        companylist = new ArrayList<Company>();
        pg=(ProgressBar)findViewById(R.id.pg);
        pg.setVisibility(View.VISIBLE);
        //rvCompany.setHasFixedSize(true);

        adapter1 = new CompanyAdapter(this, companylist);
        rvCompany.setAdapter(adapter1);
        adapter1.setItemLongClickListener(this);


        mdatabase= FirebaseDatabase.getInstance().getReference().child("companies");
        mdatabase.addListenerForSingleValueEvent(valueEventListener);
        Query mquery=FirebaseDatabase.getInstance().getReference().child("companies");



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
                pg.setVisibility(View.GONE);


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    @Override
    public void onItemLongClick(final android.view.View v, final int pos) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to remove this company event?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Query mquery=FirebaseDatabase.getInstance().getReference().child("companies");
                mquery.orderByChild("name")
                        .equalTo((String) companylist.get(pos).getName())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChildren()) {
                                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                    firstChild.getRef().removeValue();
                                    v.setVisibility(View.GONE);
                                }
                                adapter1.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                            public void onCancelled(FirebaseError firebaseError) {
                            }
                        });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.setTitle("Delete");
        a.show();
    }
    }


