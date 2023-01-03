package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-03-26.
 */

public class UserProfessionnel extends User {
    private String nom;
    private String prenom;
    private String date_Naissance;
    private String lieu_Naissance;
    private String sexe;
    private String nif;
    private String nis;

    public UserProfessionnel(String userId, int type, String email, String telephone, String adresse, String nom, String prenom, String date_Naissance, String lieu_Naissance, String sexe, String nif, String nis) {
        super(userId, type, email, telephone, adresse);
        this.nom = nom;
        this.prenom = prenom;
        this.date_Naissance = date_Naissance;
        this.lieu_Naissance = lieu_Naissance;
        this.sexe = sexe;
        this.nif = nif;
        this.nis = nis;
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

    public String getNif() {
        return nif;
    }

    public String getNis() {
        return nis;
    }
}
