package com.example.bloggingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> titre,contenu,auteur,date;
    dbhelp DB;
    MyAdapter adapter;

    Button ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);




        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.header_layout, recyclerView, false);

        DB=new dbhelp(this);
        titre=new ArrayList<>();
        contenu=new ArrayList<>();
        auteur=new ArrayList<>();
        date=new ArrayList<>();
        recyclerView=findViewById(R.id.listarticle);
        adapter=new MyAdapter(this, titre, contenu,auteur,date, headerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        voir();

        ajout=findViewById(R.id.ajouter_article);

        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent rt=new Intent(Acceuil.this,MainActivity.class);
                startActivity(rt);
            }
        });
    }
    public void voir(){
        Cursor cursor=DB.voirdata();
        if (cursor.getCount()==0){
            Toast.makeText(this, "Pas d'article", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while(cursor.moveToNext()){

                 titre.add(cursor.getString(0));
                contenu.add(cursor.getString(1));
                auteur.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }

        }
    }
}