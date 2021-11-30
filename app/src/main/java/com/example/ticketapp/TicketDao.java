package com.example.ticketapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class TicketDao {

    public ArrayList<Ticket> getAllTickets(Database vt){
        ArrayList<Ticket> ticketList=new ArrayList<>();
        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor c=db.rawQuery("SELECT * FROM ticketMovie",null);

        while (c.moveToNext()){
            Ticket ticket=new Ticket(
                    c.getInt(c.getColumnIndex("id"))
                    ,c.getString(c.getColumnIndex("movieName"))
                    ,c.getString(c.getColumnIndex("clock"))
            ,c.getInt(c.getColumnIndex("ticketNo")));
            ticketList.add(ticket);
        }
        db.close();
        return ticketList;
    }

    public void removeTicket(Database vt,int id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("ticketMovie","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void buyTicket(Database vt,String movieName,String clock){
        SQLiteDatabase db=vt.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("movieName",movieName);
        values.put("clock",clock);
        Random rnd=new Random();
        int no= rnd.nextInt(999);
        values.put("ticketNo",no);

        db.insertOrThrow("ticketMovie",null,values);
        db.close();
    }

    /*public void updateTicket(Database vt,int ticket_id,String event,String customer){
        SQLiteDatabase db=vt.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("event",event);
        values.put("customer",customer);

        db.update("ticket",values,"ticket_id=?",new String[]{String.valueOf(ticket_id)});
        db.close();
    }*/

}
