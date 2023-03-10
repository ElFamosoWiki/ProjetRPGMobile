package com.example.projetcoktail;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class InsertActivity extends AppCompatActivity{

    DBManager manage;
    EditText result;

    EditText mdp;

    Button connect;
    boolean lepre =false;

    Intent p ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        manage = new DBManager(this);
        manage.open();
        result = (EditText) findViewById(R.id.mail);
        mdp = (EditText) findViewById(R.id.mdp);
        connect = (Button) findViewById(R.id.connexion);
        AutreGestion gestion = new AutreGestion();
        p =new Intent(this, MainActivity.class);
        connect.setOnClickListener(gestion);



    }

    private class AutreGestion implements View.OnClickListener{
        public void onClick(View view) {
            String resultmdp = mdp.getText().toString();
            String lemail = result.getText().toString();
            System.out.println("je suis dans activity Insert : "+resultmdp);
            System.out.println("je suis dans activity Insert : "+lemail);
            lepre = manage.EmailVerifandMot(lemail, resultmdp);
            if((lepre)) {
                if (view.getId() == R.id.connexion) {
                    startActivity(p);
                }
            }
        }
    }

}
