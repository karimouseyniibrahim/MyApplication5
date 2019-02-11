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

import com.example.etudiantrsd.myapplication.ActiviterActivity;
import com.example.etudiantrsd.myapplication.JouerurActivity;
import com.example.etudiantrsd.myapplication.MainActivity;
import com.example.etudiantrsd.myapplication.R;
import com.example.etudiantrsd.myapplication.bdd.classes.Activite;
import com.example.etudiantrsd.myapplication.bdd.classes.Joueur;
import com.example.etudiantrsd.myapplication.bdd.classes.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ib on 08/06/2017.
 */

public class AdapterSP extends RecyclerView.Adapter<BiblioViewHolder> {
    public static Context context;
    private List<Activite> activiteList;

   // public static JoueurBD joueurbd;
    public static View view;


    public AdapterSP(Context context, List<Activite> activites) {
        this.context = context;
        this.activiteList = activites;

    }
    @Override
    public BiblioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_j, parent, false);
        return new BiblioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BiblioViewHolder holder, final int position) {
        final Activite sp= activiteList.get(position);
        if (sp!=null) {
            Sport sport = ActiviterActivity.bdd.Infos(sp.getCodesport(), sp.getCodeJoueur());
            holder.name.setText(sport.getCode() + " " + sport.getNom());
            holder.numerotation.setText(String.valueOf(position + 1));
            holder.code.setText(sport.getDescription());

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
    }

    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    private void InfosS(final int i){
        View subView =  LayoutInflater.from(view.getContext()).inflate(R.layout.activity, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("Sport");
        builder.setView(subView);
        builder.create();
        Activite a=activiteList.get(i);

        final Spinner sport=(Spinner)subView.findViewById(R.id.sport);
        final Spinner joueur=(Spinner)subView.findViewById(R.id.joeur);

        List<String> categories = new ArrayList<>();
        List<Sport> ls = ActiviterActivity.bdd.listSport();
        int s=0;
        for (int k=0;k<ls.size();k++){
            categories.add(ls.get(k).getCode()+"/"+ls.get(k).getNom());
            if (a.getCodesport().equals(ls.get(k).getCode()))
                s=k;
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sport.setAdapter(dataAdapter);
        sport.setSelection(s);

        List<String> categories2 = new ArrayList<>();
        List<Joueur> lj = ActiviterActivity.bdd.listJoueur();
         s=0;
        for (int k=0;k<lj.size();k++){
            categories2.add(lj.get(k).getMatricule()+"/"+lj.get(k).getNom()+" "+lj.get(k).getPrenom());
            if (a.getCodeJoueur().equals(lj.get(k).getMatricule()))
                s=k;
        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categories2);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        joueur.setAdapter(dataAdapter1);
        joueur.setSelection(s);

        final EditText code=(EditText)subView.findViewById(R.id.matricule);
        code.setText(""+a.getId());
        code.setEnabled(false);
        FloatingActionButton edit=(FloatingActionButton)subView.findViewById(R.id.edit);
        FloatingActionButton delte=(FloatingActionButton)subView.findViewById(R.id.delet);
        FloatingActionButton cancel=(FloatingActionButton)subView.findViewById(R.id.cancel);
        final AlertDialog d = builder.show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jm=joueur.getSelectedItem().toString().split("/")[0];
                String sm=sport.getSelectedItem().toString().split("/")[0];
                Activite sp=new Activite(Integer.valueOf(code.getText().toString()),sm,jm);
                int maj= ActiviterActivity.bdd.UpdatActiviter(sp);

                if (maj==1) {
                    activiteList.set(i,sp);
                    AdapterSP.this.notifyDataSetChanged();
                }
                d.dismiss();
            }
        });
        delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
                Activite ar = activiteList.remove(i);
                ActiviterActivity.bdd.DeleteActiviter(""+ar.getId());
                AdapterSP.this.notifyDataSetChanged();
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
        return activiteList.size();
    }
}
