package com.example.etudiantrsd.myapplication.modele;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.etudiantrsd.myapplication.JouerurActivity;
import com.example.etudiantrsd.myapplication.MainActivity;
import com.example.etudiantrsd.myapplication.R;
import com.example.etudiantrsd.myapplication.bdd.classes.Joueur;
import com.example.etudiantrsd.myapplication.bdd.classes.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ib on 08/06/2017.
 */

public class AdapterJ extends RecyclerView.Adapter<BiblioViewHolder> {
    public static Context context;
    private List<Joueur> joueurs;

   // public static JoueurBD joueurbd;
    public static View view;


    public AdapterJ(Context context, List<Joueur> joueurs) {
        this.context = context;
        this.joueurs = joueurs;
     //   joueurbd = new JoueurBD(context);
    }
    @Override
    public BiblioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_j, parent, false);
        return new BiblioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BiblioViewHolder holder, final int position) {
        final Joueur j = joueurs.get(position);

        holder.name.setText( j.getNom()+" "+j.getPrenom());
        holder.numerotation.setText(String.valueOf(position+1));
        holder.code.setText(j.getMatricule());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InfosJ(position);
                }
            });

            holder.action_document.setVisibility(View.VISIBLE);
        holder.action_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    private void InfosJ(final int i){
        View subView =  LayoutInflater.from(view.getContext()).inflate(R.layout.editjoueur, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("Ajout Joueur");
        builder.setView(subView);
        builder.create();
        Joueur j=joueurs.get(i);
        final EditText matricule=(EditText)subView.findViewById(R.id.matricule);
        matricule.setText(j.getMatricule());
        final EditText nom=(EditText)subView.findViewById(R.id.nom);
        nom.setText(j.getNom());
        final EditText prenom=(EditText)subView.findViewById(R.id.prenom);
        prenom.setText(j.getPrenom());
        final EditText datenass=(EditText)subView.findViewById(R.id.datenass);
        datenass.setText(j.getDateNaissance());
        final EditText lieuNaiss=(EditText)subView.findViewById(R.id.lieuNaiss);
        lieuNaiss.setText(j.getLieuNaissance());
        final EditText email=(EditText)subView.findViewById(R.id.email);
        email.setText(j.getEmail());
        final EditText tel=(EditText)subView.findViewById(R.id.tel);
        tel.setText(j.getTel());

        //sport.setText(j.getTypesport());
        FloatingActionButton edit=(FloatingActionButton)subView.findViewById(R.id.edit);
        FloatingActionButton delte=(FloatingActionButton)subView.findViewById(R.id.delet);
        FloatingActionButton cancel=(FloatingActionButton)subView.findViewById(R.id.cancel);
        final AlertDialog d = builder.show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Joueur joueur=new Joueur(matricule.getText().toString(),nom.getText().toString(),prenom.getText().toString(),datenass.getText().toString(),lieuNaiss.getText().toString(),email.getText().toString(),tel.getText().toString());
                int maj=JouerurActivity.joueurbd.UpdateJoueur(joueur);

                if (maj==1) {
                    joueurs.set(i,joueur);
                    AdapterJ.this.notifyDataSetChanged();
                }
                d.dismiss();
            }
        });
        delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
                Joueur jr = joueurs.remove(i);
                JouerurActivity.joueurbd.deleteJoueur(jr.getMatricule());
                AdapterJ.this.notifyDataSetChanged();;
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
        return joueurs.size();
    }
}
