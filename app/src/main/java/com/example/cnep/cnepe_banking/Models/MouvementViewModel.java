package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-04-19.
 */

public class MouvementViewModel {

    private String date;
    private String intitule;
    private double montant;

    public MouvementViewModel(String date, String intitule, double montant) {
        this.date = date;
        this.intitule = intitule;
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public String getIntitule() {
        return intitule;
    }

    public double getMontant() {
        return montant;
    }
}
