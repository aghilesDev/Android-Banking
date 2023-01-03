package com.example.cnep.cnepe_banking.DataLayer;

import android.util.Log;

import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.MotDePasseInvalideException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.CreditView;
import com.example.cnep.cnepe_banking.Models.MouvementViewModel;
import com.example.cnep.cnepe_banking.Models.RequestChangementEmail;
import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;
import com.example.cnep.cnepe_banking.Models.RequestChangementTelephone;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCarte;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCheque;
import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.example.cnep.cnepe_banking.Models.ResponseAllAgences;
import com.example.cnep.cnepe_banking.Models.ResponseLogin;
import com.example.cnep.cnepe_banking.Models.User;
import com.example.cnep.cnepe_banking.Models.UserMoral;
import com.example.cnep.cnepe_banking.Models.UserParticulier;
import com.example.cnep.cnepe_banking.Models.UserProfessionnel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-05-14.
 */

public class WebAPIService implements IService {

    private static WebAPIService instance;
    private ResponseLogin logInformation;
    private String baseUrl="http://192.168.43.237:5454/";
    private static MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    private WebAPIService() {
        logInformation= new ResponseLogin("");
    }

public static WebAPIService newInstance()
{
    instance=new WebAPIService();
    return instance;
}


    public static WebAPIService getInstance() {
        if(instance==null)
            instance=new WebAPIService();
        return instance;
    }

    private Request.Builder TokenRequestBuilder()
    {return new Request.Builder().header("Authorization","bearer "+logInformation.getAccess_token());}


    private String getUrl(String suffix)
    {
        return baseUrl+"api"+suffix;
    }



