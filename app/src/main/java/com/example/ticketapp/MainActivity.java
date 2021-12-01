package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView iwMovie;
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Ticket> ticketsArrayList;
    private TicketAdaptor adaptor;
    private Database vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iwMovie=findViewById(R.id.iwMovie);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);
        vt=new Database(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ticketsArrayList = new TicketDao().getAllTickets(vt);

        adaptor = new TicketAdaptor(this,ticketsArrayList);

        rv.setAdapter(adaptor);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MoviesActivity.class));
                finish();
            }
        });

    }

   /* @Override  ----2
    public void onBackPressed() {---2

        Intent intent=new Intent(MainActivity.this,MoviesActivity.class);---2
        finish();---2
        startActivity(intent);---2

        //        Intent intent2=new Intent(Intent.ACTION_MAIN);
        //intent2.addCategory(Intent.CATEGORY_HOME);
        //intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent2);
    }*/
}