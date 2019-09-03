package com.otemainc.miniatendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otemainc.miniatendancemanager.db.Db;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView email;
    EditText pass;
    Button login, register;
    Db mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPwd);
        login = findViewById(R.id.btnLogin);
        register= findViewById(R.id.btnRegister);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        mydb = new Db(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                auth(email.getText().toString(),pass.getText().toString());
                break;
            case R.id.btnRegister:
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
                break;
        }
    }

    private void auth(String email, String pass) {
        Cursor res = mydb.Login(email,pass);
               if(res.getCount()>0){
                   Toast.makeText(this,"Login successfull",Toast.LENGTH_SHORT).show();
                   Intent main = new Intent(MainActivity.this, HomeActivity.class);
                   startActivity(main);
                   finish();
               }else {
                   Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
                   finish();
               }


    }
}
