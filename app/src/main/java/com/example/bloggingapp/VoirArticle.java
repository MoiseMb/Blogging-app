package com.example.bloggingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VoirArticle extends AppCompatActivity {

    Button retour;

    TextView titre,contenu,auteur,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_article);

        retour=findViewById(R.id.retour_pr);
        titre=findViewById(R.id.afficher_titre);
        contenu=findViewById(R.id.afficher_contenu);
        date =findViewById(R.id.afficher_date);
        auteur=findViewById(R.id.afficher_auteur);

        String titre_value=getIntent().getStringExtra("titre");
        String contenu_value=getIntent().getStringExtra("contenu");
        String auteur_value=getIntent().getStringExtra("auteur");
        String date_value=getIntent().getStringExtra("date");

        titre.setText(titre_value);
        contenu.setText(contenu_value);
        auteur.setText(auteur_value);
        date.setText(date_value);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rt=new Intent(VoirArticle.this,Acceuil.class);
                startActivity(rt);
            }
        });


    }
}