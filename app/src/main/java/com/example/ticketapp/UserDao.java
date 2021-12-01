package com.example.ticketapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDao {
    public ArrayList<User> getUsers(Database vt){

        ArrayList<User> users=new ArrayList<>();
        SQLiteDatabase db=vt.getWritableDatabase();

        Cursor c=db.rawQuery("SELECT * FROM users",null);

        while (c.moveToNext()){
            User user=new User(
                    c.getInt(c.getColumnIndex("id"))
                    ,c.getString(c.getColumnIndex("userName"))
                    ,c.getString(c.getColumnIndex("password"))
            );
            users.add(user);
        }

        db.close();
        return users;
    }

    public void deleteAccount(Database vt,int id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("users","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void createAccount (Database vt,String userName,String password){
        SQLiteDatabase db=vt.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("userName",userName);
        values.put("password",password);

        db.insertOrThrow("users",null,values);
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
