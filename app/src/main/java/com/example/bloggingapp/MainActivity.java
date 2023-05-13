package com.example.bloggingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText titre,contenu;
    Button ajouter,annuler;

    dbhelp DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titre=findViewById(R.id.titre);  //champs pour mettre le nom de l'article

        contenu=findViewById(R.id.contenu); //champ pour le contenu de l'article

        ajouter=findViewById(R.id.ajouter); //le bouton ajouter pour valider l'ajout dans la base de donnees

        annuler=findViewById(R.id.annuler); //le bouton pour annuler l'ajout et retourne dans l'acceuil


        //Retourne a la page principal au click


       annuler.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent rt=new Intent(MainActivity.this,Acceuil.class);
               startActivity(rt);
           }
       });

        DB= new dbhelp(this);


        //ajout l'article dans la db apres click sur le bouton ajouter

       ajouter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               // on verifie d'abord si les champs ne sont pas vide

               if (titre.getText().toString().trim().isEmpty() ||contenu.getText().toString().trim().isEmpty() ){

                   AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
                   build.setCancelable(true);
                   build.setTitle("Erreur !");
                   build.setMessage("Veuillez remplir les deux champs avant de valider");
                   build.show();
                   return;
               }

               //On ajout l'article sinon

               String tr=   titre.getText().toString();
               String con=   contenu.getText().toString();


               Boolean verif=DB.ajouterarticle(tr,con);

               //Insetion reussie

               if(verif==true){

                   Toast.makeText(MainActivity.this, "Article Ajouter avec succes", Toast.LENGTH_SHORT).show();
                   Intent rt=new Intent(MainActivity.this,Acceuil.class);
                   startActivity(rt);

               }else{


                   AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
                   build.setCancelable(true);
                   build.setTitle("Erreur !");
                   build.setMessage("Le nom de l'article que vous avez saisi existe deja");
                   build.show();

               }


           }
       });

    }
}