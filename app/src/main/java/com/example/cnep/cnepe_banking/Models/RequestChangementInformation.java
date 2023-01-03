package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-02.
 */

public abstract class RequestChangementInformation {


    public final static  int _EMAIL=1;
    public final static  int _TELEPHONE=2;
    public final static  int _ADRESSE=3;
    public final static  int _Autre=3;


    private final int typeRequete;
    private String information;
    private String motDePasse;

    public RequestChangementInformation(int typeRequete, String information, String motDePasse) {
        this.typeRequete = typeRequete;
        this.information = information;
        this.motDePasse = motDePasse;
    }

    public int getTypeRequete() {
        return typeRequete;
    }

    public String getInformation() {
        return information;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }



    public boolean motDePasseIsValide()
    {
        if(motDePasse.length()<4)
            return false;

        return true;
    }

    public abstract  boolean informationIsValide();
}
