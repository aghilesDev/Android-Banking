package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-04-05.
 */

public class CreditView {
    private String idCrédit;
    private String compteId;
    private double montantAcordé;
    private double montantRestant;
    private String datePrelevement;
    private String retard;


    public CreditView(String idCrédit, String compteId, double montantAcordé, double montantRestant, String datePrelevement, String retard) {
        this.idCrédit = idCrédit;
        this.compteId = compteId;
        this.montantAcordé = montantAcordé;
        this.montantRestant = montantRestant;
        this.datePrelevement = datePrelevement;
        this.retard = retard;
    }

    public String getIdCrédit() {
        return idCrédit;
    }

    public String getCompteId() {
        return compteId;
    }

    public double getMontantAcordé() {
        return montantAcordé;
    }

    public double getMontantRestant() {
        return montantRestant;
    }

    public String getDatePrelevement() {
        return datePrelevement;
    }

    public String getRetard() {
        return retard;
    }
}