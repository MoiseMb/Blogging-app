package com.example.bloggingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VoirArticle extends AppCompatActivity {

    Button retour;

    TextView titre,contenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_article);

        retour=findViewById(R.id.retour_pr);
        titre=findViewById(R.id.afficher_titre);
        contenu=findViewById(R.id.afficher_contenu);

        String titre_value=getIntent().getStringExtra("titre");
        String contenu_value=getIntent().getStringExtra("contenu");

        titre.setText(titre_value);
        contenu.setText(contenu_value);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rt=new Intent(VoirArticle.this,Acceuil.class);
                startActivity(rt);
            }
        });


    }
}