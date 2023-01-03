package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-03-26.
 */

public class UserMoral extends User {
    private String Raison_Social;
    private String nif;
    private String nis;

    public UserMoral(String userId, int type, String email, String telephone, String adresse, String raison_Social, String nif, String nis) {
        super(userId, type, email, telephone, adresse);
        Raison_Social = raison_Social;
        this.nif = nif;
        this.nis = nis;
    }

    public String getRaison_Social() {
        return Raison_Social;
    }

    public String getNif() {
        return nif;
    }

    public String getNis() {
        return nis;
    }





}
