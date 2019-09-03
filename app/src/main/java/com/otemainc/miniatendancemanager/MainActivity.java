package com.otemainc.miniatendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView email;
    EditText pass;
    Button login, register;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                auth(email,pass);
                break;
            case R.id.btnRegister:
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
                break;
        }
    }

    private void auth(AutoCompleteTextView email, EditText pass) {
    }
}
