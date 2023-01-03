package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.MotDePasseInvalideException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IChangementMotDePasseInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IChangementinformationInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;
import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;

/**
 * Created by Aghiles on 2017-05-09.
 */

public class ChangementMotDePasseInteractor implements IChangementMotDePasseInteractor {

    private CallBack callBack;
    private IService service;

    public ChangementMotDePasseInteractor(CallBack callBack) {
        this.callBack = callBack;
        service= WebAPIService.getInstance();
    }

    @Override
    public void loginOut() {
            callBack.logedOut();

    }

    @Override
    public void executeRequest(final RequestChangementMotDePasse requete) {

        AsyncTask<Void,Void,Void> task =new AsyncTask<Void, Void, Void>() {
            private int error;

            @Override
            protected Void doInBackground(Void... params) {

                error=0;
                try {
                            service.postRequestChangeMotDePasse( requete);

                    } catch (NoConnectionException e) {
                    error= ErrorCode._NO_CONNECTION;
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
                        break;}
                    case ErrorCode._NOT_AUTHENTIFICATE:{
                        callBack.logedOut();
                        break;}
                    case ErrorCode._ERROR:{
                        callBack.NoConnectionFound();
                        break;}
                    case ErrorCode._MOT_DE_PASSE_INVALIDE:{
                        callBack.requestFailed();
                        break;}
                    default:{
                        callBack.requestExecuted();
                        break;
                    }
                }
            }
        };

        task.execute();

    }
}
