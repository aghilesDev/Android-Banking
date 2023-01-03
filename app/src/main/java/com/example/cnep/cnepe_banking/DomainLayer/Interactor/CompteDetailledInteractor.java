package com.example.cnep.cnepe_banking.DomainLayer.Interactor;


import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.MotDePasseInvalideException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.ICompteDetailledInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.RequestCommande;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCarte;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCheque;

/**
 * Created by Aghiles on 2017-04-30.
 */

public class CompteDetailledInteractor implements ICompteDetailledInteractor{

    private CallBack callBack;
    private IService service;


    public CompteDetailledInteractor(CallBack callBack) {
        this.callBack = callBack;
        service= WebAPIService.getInstance();
    }

    @Override
    public void OnInitializing(final String numCompte) {



        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {
            private CompteViewModel compte;
            private int error;
            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                    compte=service.getCompte(numCompte);
                } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
                } catch (NotAuthorizedException e) {
                    error= ErrorCode._NOT_AUTHENTIFICATE;
                } catch (ErrorException e) {
                    error= ErrorCode._ERROR;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                switch (error){
                    case ErrorCode._NO_CONNECTION:{
                        callBack.NoConnectionFound();
                        break;
                    }
                    case ErrorCode._NOT_AUTHENTIFICATE:{
                        callBack.logedOut();
                        break;
                    }
                    case ErrorCode._ERROR:{
                        callBack.NoConnectionFound();
                        break;
                    }
                    default:{
                        callBack.toInitialize(compte);
                        break;
                    }
                }
            }
        };

        task.execute();


    }

    @Override
    public void executeCommande(final RequestCommande requestCommande) {

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {
            private  int error;
            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                switch (requestCommande.getType())
                {
                    case RequestCommande._CARTE:{
                            service.postRequestCarte((RequestCommandeCarte) requestCommande);
                        break;
                    }
                    case RequestCommande._CHEQUE:{
                        service.postRequestCheque((RequestCommandeCheque) requestCommande);
                        break;
                    }
                    default:{
                        break;
                    }
                }
                } catch (NoConnectionException e) {
                    error=ErrorCode._NO_CONNECTION;
                } catch (NotAuthorizedException e) {
                    error=ErrorCode._NOT_AUTHENTIFICATE;
                } catch (ErrorException e) {
                    error=ErrorCode._ERROR;
                } catch (MotDePasseInvalideException e) {
                    error=ErrorCode._MOT_DE_PASSE_INVALIDE;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                switch (error)
                {
                    case ErrorCode._NO_CONNECTION:{
                        callBack.NoConnectionFound();
                    break;
                    }
                    case ErrorCode._NOT_AUTHENTIFICATE:{
                        callBack.logedOut();
                        break;
                    }
                    case ErrorCode._ERROR:{
                        callBack.NoConnectionFound();
                        break;
                    }
                    case ErrorCode._MOT_DE_PASSE_INVALIDE:{
                        callBack.failedCommande();
                        break;
                    }
                    default:{
                        callBack.executedCommande();
                        break;
                    }
                }

            }
        };

        task.execute();

    }

    @Override
    public void loginOut() {
        callBack.logedOut();

    }
}
