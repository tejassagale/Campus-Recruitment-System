package com.android.letsgetplaced;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import static com.google.firebase.auth.FirebaseAuth.*;


public class SignuppageActivity extends AppCompatActivity {
TextView tvMasterdata;
EditText etUsername,etPassword,etStudName,etStudAge,etStudAddress,etStudCgpa,etStudGender,etStudYOP,
        etStudQualification,etStud10,etStud12,etStudContact,etStudSkills,etStudBranch,etNoofkt,etErpid;
Button btnSignupStudent;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        context=getApplicationContext();
        FirebaseApp.initializeApp(context);

        tvMasterdata=(TextView)findViewById(R.id.tvMasterdata);
        etStudName=(EditText)findViewById(R.id.etStudName);
        etStudAge=(EditText)findViewById(R.id.etStudAge);
        etStudAddress=(EditText)findViewById(R.id.etStudAddress);
        etStudCgpa=(EditText)findViewById(R.id.etStudCgpa);
        etStudGender=(EditText)findViewById(R.id.etStudGender);
        etStudContact=(EditText)findViewById(R.id.etStudContact);
        etStudYOP=(EditText)findViewById(R.id.etStudYOP);
        etStudQualification=(EditText)findViewById(R.id.etStudQualify);
        etStudSkills=(EditText)findViewById(R.id.etStudSkills);
        etStud10=(EditText)findViewById(R.id.etStud10);
        etStud12=(EditText)findViewById(R.id.etStud12);
        etStudBranch=(EditText)findViewById(R.id.etStudBranch);
        btnSignupStudent=(Button)findViewById(R.id.btnSignupStudent);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etNoofkt=(EditText)findViewById(R.id.etNoofkt);
        etErpid=(EditText)findViewById(R.id.etErpid);
        firebaseAuth = FirebaseAuth.getInstance();
        btnSignupStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=etStudName.getText().toString().trim();
                if(name.length()==0)
                {
                    etStudName.setError("Please enter name");
                    etStudName.requestFocus();

                    return;
                }

                final String username=etUsername.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(username).matches() || username.length()==0)
                {
                    etUsername.setError("Enter Username as Email-id");
                    etUsername.requestFocus();
                    return;
                }

                final String password=etPassword.getText().toString().trim();
                if((password.length()<6) || (password.length()==0))
                {
                    etPassword.setError("Password should be min 6 characters");
                    etPassword.requestFocus();
                    return;
                }
                final String erpid=etErpid.getText().toString().trim();
                if(erpid.length()==0)
                {
                    etErpid.setError("Enter your ERP ID");
                    etErpid.requestFocus();
                    return;
                }

                final String age=etStudAge.getText().toString().trim();
                if(age.length()>3 || age.length()==0)
                {
                    etStudAge.setError("Invalid age");
                    etStudAge.requestFocus();
                    return;
                }

                final String address=etStudAddress.getText().toString();
                if(address.length()==0)
                {
                    etStudAddress.setError("Invalid Address");
                    etStudAddress.requestFocus();
                    return;
                }
                final String branch=etStudBranch.getText().toString().trim();
                if(branch.length()==0)
                {
                    etStudBranch.setError("Please enter name");
                    etStudBranch.requestFocus();
                    return;
                }
                final String cgpa=etStudCgpa.getText().toString().trim();
                if(cgpa.length()==0)
                {
                    etStudCgpa.setError("Invalid Cgpa");
                    etStudCgpa.requestFocus();
                    return;
                }

                final String gender=etStudGender.getText().toString().trim();
                if(gender.length()==0)
                {
                    etStudGender.setError("Please enter your gender");
                    etStudGender.requestFocus();
                    return;
                }

                final String contact=etStudContact.getText().toString().trim();
                if((contact.length()>=11 || contact.length()<=9) || (contact.length()==0 ))
                {
                    etStudContact.setError("Invalid Contact No");
                    etStudContact.requestFocus();
                    return;
                }

                final String year_of_passing=etStudYOP.getText().toString().trim();
                if((year_of_passing.length()>5) || (year_of_passing.length()==0 ) )
                {
                    etStudYOP.setError("Invalid year of passing");
                    etStudYOP.requestFocus();
                    return;
                }

                final String qualification=etStudQualification.getText().toString();
                if(qualification.length()==0)
                {
                    etStudQualification.setError("Please enter your qualification");
                    etStudQualification.requestFocus();
                    return;
                }

                final String skills=etStudSkills.getText().toString();
                if(skills.length()==0)
                {
                    etStudSkills.setError("Please enter your skills");
                    etStudSkills.requestFocus();
                    return;
                }

                final String ssc=etStud10.getText().toString().trim();
                if(ssc.length()==0)
                {
                    etStud10.setError("Please enter your ssc marks");
                    etStud10.requestFocus();
                    return;
                }

                final String hsc=etStud12.getText().toString().trim();
                if(hsc.length()==0)
                {
                    etStud12.setError("Enter your hsc/diploma marks");
                    etStud12.requestFocus();
                    return;
                }
                final String noofkt=etNoofkt.getText().toString().trim();
                if(noofkt.length()==0)
                {
                    etNoofkt.setError("Enter the line no of kt's");
                    etNoofkt.requestFocus();
                    return;
                }



                firebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                                    firebaseAuth.signInWithEmailAndPassword(username,password);
                                    mDatabase = FirebaseDatabase.getInstance().getReference("students");
                                    DatabaseReference currentuserDB=mDatabase.child(firebaseAuth.getCurrentUser().getUid());
                                    currentuserDB.child("name").setValue(name);
                                    currentuserDB.child("username").setValue(username);
                                    //currentuserDB.child("password").setValue(password);
                                    currentuserDB.child("erpid").setValue(Integer.parseInt(erpid));
                                    currentuserDB.child("age").setValue(Integer.parseInt(age));
                                    currentuserDB.child("address").setValue(address);
                                    currentuserDB.child("branch").setValue(branch);
                                    currentuserDB.child("cgpa").setValue(Float.parseFloat(cgpa));
                                    currentuserDB.child("gender").setValue(gender);
                                    currentuserDB.child("contact").setValue(contact);
                                    currentuserDB.child("yop").setValue(year_of_passing);
                                    currentuserDB.child("qualification").setValue(qualification);
                                    currentuserDB.child("skills").setValue(skills);
                                    currentuserDB.child("ssc").setValue(Integer.parseInt(ssc));
                                    currentuserDB.child("hsc").setValue(Integer.parseInt(hsc));
                                    currentuserDB.child("noofkt").setValue(Integer.parseInt(noofkt));




                                    Toast.makeText(SignuppageActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(SignuppageActivity.this,MainActivity.class);
                                    startActivity(i);
                                    finish();



                                } else {

                        if(task.getException() instanceof FirebaseAuthEmailException)
                        {
                            Toast.makeText(getApplicationContext(),"User already registered",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(SignuppageActivity.this,"Error: "+errorMessage,Toast.LENGTH_LONG).show();

                        }
                                }


                            }
                        });


            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to cancel registeration?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Intent in =new Intent(SignuppageActivity.this,LoginpageActivity.class);
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

}
