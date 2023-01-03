package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-02.
 */

public class RequestChangementAdresse extends RequestChangementInformation {
    public RequestChangementAdresse(String information, String motDePasse) {
        super(_ADRESSE, information, motDePasse);
    }

    @Override
    public boolean informationIsValide() {
        return true;
    }
}
