package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText userName,password,confirmPassword;
    Database vt;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbarRegister);
        btnRegister = findViewById(R.id.btnRegisterPage);
        userName = findViewById(R.id.textRUserName);
        password = findViewById(R.id.textRPassword);
        confirmPassword = findViewById(R.id.textRConfrimPassword);
        vt=new Database(this);

        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=userName.getText().toString();
                String pass=password.getText().toString();
                String pass2=confirmPassword.getText().toString();

                if(TextUtils.isEmpty(user)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass2)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(!pass.equals(pass2)){
                    Snackbar.make(view,"Passwords do not match, please check",Snackbar.LENGTH_LONG).show();
                    return;
                }

                new UserDao().createAccount(vt,password.getText().toString(),password.getText().toString());
                Snackbar.make(view,"Register Succesful",Snackbar.LENGTH_LONG).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }, 2000);


            }
        });

    }
}