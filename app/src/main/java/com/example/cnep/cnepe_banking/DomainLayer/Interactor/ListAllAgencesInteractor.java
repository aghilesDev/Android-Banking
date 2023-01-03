package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListAllAgencesInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.ResponseAllAgences;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-23.
 */

public class ListAllAgencesInteractor implements IListAllAgencesInteractor {

    private CallBack presenter;
    IService service;

    public ListAllAgencesInteractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }

    @Override
    public void onInitializing() {

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {

            private ResponseAllAgences responseAllAgences;
            private ArrayList<String> wilayas;
            private int error;

            @Override
            protected Void doInBackground(Void... params) {
            error=0;
                try {
                    wilayas=service.getWilayas();
                    responseAllAgences=service.getAllAgences(0);
                } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
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
                    case ErrorCode._ERROR:{
                        presenter.NoConnectionFound();
                        break;
                    }
                    default:{
                        presenter.toInitialize(responseAllAgences.getAgences(),wilayas,responseAllAgences.isHasMore());
                        break;
                    }
                }

            }
        };

        task.execute();




    }

    @Override
    public void loadMoreAgences(final int page) {

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {

            private ResponseAllAgences responseAllAgences;
            private ArrayList<String> wilayas;
            private int error;

            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                    responseAllAgences=service.getAllAgences(page);//changer pour faire une incrémentation automatique
                } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
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
                    case ErrorCode._ERROR:{
                        presenter.NoConnectionFound();
                        break;
                    }
                    default:{
                        presenter.receiveMoreAgences(responseAllAgences.getAgences(),responseAllAgences.isHasMore());
                        break;
                    }
                }

            }
        };
        task.execute();


    }

    @Override
    public void loadMoreAgences(final String wilaya, final int page) {

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {

            private ResponseAllAgences responseAllAgences;
            private ArrayList<String> wilayas;
            private int error;

            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                    responseAllAgences=service.getAllAgences(wilaya,page);//changer pour faire une incrémentation automatique
                } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
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
                    case ErrorCode._ERROR:{
                        presenter.NoConnectionFound();
                        break;
                    }
                    default:{
                        presenter.receiveMoreAgences(responseAllAgences.getAgences(),responseAllAgences.isHasMore());
                        break;
                    }
                }

            }
        };

        task.execute();


    }
}
