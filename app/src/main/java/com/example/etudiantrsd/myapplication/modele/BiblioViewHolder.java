package com.example.etudiantrsd.myapplication.modele;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etudiantrsd.myapplication.R;


/**
 * Created by Ib on 08/06/2017.
 */

public class BiblioViewHolder extends RecyclerView.ViewHolder {
    public TextView code,name,numerotation;
    public View view;
    public ImageView action_document;


    public BiblioViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        name=(TextView)itemView.findViewById(R.id.name);
        action_document=(ImageView)itemView.findViewById(R.id.action_document);
        numerotation=(TextView)itemView.findViewById(R.id.numerotation) ;
        code=(TextView)itemView.findViewById(R.id.code);
    }
}
