package com.example.bloggingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelp extends SQLiteOpenHelper {

    public dbhelp( Context context) {
        super(context,"userdata.db1", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table blogginges(titre TEXT primary key,contenu TEXT,auteur TEXT,date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {

        DB.execSQL("drop Table if exists blogginges");
    }

    public Boolean ajouterarticle(String titre,String contenu,String auteur,String date){

        SQLiteDatabase DB=this.getWritableDatabase(); //connexion a la base de donnees

        ContentValues contentValues=new ContentValues(); //on recupere la saisi dans un contentvalues
        contentValues.put("titre",titre);
        contentValues.put("auteur",auteur);
        contentValues.put("contenu",contenu);
        contentValues.put("date",date);

        long result=DB.insert("blogginges",null,contentValues); //on insere dans la table blogging

        if(result==-1){    //si l'insertion echoue on retourne false
            return false;
        }
        else     //sinon on retourne true
        {
            return true;
        }
    }

    public Cursor voirdata(){

        SQLiteDatabase DB=this.getWritableDatabase(); //connexion a la base de donnees

        Cursor cursor=DB.rawQuery("select * from blogginges",null);

        return cursor;
    }


}
