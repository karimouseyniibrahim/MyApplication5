package com.example.etudiantrsd.myapplication.bdd.classes;

/**
 * Created by Ib on 05/06/2017.
 */

public class Activite {
    private String codesport,CodeJoueur;
    private int id;
    public Activite() {
    }

    public String getCodeJoueur() {
        return CodeJoueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activite( int id,String codesport, String codeJoueur) {
        this.codesport = codesport;
        CodeJoueur = codeJoueur;
        this.id = id;
    }

    public void setCodeJoueur(String codeJoueur) {
        CodeJoueur = codeJoueur;
    }

    public String getCodesport() {
        return codesport;
    }

    public void setCodesport(String codesport) {
        this.codesport = codesport;
    }
}
