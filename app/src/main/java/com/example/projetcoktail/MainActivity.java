package com.example.projetcoktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.EditText;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    private String prenom;
    private String nom;
    private String mdp;
    DBManager manage;
    private String email;

    private Button lelogin;

    private EditText leprenom;
    private EditText lenom;
    private EditText lemail;
    private EditText lemdp;


    private Intent toRead, toInsert ;

    private Toolbar baraction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.latoolbar));
        leprenom = (EditText) findViewById(R.id.pre);
        lenom = (EditText) findViewById(R.id.nom);
        lemail = (EditText) findViewById(R.id.mail);
        lemdp = (EditText) findViewById(R.id.mdp);
        lelogin = (Button) findViewById(R.id.login);
        manage = new DBManager(this);
        manage.open();
        toInsert = new Intent(this, InsertActivity.class);
        Gestion gestion = new Gestion();
        lelogin.setOnClickListener(gestion);
    }



    private class Gestion implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String pr = leprenom.getText().toString();
            String no = lenom.getText().toString();
            String ma = lemail.getText().toString();
            String md = lemdp.getText().toString();
            if(manage.VerifExists(pr, no, ma)){
                toInsert.putExtra("leprenom",pr);
                toInsert.putExtra("lenom",no);
                toInsert.putExtra("lemail",ma);
                toInsert.putExtra("lemdp",md);
                startActivity(toInsert);
            }
            else{
                manage.InsertForm(pr, no, ma,md);
                manage.EmailVerifandMot(ma, md);
                toInsert.putExtra("leprenom",pr);
                toInsert.putExtra("lenom",no);
                toInsert.putExtra("lemail",ma);
                toInsert.putExtra("lemdp",md);
                startActivity(toInsert);
            }

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                Intent iretour = new Intent(this, this.getClass());
                startActivity(iretour);
                return true;
            case R.id.donjon:
                Intent idonjon = new Intent(this, DonjonActivity.class);
                startActivity(idonjon);
                return true;

            case R.id.profil :
                Intent iprofil = new Intent(this, ProfilActivity.class);
                startActivity(iprofil);
                return true;

            case R.id.parametre:
                Intent iparametre = new Intent(this, ParametreActivity.class);
                startActivity(iparametre);
            return true;

        }
        return false;
    }
}