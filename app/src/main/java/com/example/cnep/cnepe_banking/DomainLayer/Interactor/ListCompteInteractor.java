package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListCompteInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-31.
 */

public class ListCompteInteractor implements IListCompteInteractor {
    private CallBack presenter;
    private IService service;


    public ListCompteInteractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }

    @Override
    public void LoadCompteRequest(final int codeAgence) {

        new AsyncTask<Void, Void, Void>( ) {

            int error;
            private ArrayList<CompteViewModel> comptes;
            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {


                    comptes=service.getUserComptes(codeAgence);


                }catch (NoConnectionException e1)
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
                    case ErrorCode._NO_CONNECTION: {
                        presenter.NoConnectionFound();
                        break;
                    }
                    case ErrorCode._NOT_AUTHENTIFICATE: {
                        presenter.logedOut();
                        break;
                    }
                    case ErrorCode._ERROR: {
                        presenter.NoConnectionFound();
                        break;
                    }
                    default: {
                        presenter.LoadComptesReponse(comptes);
                        break;
                    }
                }

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);


    }

    @Override
    public void loginOut() {

        presenter.logedOut();

    }
}
