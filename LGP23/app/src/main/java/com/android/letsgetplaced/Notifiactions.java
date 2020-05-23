package com.android.letsgetplaced;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class Notifiactions extends AppCompatActivity {

    FirebaseAuth mauth;
   // FirebaseUser user;
    DatabaseReference users;
//    public static FirebaseDatabase mDatabase;
    static String LoggedIn_User_Email;
    Button sendNotifications;
    TextView notification;
    String mDisplayName;

    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiactions);

        OneSignal.startInit(this).init();
        LoggedIn_User_Email = "tejassagale1985@gmail.com";

        mauth= FirebaseAuth.getInstance();
 //      user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null){
//            mDisplayName = user.getDisplayName();
//            //LoggedIn_User_Email = user.get
//        }

//        mauth=FirebaseAuth.getInstance();
        users= FirebaseDatabase.getInstance().getReference().child("students");
//
//
//
//
//
//
//
////        if (ViewNotifications.mDatabase == null) {
////            ViewNotifications.mDatabase = FirebaseDatabase.getInstance();
////            //mDatabase.setPersistenceEnabled(true);
////            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
////        }
//
////        users = FirebaseDatabase.getInstance().getReference().child("students").child(mauth.getCurrentUser().getusername());


      //  Toast.makeText(Notifiactions.this,"user name  "+LoggedIn_User_Email,Toast.LENGTH_LONG).show();
        OneSignal.sendTag("User_ID", LoggedIn_User_Email);
//        Log.d("LOGGED", "user: " + LoggedIn_User_Email);
//
//        Log.d("LOGGED", "user: " + user);
//        if (user != null) {
//
//            // notification.setText("Welcome, " + mDisplayName);
//        }

        sendNotifications=(Button)findViewById(R.id.send);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifications();

            }
        });
        //sendNotifications("@gmail.com");

            }


    public void sendNotifications()
    {
        Toast.makeText(Notifiactions.this,"Notifications sent successfully ",Toast.LENGTH_LONG).show();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                   String send_email="tejassagale1985@gmail.com";

                    //This is a Simple Logic to Send Notification different Device Programmatically....
//                    if (AdminActivity.LoggedIn_User_Email.equals("tpoltce@gmail.com")) {
                       send_email ="tejassagale1985@gmail.com";
//                  } else {
//                        send_email = "tpoltce@gmail.com";
//                   }

                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic NTkwZWU4OTAtM2Q5Zi00OTBiLTgzZTktMDYzNDY0NmZmOWJi");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"0616283c-c835-4614-9544-c1ad9ca8fa8e\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"New Company Added\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }
}
