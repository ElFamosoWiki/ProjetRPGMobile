package com.example.projetcoktail;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBHandlerJoueur dbjoueur;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {

        context = c;
    }

    public DBManager open() throws SQLException {
        dbjoueur = new DBHandlerJoueur(context);
        database = dbjoueur.getWritableDatabase();
        return this;
    }
    public void close() {
        dbjoueur.close();
    }

    public void InsertForm(String prenom, String nom, String email, String mot){

        ContentValues row = new ContentValues();
        row.put(DBjoueur.Form.COLUMN_PRENOM, prenom);
        row.put(DBjoueur.Form.COLUMN_NOM, nom);
        row.put(DBjoueur.Form.COLUMN_EMAIL, email);
        row.put(DBjoueur.Form.COLUMN_PASSWORD, mot);
        long newRowId = database.insert(DBjoueur.Form.TABLE_NAME, null, row);
    }

    public void updateForm(String email, String newPrenom, String newNom){

        ContentValues values = new ContentValues();
        values.put(DBjoueur.Form.COLUMN_PRENOM,newPrenom);
        values.put(DBjoueur.Form.COLUMN_NOM,newNom);
//row to update based on name
        String selection = DBjoueur.Form.COLUMN_EMAIL +" LIKE ? ";
//matches ? from selection
        String[] selectionArgs = {email};
// return number of rows updated
        int count = database.update(DBjoueur.Form.TABLE_NAME,values,selection,selectionArgs);
    }

    public void deleteForm (String prenom ){
//rows to delete based on name
        String selection = DBjoueur.Form.COLUMN_PRENOM +" LIKE ? ";
//matches ? from selection
        String[] selectionArgs = {prenom};
// return number of rows deleted
        int count = database.delete(DBjoueur.Form.TABLE_NAME,selection,selectionArgs);
    }

    public String getPrenom(String lenom){


        database = dbjoueur.getWritableDatabase();
        String result_name="";
        String[] projection = {
                DBjoueur.Form.COLUMN_PRENOM
        };
        String selection = DBjoueur.Form.COLUMN_NOM + " = ?";
        String[] selectionArgs = {lenom};
        Cursor cursor=database.rawQuery("SELECT "+DBjoueur.Form.COLUMN_PRENOM+" FROM '"+DBjoueur.Form.TABLE_NAME+"' WHERE "+DBjoueur.Form.COLUMN_NOM+"=?",selectionArgs);
        while(cursor.moveToNext()){

           String lepre = cursor.getString(cursor.getColumnIndexOrThrow(DBjoueur.Form.COLUMN_PRENOM));
            result_name=lepre;
        }
        cursor.close();
        return result_name;
    }
    public boolean VerifExists(String p, String n, String e){

        boolean existe =false;
        database = dbjoueur.getWritableDatabase();
        String[] selectionArgs = {p, n, e};
        Cursor cursor=database.rawQuery("SELECT * FROM '"+DBjoueur.Form.TABLE_NAME+"' WHERE prenom=? AND nom=? AND email=?",selectionArgs);
        if(cursor.moveToFirst()){
           existe=true;
        }
        return existe;
    }
    public boolean EmailVerifandMot(String e, String m){

        boolean existe =false;
        database = dbjoueur.getWritableDatabase();
        String[] selectionArgs = {e, m};
        Cursor cursor=database.rawQuery("SELECT * FROM '"+DBjoueur.Form.TABLE_NAME+"' WHERE "+DBjoueur.Form.COLUMN_EMAIL+"=? AND "+DBjoueur.Form.COLUMN_PASSWORD+"=?",selectionArgs);
       while(cursor.moveToNext()){
           System.out.println(cursor.getString(cursor.getColumnIndexOrThrow(DBjoueur.Form.COLUMN_EMAIL)));
           System.out.println(cursor.getString(cursor.getColumnIndexOrThrow(DBjoueur.Form.COLUMN_PASSWORD)));
       }
        if(cursor.moveToFirst()){
           System.out.println("valeur du mot de passe :"+cursor.getString(cursor.getColumnIndexOrThrow(DBjoueur.Form.COLUMN_PASSWORD)));
            existe=true;
        }
        System.out.println("bon cç bug ça trouve pas");
        return existe;
    }
}
