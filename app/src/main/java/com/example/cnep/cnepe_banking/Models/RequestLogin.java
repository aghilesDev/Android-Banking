package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-15.
 */

public class RequestLogin {

    private  String identifiant;
    private String motDePasse;

    public RequestLogin(String identifiant, String motDePasse) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isValideMotDepasse()
    {
        return motDePasse.length()>=0;
    }
    public boolean isValideIdentifiant()
    {
        return identifiant.isEmpty()==false;
    }

    public boolean isValide()
    {
        return isValideIdentifiant()&&isValideMotDepasse();
    }

    public boolean isComplete()
    {
        return true;
    }
}
