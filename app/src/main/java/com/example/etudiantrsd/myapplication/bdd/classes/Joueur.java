package com.example.etudiantrsd.myapplication.bdd.classes;

/**
 * Created by Ib on 07/06/2017.
 */

public class Joueur {

   String Matricule,nom,prenom,dateNaissance,LieuNaissance,email,tel;

    public Joueur(String matricule, String nom, String prenom, String dateNaissance, String lieuNaissance, String email, String tel) {
        Matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        LieuNaissance = lieuNaissance;
        this.email = email;
        this.tel = tel;

    }

    public Joueur() {
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return LieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        LieuNaissance = lieuNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        Matricule = matricule;
    }
}
