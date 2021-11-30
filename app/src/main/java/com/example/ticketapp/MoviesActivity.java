package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MoviesActivity extends AppCompatActivity {

    ImageView mInception;
    ImageView mInterstellar;
    ImageView mParasite;
    ImageView mJoker;
    Toolbar toolbar;
    Button btnMyTickets;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        toolbar=findViewById(R.id.toolbarMovies);
        mInception=findViewById(R.id.iwInception);
        mInterstellar=findViewById(R.id.iwInterstellar);
        mParasite=findViewById(R.id.iwParasite);
        mJoker=findViewById(R.id.iwJoker);
        btnMyTickets=findViewById(R.id.btnMyTickets);

        toolbar.setTitle("Movies");
        setSupportActionBar(toolbar);

        Movie movie=new Movie();

        mInception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setId(1);
                movie.setName("Inception");
                movie.setDescription("A thief who steals corporate secrets through the use of dream-sharing technology " +
                        "is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may " +
                        "doom the project and his team to disaster.");

                Intent intent=new Intent(MoviesActivity.this,MoviesDetailActivity.class);
                intent.putExtra("movieObj",movie);
                startActivity(intent);
            }
        });

        mInterstellar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setId(2);
                movie.setName("Interstellar");
                movie.setDescription("Interstellar is a 2014 epic science fiction film co-written, " +
                        "directed and produced by Christopher Nolan. It stars Matthew McConaughey, " +
                        "Anne Hathaway, Jessica Chastain, Bill Irwin, Ellen Burstyn, and Michael Caine. " +
                        "Set in a dystopian future where humanity is struggling to survive, the film " +
                        "follows a group of astronauts who travel through a wormhole near Saturn in search of a new home for mankind.");

                Intent intent=new Intent(MoviesActivity.this,MoviesDetailActivity.class);
                intent.putExtra("movieObj",movie);
                startActivity(intent);
            }
        });

        mParasite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setId(3);
                movie.setName("Parasite");
                movie.setDescription("Parasite follows the Kim family struggling to make ends meet until " +
                        "son Ki-woo comes across an unexpected opportunity. With no experience teaching English, " +
                        "he decides to accept a job tutoring Da-hye, a daughter from the wealthy Park family.");

                Intent intent=new Intent(MoviesActivity.this,MoviesDetailActivity.class);
                intent.putExtra("movieObj",movie);
                startActivity(intent);
            }
        });

        mJoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setId(2);
                movie.setName("Joker");
                movie.setDescription("Joker 2019 is a genuinely sensational film by Todd Phillips. " +
                        "It is a tragic film where Joaquín Phoenix depicts Arthur Fleck, a harassed, bullied, " +
                        "disappointed, wiped out man who turns into Joker, a brutal, violent and boundless character");

                Intent intent=new Intent(MoviesActivity.this,MoviesDetailActivity.class);
                intent.putExtra("movieObj",movie);
                startActivity(intent);
            }
        });

        btnMyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoviesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}