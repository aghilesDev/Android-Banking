package com.example.cnep.cnepe_banking.DataLayer;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Aghiles on 2017-03-23.
 */

public class TestRepository{



    public String getAgence()
    {
        Gson gson=new Gson();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.43.237:5454/api/Agence/5")
                .build();
        try {
            Response response = client.newCall(request).execute();
            //AgenceViewModel agence=gson.fromJson(response.body().toString(),AgenceViewModel.class);
            return " HTTP REQUEST c'est fait!!!!\naaaa"+response.body().string();



        } catch (IOException e) {

            return " HTTP REQUEST echoué!!!"+e.getMessage();
        }

    }



}
