package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-09.
 */

public class RequestChangementMotDePasse extends RequestChangementInformation{
    protected String confirmationInformation;

    public RequestChangementMotDePasse(String information, String motDePasse,String confirmationInformation) {
        super(_Autre, information, motDePasse);
        this.confirmationInformation=confirmationInformation;
    }

    @Override
    public boolean informationIsValide() {
        return this.motDePasseIsValide();
    }


    public  boolean estConfirmee()
    {
        return confirmationInformation.equals(getInformation());
    }

    public String getConfirmationInformation() {
        return confirmationInformation;
    }
}
