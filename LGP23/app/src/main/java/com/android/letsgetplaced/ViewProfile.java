package com.android.letsgetplaced;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ViewProfile extends AppCompatActivity {
    FirebaseAuth mauth;
    DatabaseReference mdatabase;
    ProgressBar pg;
    TextView tvname,tvusername,tvSSC,tvHSC,tvBecgpi,tvage,tvgender,tvaddress,tvcontact,tvbranch,tvnoofkt,tvqualification,tvskills,tvyop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        tvname=(TextView)findViewById(R.id.tvname);
        tvusername=(TextView)findViewById(R.id.tvusername);
        tvSSC=(TextView)findViewById(R.id.tvSSC);
        tvHSC=(TextView)findViewById(R.id.tvHSC);
        tvBecgpi=(TextView)findViewById(R.id.tvBecgpi);
        tvage=(TextView)findViewById(R.id.tvage);
        tvgender=(TextView)findViewById(R.id.tvgender);
        tvaddress=(TextView)findViewById(R.id.tvaddress);
        tvcontact=(TextView)findViewById(R.id.tvcontact);
        tvbranch=(TextView)findViewById(R.id.tvbranch);
        tvnoofkt=(TextView)findViewById(R.id.tvnoofkt);
        tvqualification=(TextView)findViewById(R.id.tvqualification);
        tvskills=(TextView)findViewById(R.id.tvskills);
        tvyop=(TextView)findViewById(R.id.tvyop);
        pg=(ProgressBar)findViewById(R.id.pg);
        pg.setVisibility(View.VISIBLE);
        mauth=FirebaseAuth.getInstance();

        mdatabase= FirebaseDatabase.getInstance().getReference().child("students");

        final String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();



        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                float BEcgpi=dataSnapshot.child(uid).child("cgpa").getValue(Float.class);
                tvBecgpi.setText(String.valueOf(BEcgpi));
                int SSC=dataSnapshot.child(uid).child("ssc").getValue(Integer.class);
                tvSSC.setText(String.valueOf(SSC));
                int HSC=dataSnapshot.child(uid).child("hsc").getValue(Integer.class);
                tvHSC.setText(String.valueOf(HSC));
                int NOOFKT=dataSnapshot.child(uid).child("noofkt").getValue(Integer.class);
                tvnoofkt.setText(String.valueOf(NOOFKT));
                String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                tvname.setText(name);
                String username=dataSnapshot.child(uid).child("username").getValue(String.class);
                tvusername.setText(username);
                int age=dataSnapshot.child(uid).child("age").getValue(Integer.class);
                tvage.setText(String.valueOf(age));
                String gender=dataSnapshot.child(uid).child("gender").getValue(String.class);
                tvgender.setText(gender);
                String address=dataSnapshot.child(uid).child("address").getValue(String.class);
                tvaddress.setText(address);
                String contact=dataSnapshot.child(uid).child("contact").getValue(String.class);
                tvcontact.setText(contact);
                String branch=dataSnapshot.child(uid).child("branch").getValue(String.class);
                tvbranch.setText(branch);
                String qualification=dataSnapshot.child(uid).child("qualification").getValue(String.class);
                tvqualification.setText(qualification);
                String skills=dataSnapshot.child(uid).child("skills").getValue(String.class);
                tvskills.setText(skills);
                String yop=dataSnapshot.child(uid).child("yop").getValue(String.class);
                tvyop.setText(yop);
                pg.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }
}
