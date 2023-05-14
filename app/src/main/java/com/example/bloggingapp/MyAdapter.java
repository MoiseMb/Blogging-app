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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private ArrayList<String> titre_id;
    private ArrayList<String> contenu_id;
    private View headerView;

    public MyAdapter(Context context, ArrayList<String> titre_id, ArrayList<String> contenu_id, View headerView) {
        this.context = context;
        this.titre_id = titre_id;
        this.contenu_id = contenu_id;
        this.headerView = headerView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new MyViewHolder(headerView);
        }

        View v = LayoutInflater.from(context).inflate(R.layout.article, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position != 0) {
            holder.titre_id.setText(titre_id.get(position - 1));
            holder.contenu_id.setText(contenu_id.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return titre_id.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titre_id,contenu_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titre_id=itemView.findViewById(R.id.nom_titre);
            contenu_id=itemView.findViewById(R.id.nom_contenu);

            //On recupere le le titre de l'article et le contenu au click sur l'article

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
