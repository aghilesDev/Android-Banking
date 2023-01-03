package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListMouvementInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.MouvementViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-19.
 */

public class ListMouvementInteractor implements IListMouvementInteractor{

    private CallBack presenter;
    private IService service;



    public ListMouvementInteractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }


    @Override
    public void loadMouvementsRequest(final int numeroCompte) {


        new AsyncTask<Void, Void, Void>( ) {

            int error;
            private ArrayList<MouvementViewModel> mouvements;

            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {

                mouvements=service.getMouvements(numeroCompte);


                }catch (NoConnectionException e)
                {
                    error= ErrorCode._NO_CONNECTION;
                }catch (NotAuthorizedException e1)
                {
                    error=ErrorCode._NOT_AUTHENTIFICATE;
                } catch (ErrorException e2) {
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
                    default:
                    {
                        presenter.loadMouvementsReponse(mouvements);
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
