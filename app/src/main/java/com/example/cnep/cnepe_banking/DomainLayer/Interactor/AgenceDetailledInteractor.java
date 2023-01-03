package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorCode;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IAgenceDetailleInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

/**
 * Created by Aghiles on 2017-04-29.
 */

public class AgenceDetailledInteractor implements IAgenceDetailleInteractor {

    private CallBack callBack;

    private IService service;

    public AgenceDetailledInteractor(CallBack callBack) {
        this.callBack = callBack;
        service= WebAPIService.getInstance();
    }

    @Override
    public void OnInitializing(final int codeAgence) {

        AsyncTask<Void,Void,Void> task=new AsyncTask<Void, Void, Void>() {
            int error=0;
            private AgenceViewModel agence;
            @Override
            protected Void doInBackground(Void... params) {
                agence=null;
                try {
                    agence=service.getAgence(codeAgence);
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
                    case 1:{callBack.NoConnectionFound();
                        break;}
                    case 2:{
                        callBack.NoConnectionFound();
                        break;}
                    default:{
                        callBack.toInitialize(agence);
                        break;
                    }
                }


            }
        };

        task.execute();

    }
}
