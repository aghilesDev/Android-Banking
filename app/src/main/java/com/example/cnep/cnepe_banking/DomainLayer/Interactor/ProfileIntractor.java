package com.example.cnep.cnepe_banking.DomainLayer.Interactor;

import android.os.AsyncTask;

import com.example.cnep.cnepe_banking.DataLayer.WebAPIService;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IProfilInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Repository.IService;
import com.example.cnep.cnepe_banking.Models.User;
import com.example.cnep.cnepe_banking.Models.UserParticulier;
import com.example.cnep.cnepe_banking.Models.UserProfessionnel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-05-01.
 */

public class ProfileIntractor implements IProfilInteractor {

    private CallBack presenter;
    private IService service;


    public ProfileIntractor(CallBack presenter) {
        this.presenter = presenter;
        service= WebAPIService.getInstance();
    }

    @Override
    public void onInitializing() {

        AsyncTask<Void,Void,Void> task= new AsyncTask<Void, Void, Void>() {
            private User user;
            private int error;
            @Override
            protected Void doInBackground(Void... params) {
                error=0;
                try {
                    user=service.getProfile();
                } catch (NoConnectionException e) {
                    e.printStackTrace();
                } catch (NotAuthorizedException e) {
                    e.printStackTrace();
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                presenter.toInitialize(user);
            }
        };
        task.execute();

    }

    @Override
    public void loginOut() {

        presenter.logedOut();

    }
}
