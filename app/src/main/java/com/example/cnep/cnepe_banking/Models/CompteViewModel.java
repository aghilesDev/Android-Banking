package com.example.cnep.cnepe_banking.Models;

import java.io.Serializable;

/**
 * Created by Aghiles on 2017-03-31.
 */

public class CompteViewModel implements Serializable{
    public int id;
    public String compteId;
    public String rib;
    public String typeCompte;
    public String DateMaj;
    public double solde;
    private boolean commande;

    public CompteViewModel(int id,String compteId, String rib, String type, String DateMaj , double solde, boolean commande) {
        this.id = id;
        this.compteId = compteId;
        this.rib = rib;
        this.typeCompte = type;
        this.DateMaj=DateMaj;
        this.solde = solde;
        this.commande=commande;
    }

    public int getId() {
        return id;
    }

    public boolean isCommande() {
        return commande;
    }

    public String getCompteId() {
        return compteId;
    }

    public String getRib() {
        return rib;
    }

    public String getType() {
        return typeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public String getDateMaj() {
        return DateMaj;
    }

    public void setCommande(boolean commande) {
        this.commande = commande;
    }
}