    @Override
    public ArrayList<CompteViewModel> getUserComptes(int codeAgence) throws NoConnectionException, NotAuthorizedException, ErrorException {

        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/compte?agenceid="+codeAgence)).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String comptesInfo="";
        Gson gson=new Gson();

        try {
            comptesInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<CompteViewModel> comptes;

        Type comptesListeType=new  TypeToken<ArrayList<CompteViewModel>>(){}.getType();
        comptes=gson.fromJson(comptesInfo,comptesListeType);


        return comptes;
    }

    @Override
    public CompteViewModel getCompte(String rib) throws NoConnectionException, NotAuthorizedException, ErrorException {

        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/Agence/all")).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String compteInfo="";
        Gson gson=new Gson();

        try {
            compteInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompteViewModel compte;
        compte=gson.fromJson(compteInfo,CompteViewModel.class);


        return compte;
    }

    @Override
    public ArrayList<MouvementViewModel> getMouvements(int idcompte) throws NoConnectionException, NotAuthorizedException, ErrorException {
        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/mouvement?idcompte="+idcompte)).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String mouvementsInfo="";
        Gson gson=new Gson();

        try {
            mouvementsInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("TEST", mouvementsInfo);
        ArrayList<MouvementViewModel> mouvements;

        Type mouvementsListeType=new  TypeToken<ArrayList<MouvementViewModel>>(){}.getType();
        mouvements=gson.fromJson(mouvementsInfo,mouvementsListeType);


        return mouvements;
    }

    @Override
        public ArrayList<String> getWilayas() throws NoConnectionException, ErrorException {

        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url(getUrl("/Agence/wilaya"))//ajouter la page
                .build();
        Response response=null;
        try {
            response= client.newCall(request).execute();
        } catch (IOException e) {
            throw new NoConnectionException();
        }



        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                Log.i("test","alottttrs");
                throw new NoConnectionException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String wilayasInfo="";
        Gson gson=new Gson();

        try {
            wilayasInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> wilayas;

        Type wilayasListeType=new  TypeToken<ArrayList<String>>(){}.getType();
        wilayas=gson.fromJson(wilayasInfo,wilayasListeType);


        return wilayas;
    }

    @Override
    public ResponseAllAgences getAllAgences(int page) throws NoConnectionException, ErrorException {
        Log.i("test","page="+page);
        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url(getUrl("/Agence?page="+page))//ajouter la page
                .build();
        Response response=null;
        try {
            response= client.newCall(request).execute();
        } catch (IOException e) {
            throw new NoConnectionException();
        }

        switch (response.code())
        {
            case 200:{break;}
            case 404:{
                throw  new NoConnectionException();
            }
            default:{
                throw new ErrorException();
            }
        }


        Gson gson= new Gson();
        ResponseAllAgences responseAllAgences=null;
        try {
            responseAllAgences=gson.fromJson(response.body().string(),ResponseAllAgences.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseAllAgences;
    }

    @Override
    public ResponseAllAgences getAllAgences(String wilaya,int page) throws NoConnectionException, ErrorException {
        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url(getUrl("/Agence?w="+wilaya+"&page="+page))//ajouter la string wilayas plus la page
                .build();
        Response response=null;
        try {
            response= client.newCall(request).execute();
        } catch (IOException e) {
            throw new NoConnectionException();
        }

        switch (response.code())
        {
            case 200:{break;}
            case 404:{
                throw  new NoConnectionException();
            }
            default:{
                throw new ErrorException();
            }
        }

        Log.i("TEST","wilaya :"+wilaya+" page:"+page);
        //

        Gson gson= new Gson();
        ResponseAllAgences responseAllAgences=null;
        try {
            responseAllAgences=gson.fromJson(response.body().string(),ResponseAllAgences.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseAllAgences;
    }

    @Override
    public ArrayList<AgenceViewModel> getUserAgences() throws NoConnectionException, NotAuthorizedException, ErrorException {
        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/AgenceClient")).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
                }
            default:{
                throw  new ErrorException();
            }
        }
        String agencesInfo="";
        Gson gson=new Gson();

        try {
            agencesInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<AgenceViewModel> agences;

        Type agenceListeType=new  TypeToken<ArrayList<AgenceViewModel>>(){}.getType();
        agences=gson.fromJson(agencesInfo,agenceListeType);


        return agences;
    }

    @Override
    public AgenceViewModel getAgence(int codeAgence) throws NoConnectionException, ErrorException {


        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url(getUrl("/Agence/"+codeAgence))
                .build();
        Response response=null;
        try {
            response= client.newCall(request).execute();
        } catch (IOException e) {
            throw new NoConnectionException();
        }
        switch (response.code())
        {
            case 200:{break;}
            case 404:{
                throw  new NoConnectionException();
            }
            default:{
                throw new ErrorException();
            }
        }

        Gson gson= new Gson();
        AgenceViewModel agenceViewModel=null;
        try {
            agenceViewModel=(AgenceViewModel)gson.fromJson(response.body().string(),AgenceViewModel.class);
            System.out.println(agenceViewModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agenceViewModel;
    }

    @Override
    public ArrayList<CreditView> getCredits() throws NoConnectionException, NotAuthorizedException, ErrorException {
        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/Credit")).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String creditsInfo="";
        Gson gson=new Gson();

        try {
            creditsInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<CreditView> credits;

        Type creditListeType=new  TypeToken<ArrayList<CreditView>>(){}.getType();
        credits=gson.fromJson(creditsInfo,creditListeType);


        return credits;
    }

    @Override
    public User getProfile() throws NoConnectionException, NotAuthorizedException, ErrorException {
        OkHttpClient client=new OkHttpClient();

        Request request=TokenRequestBuilder().
                url(getUrl("/user")).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        String userInfo="";
        Gson gson=new Gson();

        try {
            userInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user;
        user=gson.fromJson(userInfo,User.class);
        switch (user.getType())
        {
            case User.PARTICULIER:{
                user=gson.fromJson(userInfo,UserParticulier.class);
                break;
            }
            case User.PROFESSIONNEL:{
                user=gson.fromJson(userInfo,UserProfessionnel.class);
                break;
            }
            case User.MORAL:{
                user=gson.fromJson(userInfo,UserMoral.class);
                break;
            }
            default:{
                break;
            }
        }

        return user;
    }

    @Override
    public int postRequestCheque(RequestCommandeCheque requete) throws NoConnectionException, NotAuthorizedException, ErrorException, MotDePasseInvalideException {
        OkHttpClient client=new OkHttpClient();
        Gson gson=new Gson();
        String json =gson.toJson(requete);

        RequestBody body = RequestBody.create(JSON, json);

        Request request=TokenRequestBuilder().
                url(getUrl("/compte/cheque")). //modifier url
                post(body).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            case 400:{
                throw new MotDePasseInvalideException();
            }
            default:{
                throw  new ErrorException();
            }
        }

        return 0;
    }

    @Override
    public int postRequestCarte(RequestCommandeCarte requete) throws NoConnectionException, NotAuthorizedException, ErrorException, MotDePasseInvalideException {

        OkHttpClient client=new OkHttpClient();
        Gson gson=new Gson();
        String json =gson.toJson(requete);

        RequestBody body = RequestBody.create(JSON, json);

        Request request=TokenRequestBuilder().
                url(getUrl("/compte/carte")). //modifier url
                post(body).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            case 400:{
                throw new MotDePasseInvalideException();
            }
            default:{
                throw  new ErrorException();
            }
        }

        return 0;
    }


    @Override
    public int postRequestChangeEmail(RequestChangementEmail requete) throws NoConnectionException, NotAuthorizedException, ErrorException, MotDePasseInvalideException {

        OkHttpClient client=new OkHttpClient();
        Gson gson=new Gson();
        String json =gson.toJson(requete);

        RequestBody body = RequestBody.create(JSON, json);

        Request request=TokenRequestBuilder().
                url(getUrl("/user/mail")). //modifier url
                post(body).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            case 400:{
                throw new MotDePasseInvalideException();
            }
            default:{
                throw  new ErrorException();
            }
        }
     /*   String comptesInfo="";


        try {
            comptesInfo=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<CompteViewModel> comptes;

        Type comptesListeType=new  TypeToken<ArrayList<CompteViewModel>>(){}.getType();
        comptes=gson.fromJson(comptesInfo,comptesListeType);*/




        return 0;
    }

    @Override
    public int postRequestChangeTelephone(RequestChangementTelephone requete) throws NoConnectionException, NotAuthorizedException, ErrorException, MotDePasseInvalideException {

        OkHttpClient client=new OkHttpClient();
        Gson gson=new Gson();
        String json =gson.toJson(requete);
        Log.i("TEST",json);
        RequestBody body = RequestBody.create(JSON, json);

        Request request=TokenRequestBuilder().
                url(getUrl("/user/Tel")). //modifier url
                post(body).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            case 400:{
            throw new MotDePasseInvalideException();
        }
            default:{
                throw  new ErrorException();
            }
        }


        return 0;
    }

    @Override
    public int postRequestChangeMotDePasse(RequestChangementMotDePasse requete) throws NoConnectionException, NotAuthorizedException, ErrorException, MotDePasseInvalideException {

        OkHttpClient client=new OkHttpClient();
        Gson gson=new Gson();
        String json =gson.toJson(requete);

        RequestBody body = RequestBody.create(JSON, json);

        Request request=TokenRequestBuilder().
                url(getUrl("/user/mdp")). //modifier url
                post(body).
                build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (response.code())
        {
            case 200: {
                break;
            }
            case 404:{
                throw new NoConnectionException();
            }
            case 401:{
                throw new NotAuthorizedException();
            }
            case 400:{
                throw new MotDePasseInvalideException();
            }
            default:{
                throw  new ErrorException();
            }
        }
        return 0;
    }

    @Override
    public ResponseLogin postRequestLogIn(RequestLogin requestLogin) throws NoConnectionException, MotDePasseInvalideException, ErrorException {

        OkHttpClient client= new OkHttpClient();




        RequestBody body= new FormEncodingBuilder()
                .add("username", requestLogin.getIdentifiant())
                .add("password", requestLogin.getMotDePasse())
                .add("grant_type","password")
                .build();

        Request request = new Request.Builder()
                .url(baseUrl+"token")
                .header("Content-Type","application/x-www-form-urlencoded")
                .post(body)
                .build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        } catch (IOException e) {
            throw  new NoConnectionException();
        }

        switch (response.code())
        {
            case 200:{
                break;
            }
            case 400:{
                throw new MotDePasseInvalideException();
            }
            case 404:{

                throw  new NoConnectionException();
            }
            default:
            {
                throw  new ErrorException();
            }
        }

        Gson gson= new Gson();

        try {
            logInformation=(ResponseLogin)gson.fromJson(response.body().string(),ResponseLogin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("token",logInformation.getAccess_token());


        return null;
    }

    @Override
    public int postRequestLogOut() throws NoConnectionException, NotAuthorizedException, ErrorException {
        return 0;
    }
}
