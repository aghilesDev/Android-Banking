package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-23.
 */

public class RequestCommandeCarte extends RequestCommande {
    public RequestCommandeCarte(int rib,String motDePasse) {
        super(motDePasse,rib,_CARTE);
    }
}
