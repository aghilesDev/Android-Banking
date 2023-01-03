package com.example.cnep.cnepe_banking.Models;

/**
 * Created by Aghiles on 2017-05-14.
 */

public class ResponseLogin {

    private String access_token;

    public ResponseLogin(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
