package com.otemainc.miniatendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otemainc.miniatendancemanager.db.Db;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public AutoCompleteTextView name,email,dept;
    public EditText pass;
    public Button login,regist;
    public Db myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.txtRName);
        email = findViewById(R.id.txtREmail);
        dept = findViewById(R.id.txtRDept);
        pass = findViewById(R.id.txtRPass);
        login = findViewById(R.id.btnRLogin);
        regist = findViewById(R.id.btnRRegister);
        myDb = new Db(this);
        login.setOnClickListener(this);
        regist.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRRegister:
                boolean userAdded;
               userAdded = myDb.addUser(name.getText().toString(),dept.getText().toString(),email.getText().toString(),pass.getText().toString());
               if(userAdded == true){
                   Toast.makeText(this,"Registration Sucessful",Toast.LENGTH_SHORT).show();
                   showLogin();
               }else{
                   Toast.makeText(this,"Unable to add user",Toast.LENGTH_SHORT).show();
               }
               break;
            case R.id.btnRLogin:
                showLogin();
                break;
        }
    }

    private void showLogin() {
        Intent login = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(login);
        finish();
    }
}
