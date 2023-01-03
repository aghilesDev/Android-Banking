package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.TestRepository;
import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.MotDePasseInvalideException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IloginInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces.ILoginPresenter;

/**
 * Created by Aghiles on 2017-03-25.
 */

public class LoginInteractor implements IloginInteractor {


    CallBack presenter;
    IService service;


    public LoginInteractor(CallBack presenter)
    {
        this.presenter=presenter;
        service= WebAPIService.newInstance();
    }

    @Override
    public void loginCase(final RequestLogin requestLogin) {



        new AsyncTask<Void, Void, Void>() {
            int error;
            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                    service.postRequestLogIn(requestLogin);

                } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
                } catch (MotDePasseInvalideException e) {
                    error=ErrorCode._MOT_DE_PASSE_INVALIDE;
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
                        break;}
                    case ErrorCode._MOT_DE_PASSE_INVALIDE:{
                        presenter.loginNotAuthorized();
                        break;}
                    case ErrorCode._ERROR:{
                        presenter.NoConnectionFound();
                        break;}
                    default:{
                        presenter.loginAuthorized();
                    }
                }
            }
        }.execute();









    }


}
