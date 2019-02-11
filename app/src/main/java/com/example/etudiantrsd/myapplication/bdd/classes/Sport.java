package com.example.etudiantrsd.myapplication.bdd.classes;

/**
 * Created by Ib on 06/06/2017.
 */

public class Sport {

    String code,libeller,description;

    public Sport(String code, String nom, String description) {
        this.code = code;
        this.libeller = nom;
        this.description = description;
    }

    public Sport() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return libeller;
    }

    public void setNom(String nom) {
        this.libeller = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
