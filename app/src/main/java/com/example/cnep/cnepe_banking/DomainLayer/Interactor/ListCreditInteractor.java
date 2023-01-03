package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListCreditInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.CreditView;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces.IListCreditPresenter;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-06.
 */

public class ListCreditInteractor implements IListCreditInteractor {


    private CallBack presenter;
    IService service;


    public ListCreditInteractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }


    @Override
    public void LoadCreditsRequest() {


        new AsyncTask<Void,Void,Void>(){

            int error=0;
            private ArrayList<CreditView> credits;

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    credits=service.getCredits();

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
                        presenter.LoadCreditsReponse(credits);
                        break;
                    }
                }


            }
        }.execute();


    }


    @Override
    public void loginOut() {
        presenter.logedOut();

    }
}
