package com.otemainc.miniatendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.otemainc.miniatendancemanager.db.Db;

import java.util.Calendar;
import java.util.Date;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Attendance extends AppCompatActivity implements View.OnClickListener {
    EditText staff;
    Button signIn, signOut;
    TextView status;
    Db myDb;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Status = "statusKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        staff= findViewById(R.id.txtNo);
        signIn = findViewById(R.id.btnCheck);
        signOut = findViewById(R.id.btnSignOut);
        status = findViewById(R.id.txtStatus);
        myDb = new Db(this);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        signIn.setOnClickListener(this);
        signOut.setOnClickListener(this);
        if (sharedpreferences.contains(Status)) {
            status.setText(sharedpreferences.getString(Status, ""));
            staff.setVisibility(GONE);
            signIn.setVisibility(GONE);
            signOut.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCheck:
                Date timeIn = Calendar.getInstance().getTime();
                String no = staff.getText().toString().trim();
                boolean signedIn;
               signedIn= myDb.addTimeIn(no,timeIn.toString());
               if(signedIn){
                   SharedPreferences.Editor editor = sharedpreferences.edit();
                   String statusText = "Working";
                   editor.putString(Status, statusText);
                   editor.putString(Name, no);
                   editor.commit();
                   status.setText(statusText);
                   Toast.makeText(this,"Welcome "+ no +" Enjoy you working session", Toast.LENGTH_LONG).show();
                   staff.setVisibility(GONE);
                   signIn.setVisibility(GONE);
                   signOut.setVisibility(VISIBLE);
               }
                break;
            case R.id.btnSignOut:
                boolean signedOut;
                String staffNo = sharedpreferences.getString(Name, "");
                Date timeOut = Calendar.getInstance().getTime();
                signedOut = myDb.addTimeOut(staffNo,timeOut.toString());
                if(signedOut) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.remove(Status);
                    editor.remove(Name);
                    editor.commit();
                    status.setText(R.string.you_have_not_checked_in_yet);
                    Toast.makeText(this, "Good bye Hope to see you soon", Toast.LENGTH_LONG).show();
                    staff.setVisibility(VISIBLE);
                    signIn.setVisibility(VISIBLE);
                    signOut.setVisibility(GONE);
                }
                break;
        }
    }
}
