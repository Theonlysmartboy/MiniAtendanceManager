package com.otemainc.miniatendancemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    CardView account, checkIn,checkOut,task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        account = findViewById(R.id.account);
        checkIn = findViewById(R.id.checkIn);
        checkOut = findViewById(R.id.checkOut);
        task = findViewById(R.id.task);
        account.setOnClickListener(this);
        checkIn.setOnClickListener(this);
        checkOut.setOnClickListener(this);
        task.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account:
                Toast.makeText(this,"Coming soon in the next Update",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkIn:
                showAttendance();
                break;
            case R.id.checkOut:
                Intent attend = new Intent(HomeActivity.this,Attendance.class);
                startActivity(attend);
                break;
            case R.id.task:
                Toast.makeText(this,"Coming soon in the next Update",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showAttendance() {
        Intent attend = new Intent(HomeActivity.this,Attendance.class);
        startActivity(attend);
        finish();
    }
}
