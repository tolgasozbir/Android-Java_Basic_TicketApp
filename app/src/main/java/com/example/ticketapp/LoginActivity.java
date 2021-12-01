package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button signIn,register;
    EditText userName,password;
    Database vt;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vt=new Database(this);
        signIn=findViewById(R.id.btnSignIn);
        register=findViewById(R.id.btnRegister);
        userName=findViewById(R.id.textLoginUserName);
        password=findViewById(R.id.textLoginPassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=userName.getText().toString();
                String pass=password.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                users = new UserDao().getUsers(vt);
                for(User u:users){
                    if (username.equals(u.getUserName()) && pass.equals(u.getPassword())){
                        Intent intent = new Intent(LoginActivity.this,MoviesActivity.class);
                        intent.putExtra("usr",u);
                        finish();
                        startActivity(intent);
                        break;
                    }else {
                        Snackbar.make(view,"Wrong username or password",Snackbar.LENGTH_LONG).show();
                    }

                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}