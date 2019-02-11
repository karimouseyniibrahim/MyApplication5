package com.example.etudiantrsd.myapplication;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.etudiantrsd.myapplication.bdd.BDD;
import com.example.etudiantrsd.myapplication.bdd.classes.Activite;
import com.example.etudiantrsd.myapplication.bdd.classes.Joueur;
import com.example.etudiantrsd.myapplication.bdd.classes.Sport;
import com.example.etudiantrsd.myapplication.modele.AdapterA;
import com.example.etudiantrsd.myapplication.modele.AdapterSP;

import java.util.ArrayList;
import java.util.List;

public class ActiviterActivity extends AppCompatActivity {

    RecyclerView Activityiews;
    AdapterSP mAdapterSP;
    AdapterA mAdapterS;
    public static BDD bdd;

    int mode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiter);
        bdd=new BDD(this);

        this.
        Activityiews=(RecyclerView)findViewById(R.id.activity_list) ;
        Activityiews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        init();
        changeAdaptter();

    }

    public void changeAdaptter(){
        if (mode==1){
              mAdapterS = new AdapterA(this, bdd.listSport());
            Activityiews.setAdapter(mAdapterS);
        }else{
              mAdapterSP = new AdapterSP(this, bdd.listActiver());
            Activityiews.setAdapter(mAdapterSP);
        }
    }
    public void addsport(View view){
        AddS();
    }

    public void addsportpratiquer(View view){
        AddSP();
    }
    private void AddS(){
        View subView =  getLayoutInflater().inflate(R.layout.sport, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (mode!=1){
            mode=1;
            changeAdaptter();
        }
        builder.setTitle("Sport");
        builder.setView(subView);
        builder.create();

        final EditText code=(EditText)subView.findViewById(R.id.code);

        final EditText libeller=(EditText)subView.findViewById(R.id.libeller);

        final EditText description=(EditText)subView.findViewById(R.id.description);


        FloatingActionButton edit=(FloatingActionButton)subView.findViewById(R.id.edit);

        FloatingActionButton delte=(FloatingActionButton)subView.findViewById(R.id.delet);
        delte.setVisibility(View.INVISIBLE);
        FloatingActionButton cancel=(FloatingActionButton)subView.findViewById(R.id.cancel);
        final AlertDialog d = builder.show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sport sp=new Sport(code.getText().toString(),libeller.getText().toString(),description.getText().toString());
                 ActiviterActivity.bdd.addsport(sp);
                mAdapterS.getListSport().add(sp);
                Activityiews.getAdapter().notifyDataSetChanged();

                d.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActiviterActivity.this,getString(R.string.addcancel), Toast.LENGTH_LONG).show();
                d.dismiss();
            }
        });
    }

    private void AddSP(){
        if (mode!=0){
            mode=0;
            changeAdaptter();
        }
        View subView =  getLayoutInflater().inflate(R.layout.activity, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sport");
        builder.setView(subView);
        builder.create();


        final Spinner sport=(Spinner)subView.findViewById(R.id.sport);
        final Spinner joueur=(Spinner)subView.findViewById(R.id.joeur);

        List<String> categories = new ArrayList<>();
        List<Sport> ls = ActiviterActivity.bdd.listSport();

        for (int k=0;k<ls.size();k++){
            categories.add(ls.get(k).getCode()+"/"+ls.get(k).getNom());

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sport.setAdapter(dataAdapter);
        sport.setSelection(0);

        List<String> categories2 = new ArrayList<>();
        List<Joueur> lj = ActiviterActivity.bdd.listJoueur();

        for (int k=0;k<lj.size();k++){
            categories2.add(lj.get(k).getMatricule()+"/"+lj.get(k).getNom()+" "+lj.get(k).getPrenom());

        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        joueur.setAdapter(dataAdapter1);
        joueur.setSelection(0);

        final EditText code=(EditText)subView.findViewById(R.id.matricule);

        code.setVisibility(View.INVISIBLE);
        FloatingActionButton edit=(FloatingActionButton)subView.findViewById(R.id.edit);
        FloatingActionButton delte=(FloatingActionButton)subView.findViewById(R.id.delet);
        delte.setVisibility(View.INVISIBLE);
        FloatingActionButton cancel=(FloatingActionButton)subView.findViewById(R.id.cancel);
        final AlertDialog d = builder.show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jm=null,sm=null;
                if (joueur.getSelectedItem().toString()!=null)
                     jm=joueur.getSelectedItem().toString().split("/")[0];
                if (sport.getSelectedItem().toString()!=null)
                    sm=sport.getSelectedItem().toString().split("/")[0];
                if (sm!=null&& jm!=null) {
                    Activite sp = new Activite(0, sm, jm);
                    ActiviterActivity.bdd.addActiviter(sp);

                    mAdapterSP.getActiviteList().add(sp);
                    Activityiews.getAdapter().notifyDataSetChanged();
                }
                d.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActiviterActivity.this,getString(R.string.addcancel), Toast.LENGTH_LONG).show();
                d.dismiss();
            }
        });
    }

    public void init(){

        if (bdd.listSport().size()==0){
            bdd.addsport(new Sport("S001","FoorBall","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S002","Tennis","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S003","Handball","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S004","BasketBall","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S005","VolletBall","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S006","FoorAmericain","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S007","Golf","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S008","Rubbi","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S009","Cours","Equipe composer de 11 joueurs"));
            bdd.addsport(new Sport("S0010","Marche","Equipe composer de 11 joueurs"));

        }
    }
}
