package com.example.projetcoktail;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerJoueur extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Form.db";

    public DBHandlerJoueur(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =  "CREATE TABLE " + DBjoueur.Form.TABLE_NAME + "(" +
                DBjoueur.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBjoueur.Form.COLUMN_PRENOM + " TEXT," +
                DBjoueur.Form.COLUMN_NOM + " TEXT," +
                DBjoueur.Form.COLUMN_EMAIL + " TEXT," +
                DBjoueur.Form.COLUMN_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*if (i1 > i) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBjoueur.Form.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }*/
    }


}
