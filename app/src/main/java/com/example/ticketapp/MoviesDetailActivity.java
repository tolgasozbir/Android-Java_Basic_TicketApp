package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MoviesDetailActivity extends AppCompatActivity {

    ImageView iw;
    private Movie movie;
    TextView textName,textDesc;
    Button btnBuy;
    Database vt;
    String[] items={"14:30","16:00","17:30","19:00","21:30"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        iw=findViewById(R.id.iwDetail);
        textName=findViewById(R.id.textName);
        textDesc=findViewById(R.id.textDesc);
        btnBuy=findViewById(R.id.btnBuyTicket);
        vt=new Database(this);

        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        adapterItems=new ArrayAdapter<>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clock=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),clock+" Choosed",Toast.LENGTH_SHORT).show();
            }
        });

        movie = (Movie) getIntent().getSerializableExtra("movieObj");
        textName.setText(movie.getName());
        textDesc.setText(movie.getDescription());

        String mDrawableName=movie.getName().toLowerCase(Locale.ENGLISH);
        int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        iw.setImageResource(resID);



        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clock==null){
                    Snackbar.make(view,"Choose a time",Snackbar.LENGTH_LONG).show();
                    return;
                }

                new TicketDao().buyTicket(vt,movie.getName(),clock);

                startActivity(new Intent(MoviesDetailActivity.this,MoviesActivity.class));
                finish();

            }
        });




    }

}