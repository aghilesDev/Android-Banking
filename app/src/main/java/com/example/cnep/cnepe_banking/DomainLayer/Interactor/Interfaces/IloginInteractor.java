package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.RequestLogin;

/**
 * Created by Aghiles on 2017-03-25.
 */

public interface IloginInteractor extends  IConnectedInteractor{

    public void loginCase(RequestLogin requestLogin);



    public interface CallBack extends IConnectedInteractor.CallBack{

        public void loginAuthorized();
        public void loginNotAuthorized();

    }
}
