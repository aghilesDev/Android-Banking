package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-23.
 */

public abstract class RequestCommande {

    public final static int _CHEQUE=1;
    public final static int _CARTE=2;


    private  int rib;
    private String motDePasse;
    private int type;

    public RequestCommande(String motDePasse,int rib,int type) {
        this.motDePasse = motDePasse;
        this.rib=rib;
        this.type = type;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getType() {
        return type;
    }

    public boolean isValide()
    {
        return true;
    }
}
