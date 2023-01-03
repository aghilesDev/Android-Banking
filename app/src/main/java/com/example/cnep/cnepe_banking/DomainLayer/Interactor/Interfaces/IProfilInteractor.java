package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.User;

/**
 * Created by Aghiles on 2017-05-01.
 */

public interface IProfilInteractor  extends ILogedInteractor{

    public void onInitializing();


    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void toInitialize(User user);
    }
}
