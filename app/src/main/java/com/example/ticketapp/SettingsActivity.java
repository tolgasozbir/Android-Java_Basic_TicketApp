package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbarSettings;
    private TextView txtSettingsUsername;
    private EditText txtboxOldPass,txtboxNewPass;
    private Button btnSave,btnDeleteAccount;
    private Database vt;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbarSettings=findViewById(R.id.toolbarSettings);
        txtSettingsUsername=findViewById(R.id.txtSettingsUsername);
        txtboxOldPass=findViewById(R.id.txtboxOldPass);
        txtboxNewPass=findViewById(R.id.txtboxNewPass);
        btnSave=findViewById(R.id.btnSave);
        btnDeleteAccount=findViewById(R.id.btnDeleteAccount);
        vt=new Database(this);

        toolbarSettings.setTitle("Settings");
        setSupportActionBar(toolbarSettings);

        user=utils.loggedUser;
        txtSettingsUsername.setText(user.getUserName());

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String oldPass=txtboxOldPass.getText().toString();
                String newPass=txtboxNewPass.getText().toString();
                System.out.println(oldPass);
                System.out.println(newPass);

                if (TextUtils.isEmpty(oldPass)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(newPass)){
                    Snackbar.make(view,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (!oldPass.equals(user.getPassword())){
                    Snackbar.make(view,"You entered the old password incorrectly.",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (oldPass.equals(newPass)){
                    Snackbar.make(view,"cannot be the same as the old password",Snackbar.LENGTH_LONG).show();
                    return;
                }

                new UserDao().changePassword(vt,user.getId(),newPass);
                Snackbar.make(view,"Succesful",Snackbar.LENGTH_LONG).show();
                utils.loggedUser.setPassword(newPass);
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(toolbarSettings,"Your account will be deleted, do you confirm? ",Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        utils.loggedUser=null;
                        new UserDao().deleteAccount(vt,user.getId());
                        startActivity(new Intent(SettingsActivity.this,LoginActivity.class));
                        finish();
                    }
                }).show();

            }
        });


    }
}