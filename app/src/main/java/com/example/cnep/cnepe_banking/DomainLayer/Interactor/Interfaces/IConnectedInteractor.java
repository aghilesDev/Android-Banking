package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

/**
 * Created by Aghiles on 2017-04-23.
 */

public interface IConnectedInteractor {



    public interface CallBack
    {
        public void NoConnectionFound();
        boolean isConnected();
    }
}
