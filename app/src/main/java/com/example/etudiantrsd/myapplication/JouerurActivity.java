package com.example.etudiantrsd.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.etudiantrsd.myapplication.bdd.BDD;
import com.example.etudiantrsd.myapplication.bdd.classes.Joueur;
import com.example.etudiantrsd.myapplication.bdd.classes.Sport;
import com.example.etudiantrsd.myapplication.modele.AdapterJ;

import java.util.ArrayList;
import java.util.List;

public class JouerurActivity extends AppCompatActivity {


    RecyclerView Joueuriews;
    public static BDD joueurbd;
    AdapterJ mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouerur);
        Joueuriews=(RecyclerView)findViewById(R.id.joueur_list) ;
        Joueuriews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        joueurbd = new BDD(this);
        List<Joueur> list = joueurbd.listJoueur();

        mAdapter = new AdapterJ(this, list);
        Joueuriews.setAdapter(mAdapter);

    }

    public void addJoueur(View view){
       AddJ();
        System.err.println(joueurbd.listJoueur().size());

    }


    private void AddJ(){
        View subView = getLayoutInflater().inflate(R.layout.joueur, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Ajout Joueur");
        builder.setView(subView);
        builder.create();
        final EditText matricule=(EditText)subView.findViewById(R.id.matricule);
        final EditText nom=(EditText)subView.findViewById(R.id.nom);
        final EditText prenom=(EditText)subView.findViewById(R.id.prenom);
        final EditText datenass=(EditText)subView.findViewById(R.id.datenass);
        final EditText lieuNaiss=(EditText)subView.findViewById(R.id.lieuNaiss);
        final EditText email=(EditText)subView.findViewById(R.id.email);
        final EditText tel=(EditText)subView.findViewById(R.id.tel);



        builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Joueur joueur=new Joueur(matricule.getText().toString(),nom.getText().toString(),prenom.getText().toString(),datenass.getText().toString(),lieuNaiss.getText().toString(),email.getText().toString(),tel.getText().toString());

                joueurbd.addJoueur(joueur);

               mAdapter.getJoueurs().add(mAdapter.getItemCount(),joueur);
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(JouerurActivity.this,getString(R.string.addcancel), Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
