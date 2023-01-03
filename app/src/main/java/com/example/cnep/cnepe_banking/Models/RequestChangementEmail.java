package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-02.
 */

public class RequestChangementEmail extends RequestChangementInformation {




    public RequestChangementEmail(String information, String motDePasse) {
        super(_EMAIL, information, motDePasse);
    }


    @Override
    public boolean informationIsValide() {
        return true;
    }
}
