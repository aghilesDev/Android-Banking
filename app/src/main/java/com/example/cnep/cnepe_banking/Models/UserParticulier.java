package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-03-26.
 */

public class UserParticulier extends  User {

    private String nom;
    private String prenom;
    private String date_Naissance;
    private String lieu_Naissance;
    private String sexe;

    public UserParticulier(String userId, int type, String email, String telephone, String adresse, String nom, String prenom, String date_Naissance, String lieu_Naissance, String sexe) {
        super(userId, type, email, telephone, adresse);
        this.nom = nom;
        this.prenom = prenom;
        this.date_Naissance = date_Naissance;
        this.lieu_Naissance = lieu_Naissance;
        this.sexe = sexe;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate_Naissance() {
        return date_Naissance;
    }

    public String getLieu_Naissance() {
        return lieu_Naissance;
    }

    public String getSexe() {
        return sexe;
    }
}
