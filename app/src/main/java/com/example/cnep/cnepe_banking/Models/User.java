package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-03-26.
 */

public class User  {

    private String userId;
    private int type ;
    private String email;
    private String telephone;
    private String adresse;


    public static final int PARTICULIER=1;
    public static final int MORAL=2;
    public static final int PROFESSIONNEL=3;


    public User(String userId, int type, String email, String telephone, String adresse) {
        this.userId = userId;
        this.type = type;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public String getUserId() {
        return userId;
    }

    public int getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
