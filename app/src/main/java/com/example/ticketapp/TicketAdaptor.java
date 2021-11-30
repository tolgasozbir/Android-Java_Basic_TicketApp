package com.example.ticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TicketAdaptor extends RecyclerView.Adapter<TicketAdaptor.CardDesign> {
    private Context mContext;
    private List<Ticket> ticketsList;

    public TicketAdaptor(Context mContext, ArrayList<Ticket> ticketsList) {
        this.mContext = mContext;
        this.ticketsList = ticketsList;
    }

    @NonNull
    @Override
    public CardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);

        return new CardDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdaptor.CardDesign holder, int position) {
        final Ticket ticket=ticketsList.get(position);
        //Tickets ticket=ticketsList.get(position);

        holder.txtMovieName.setText("Movie Name\n\n"+ticket.getMovieName());
        holder.txtClock.setText("Session\n\n"+ticket.getClock());
        holder.txtTicketNo.setText(String.valueOf("Ticket No\n\n"+ticket.getTicketNo()));
        holder.iwMovie.setImageResource(mContext.getResources().getIdentifier(ticket.getMovieName().toLowerCase(Locale.ENGLISH),"drawable",mContext.getPackageName()));

        holder.ticketCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext,DetailActivity.class);

                intent.putExtra("object",ticket);

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return ticketsList.size();
    }






    public class CardDesign extends RecyclerView.ViewHolder{
        private TextView txtMovieName;
        private TextView txtClock;
        private TextView txtTicketNo;
        private ImageView iwMovie;
        private CardView ticketCard;


        public CardDesign(View itemView) {
            super(itemView);
            iwMovie=itemView.findViewById(R.id.iwMovie);
            txtClock = itemView.findViewById(R.id.txtClock);
            txtMovieName = itemView.findViewById(R.id.txtMovieName);
            txtTicketNo = itemView.findViewById(R.id.txtTicketNo);
            ticketCard = itemView.findViewById(R.id.ticket_card);
        }
    }

}
