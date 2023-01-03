package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

/**
 * Created by Aghiles on 2017-04-29.
 */

public interface IAgenceDetailleInteractor  extends IConnectedInteractor{

    public  void OnInitializing(int codeAgence);



    public interface CallBack extends IConnectedInteractor.CallBack
    {
        public void toInitialize(AgenceViewModel agence);
    }
}
