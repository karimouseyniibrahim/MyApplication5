package com.example.etudiantrsd.myapplication.modele;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import com.example.etudiantrsd.myapplication.ActiviterActivity;
import com.example.etudiantrsd.myapplication.JouerurActivity;

import com.example.etudiantrsd.myapplication.R;
import com.example.etudiantrsd.myapplication.bdd.classes.Activite;

import com.example.etudiantrsd.myapplication.bdd.classes.Sport;


import java.util.List;

/**
 * Created by Ib on 08/06/2017.
 */

public class AdapterA extends RecyclerView.Adapter<BiblioViewHolder> {
    public static Context context;
    private List<Sport> listSport;
    private List<Activite> activiteList;

   // public static JoueurBD joueurbd;
    public static View view;


    public AdapterA(Context context, List<Sport> sports) {
        this.context = context;
        this.listSport = sports;

    }
    @Override
    public BiblioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_j, parent, false);
        return new BiblioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BiblioViewHolder holder, final int position) {
        final Sport sp= listSport.get(position);

        holder.name.setText( sp.getNom());
        holder.numerotation.setText(String.valueOf(position+1));
        holder.code.setText(sp.getCode());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InfosS(position);
                }
            });

            holder.action_document.setVisibility(View.VISIBLE);
        holder.action_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public List<Sport> getListSport() {
        return listSport;
    }

    public void setListSport(List<Sport> listSport) {
        this.listSport = listSport;
    }

    private void InfosS(final int i){
        View subView =  LayoutInflater.from(view.getContext()).inflate(R.layout.sport, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("Sport");
        builder.setView(subView);
        builder.create();
        Sport s=listSport.get(i);
        final EditText code=(EditText)subView.findViewById(R.id.code);
        code.setText(s.getCode());
        final EditText libeller=(EditText)subView.findViewById(R.id.libeller);
        libeller.setText(s.getNom());
        final EditText description=(EditText)subView.findViewById(R.id.description);
        description.setText(s.getDescription());

        FloatingActionButton edit=(FloatingActionButton)subView.findViewById(R.id.edit);
        FloatingActionButton delte=(FloatingActionButton)subView.findViewById(R.id.delet);
        FloatingActionButton cancel=(FloatingActionButton)subView.findViewById(R.id.cancel);
        final AlertDialog d = builder.show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sport sp=new Sport(code.getText().toString(),libeller.getText().toString(),description.getText().toString());
                int maj= ActiviterActivity.bdd.UpdateSport(sp);

                if (maj==1) {
                    listSport.set(i,sp);
                    AdapterA.this.notifyDataSetChanged();
                }
                d.dismiss();
            }
        });
        delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
                Sport sr = listSport.remove(i);
                ActiviterActivity.bdd.DeleteSport(sr.getCode());
                AdapterA.this.notifyDataSetChanged();;
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
    }
    @Override
    public int getItemCount() {
        return listSport.size();
    }
}
