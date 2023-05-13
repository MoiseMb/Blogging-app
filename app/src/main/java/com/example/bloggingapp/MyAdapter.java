package com.example.bloggingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList titre_id,contenu_id;

    public MyAdapter(Context context, ArrayList titre_id, ArrayList contenu_id) {
        this.context = context;
        this.titre_id = titre_id;
        this.contenu_id = contenu_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.article,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titre_id.setText(String.valueOf(titre_id.get(position)));
        holder.contenu_id.setText(String.valueOf(contenu_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return titre_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titre_id,contenu_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titre_id=itemView.findViewById(R.id.nom_titre);
            contenu_id=itemView.findViewById(R.id.nom_contenu);

            //On recupere le le titre de l'article et le contenu au click sur larticle

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String myText = titre_id.getText().toString();
                    String ctn=contenu_id.getText().toString();

                    Intent rt=new Intent(context,VoirArticle.class);
                    rt.putExtra("titre",myText);
                    rt.putExtra("contenu",ctn);

                    context.startActivity(rt);

                }
            });
        }
    }
}
