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
        users = new UserDao().getUsers(vt);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=userName.getText().toString();
                String pass=password.getText().toString();
                System.out.println(username);
                System.out.println(pass);

                if (userName.length()==0 || password.length()==0){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                }

                if(TextUtils.isEmpty(username)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }


                vt.close();

                //check user list for developer
                /*for (User u:users) {
                    System.out.println("id : " + u.getId());
                    System.out.println("name : " + u.getUserName());
                    System.out.println("pass : " + u.getPassword());
                }*/

                for(User u:users){
                    if (username.equals(u.getUserName()) && pass.equals(u.getPassword())){
                        utils.loggedUser=u;
                        Intent intent = new Intent(LoginActivity.this,MoviesActivity.class);
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

    //login backpress
    @Override
    public void onBackPressed() {
        Intent intent2=new Intent(Intent.ACTION_MAIN);
        intent2.addCategory(Intent.CATEGORY_HOME);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}