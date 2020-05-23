package com.android.letsgetplaced;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout adminGrid;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mauth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        adminGrid = (GridLayout) findViewById(R.id.admingrid);

        setSingleEvent(adminGrid);
    }

    public void setSingleEvent(GridLayout adminGrid) {

        for(int i=0;i<adminGrid.getChildCount();i++){

            CardView cardView=(CardView)adminGrid.getChildAt(i);
            final int FinalI=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(FinalI == 0){
                        Intent i =new Intent(AdminActivity.this,Event.class);
                        startActivity(i);
                    }else if(FinalI==1){
                        Intent i =new Intent(AdminActivity.this,Delete.class);
                        startActivity(i);
                    }else if(FinalI==2) {
                        Intent i = new Intent(AdminActivity.this, com.android.letsgetplaced.View.class);
                        startActivity(i);
                    }else if(FinalI==3) {
                        Intent i = new Intent(AdminActivity.this, Masterdata.class);
                        startActivity(i);
                    }else if(FinalI==4) {
                        Intent i = new Intent(AdminActivity.this, Notifiactions.class);
                        startActivity(i);
                    }else if(FinalI==5) {
                        Intent i = new Intent(AdminActivity.this, Report.class);
                        startActivity(i);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to logout?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in =new Intent(AdminActivity.this,LoginpageActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(in);

                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.setTitle("Exit");
        a.show();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cevent) {
            Intent i=new Intent(this,Event.class);
            startActivity(i);
        } else if (id == R.id.vcompany) {
            Intent i=new Intent(this, com.android.letsgetplaced.View.class);
            startActivity(i);
        } else if (id == R.id.vmaster) {
            Intent i=new Intent(this,Masterdata.class);
            startActivity(i);
        } else if (id == R.id.snotify) {
            Intent i=new Intent(this,Notifiactions.class);
            startActivity(i);
        } else if (id == R.id.mreport) {
            Intent i=new Intent(this,Report.class);
            startActivity(i);

        } else if (id == R.id.devent) {
            Intent i=new Intent(this,Delete.class);
            startActivity(i);

        }else if (id == R.id.legacy) {
            Intent i=new Intent(this,Legacy.class);
            startActivity(i);

        }else if (id == R.id.lgp) {
            Intent i=new Intent(this,LetsGetPlaced.class);
            startActivity(i);

        }else if (id == R.id.help) {
            Intent i=new Intent(this,Help.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    }

