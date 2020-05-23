package com.android.letsgetplaced;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.onesignal.OneSignal;

import static com.android.letsgetplaced.Notifiactions.LoggedIn_User_Email;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout studGrid;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OneSignal.startInit(this).init();

        OneSignal.sendTag("User_ID", LoggedIn_User_Email);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        studGrid=(GridLayout)findViewById(R.id.studgrid);

        setSingleEvent(studGrid);
    }

    private void setSingleEvent(GridLayout studGrid) {

        for (int i = 0; i < studGrid.getChildCount(); i++) {

            CardView cardView = (CardView) studGrid.getChildAt(i);
            final int FinalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FinalI == 0) {
                        Intent i = new Intent(MainActivity.this, Companylist.class);
                        startActivity(i);
                    } else if (FinalI == 1) {
                        Intent i = new Intent(MainActivity.this, UpdateProfile.class);
                        startActivity(i);
                    } else if (FinalI == 2) {
                        Intent i = new Intent(MainActivity.this, ViewNotifications.class);
                        startActivity(i);
                    } else if (FinalI == 3) {
                        Intent i = new Intent(MainActivity.this, ResumeActivity.class);
                        startActivity(i);
                    } else if (FinalI == 4) {
                        Intent i = new Intent(MainActivity.this, Help.class);
                        startActivity(i);
                    }else if (FinalI == 5) {
                        Intent i = new Intent(MainActivity.this, ViewProfile.class);
                        startActivity(i);
                    }

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //if (drawer.isDrawerOpen(GravityCompat.START)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to logout?");


            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent in =new Intent(MainActivity.this,LoginpageActivity.class);
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
            /*drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }*/
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.list) {
            Intent list = new Intent(this, Companylist.class);
            startActivity(list);

        } else if (id == R.id.notify) {
            Intent notify = new Intent(this, ViewNotifications.class);
            startActivity(notify);

        } else if (id == R.id.profile) {
            Intent profile = new Intent(this, UpdateProfile.class);
            startActivity(profile);


        }else if (id == R.id.resume) {
            Intent resume = new Intent(this, ResumeActivity.class);
            startActivity(resume);


        }else if (id == R.id.view) {
            Intent resume = new Intent(this, ViewProfile.class);
            startActivity(resume);


        }
        else if (id == R.id.legacy) {
            Intent legacy = new Intent(this, Legacy.class);
            startActivity(legacy);

        } else if (id == R.id.about) {
            Intent about = new Intent(this, LetsGetPlaced.class);
            startActivity(about);

        }else if (id == R.id.help) {
            Intent help = new Intent(this, Help.class);
            startActivity(help);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    }

