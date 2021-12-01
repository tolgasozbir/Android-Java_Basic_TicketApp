package com.example.ticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextEventDetail,editTextCustomerDetail;
    private Ticket ticket;
    private ImageView iwDetailTicketPic;
    private Database vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iwDetailTicketPic=findViewById(R.id.iwDetailTicketPic);
        toolbar=findViewById(R.id.toolbarEdit);
        editTextEventDetail=findViewById(R.id.editTextSessionDetail);
        editTextCustomerDetail=findViewById(R.id.editTextMovieNameDetail);

        vt=new Database(this);

        ticket = (Ticket) getIntent().getSerializableExtra("object");

        editTextEventDetail.setText(ticket.getMovieName());
        editTextCustomerDetail.setText(ticket.getClock());
        iwDetailTicketPic.setImageResource(this.getResources().getIdentifier(ticket.getMovieName().toLowerCase(Locale.ENGLISH),"drawable",this.getPackageName()));

        toolbar.setTitle("Ticket Details");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Action_Update:
                String eventName=editTextEventDetail.getText().toString().trim();
                String customerName=editTextCustomerDetail.getText().toString().trim();

                /*
                if(TextUtils.isEmpty(eventName)){
                    Snackbar.make(toolbar,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return false;
                }

                if(TextUtils.isEmpty(customerName)){
                    Snackbar.make(toolbar,"field cannot be empty",Snackbar.LENGTH_LONG).show();
                    return false;
                }

                //new TicketDAL().updateTicket(vt,ticket.getTicketId(),eventName,customerName);

                 */

                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                finish();
                return true;

            case R.id.Action_Delete:
                Snackbar.make(toolbar,"The ticket will be cancelled, do you confirm?",Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new TicketDao().removeTicket(vt,ticket.getId());

                        //startActivity(new Intent(DetailActivity.this,MainActivity.class)); ---3
                        startActivity(new Intent(DetailActivity.this,MoviesActivity.class));

                        finish();
                    }
                }).show();
                return true;

            default:
                return false;
        }
    }
}