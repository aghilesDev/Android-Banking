package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListAgenceInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-30.
 */

public class ListAgenceInteractor implements IListAgenceInteractor {

    private CallBack presenter;
    private ArrayList<AgenceViewModel> agences;
    private IService service;



    public ListAgenceInteractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }

    @Override
    public void loadWilayas() {
        ArrayList<String> villes=new ArrayList<>();
        villes.add("Alger");
        villes.add("Boumerdes");
        villes.add("Tizi-Ouzou");

        //presenter.sendWilayas(villes);
    }






    @Override
    public void LoadAgences() {
       // ArrayList<AgenceViewModel> agences=new  ArrayList<>();
        //agences.add(new AgenceViewModel(125,"Alger","Alger","10 rue ,place 1 er mai","021362156","agce@cnepbanque.dz"));
       // agences.add(new AgenceViewModel(126,"boumerdes","Boumerdes","10 rue ,place 1 er mai","021362156","agce@cnepbanque.dz"));
       // agences.add(new AgenceViewModel(127,"Tizi-Ouzou","Tizi-Ouzou","10 rue ,place 1 er mai","021362156","agce@cnepbanque.dz"));
       //presenter.sendAgences(agences);
    }

    @Override
    public void LoadAgences(String wilaya) {
        //ArrayList<AgenceViewModel> agences=new  ArrayList<>();
        //agences.add(new AgenceViewModel(127,"Tizi-Ouzou","Tizi-Ouzou","10 rue ,place 1 er mai","021362156","agce@cnepbanque.dz"));
       // presenter.sendAgences(agences);
    }

    @Override
    public void LoadMoreAgences() {

    }

    @Override
    public void LoadMoreAgences(String wilaya) {

    }
    //nouveau

    @Override
    public void loadAgencesRequest() {
        agences=new  ArrayList<>();






        new AsyncTask<Void, Void, Void>( ) {
            int error=0;

            @Override
            protected Void doInBackground(Void... params) {
                try {



                    agences=service.getUserAgences();


                }catch (NoConnectionException e2)
                {
                 error= ErrorCode._NO_CONNECTION;
                }catch (NotAuthorizedException e2)
                {
                    error=ErrorCode._NOT_AUTHENTIFICATE;

                } catch (ErrorException e) {
                    error=ErrorCode._ERROR;
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);



                switch (error)
                {
                    case ErrorCode._NO_CONNECTION:{
                        presenter.NoConnectionFound();
                        break;
                    }
                    case ErrorCode._NOT_AUTHENTIFICATE:{
                        presenter.logedOut();
                        break;
                    }
                    case ErrorCode._ERROR:{
                        presenter.NoConnectionFound();
                        break;
                    }
                    default: {
                        presenter.loadAgencesReponse(agences);
                        break;
                    }
                }


            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);;



    }

    @Override
    public void loginOut() {

        presenter.logedOut();
    }
}
