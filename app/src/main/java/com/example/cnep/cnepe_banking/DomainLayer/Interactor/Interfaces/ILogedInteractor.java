package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

/**
 * Created by Aghiles on 2017-04-23.
 */

public interface ILogedInteractor extends IConnectedInteractor {

    public final int CONNECTION_ERROR=1;
    public final int AUTHORIZATION_ERROR=2;

    public void loginOut();

    public interface CallBack extends IConnectedInteractor.CallBack {

        public void logedOut();

    }
}
