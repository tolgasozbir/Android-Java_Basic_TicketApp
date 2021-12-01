package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
    private NotificationCompat.Builder builder;

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
                //notification
                customNotifications();
                startActivity(new Intent(MoviesDetailActivity.this,MoviesActivity.class));
                finish();

            }
        });




    }

    public void customNotifications(){

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent=new Intent(MoviesDetailActivity.this,LoginActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivities(this,1, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);

        //Check Sdk Version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            String channelId="channelId";
            String channelName="channelName";
            String channelDefinition="channelDefinition";
            int prioty=notificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            if (channel==null){
                channel = new NotificationChannel(channelId,channelName,prioty);
                channel.setDescription(channelDefinition);
                notificationManager.createNotificationChannel(channel);
            }

            builder = new NotificationCompat.Builder(this,channelId);

            builder.setContentTitle("Thank you");
            builder.setContentText("Don't forget your session time :) check your tickets");
            builder.setSmallIcon(R.drawable.icon_notifycheck);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);

        }else{

            builder = new NotificationCompat.Builder(this);

            builder.setContentTitle("Thank you");
            builder.setContentText("Don't forget your session time :) check your tickets");
            builder.setSmallIcon(R.drawable.icon_notifycheck);
            builder.setAutoCancel(true);
            builder.setContentIntent(pendingIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);

        }

        notificationManager.notify(1,builder.build());

    }

}