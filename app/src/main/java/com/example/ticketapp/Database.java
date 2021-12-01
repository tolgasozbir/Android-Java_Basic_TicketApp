package com.example.ticketapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database( Context context) {
        super(context,"ticketMovie.sqlite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"ticketMovie\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"movieName\"\tTEXT,\n" +
                "\t\"clock\"\tTEXT,\n" +
                "\t\"ticketNo\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE \"users\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"userName\"\tTEXT,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ticketMovie");
        onCreate(sqLiteDatabase);
    }
}
