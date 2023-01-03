package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;

/**
 * Created by Aghiles on 2017-05-09.
 */

public interface IChangementMotDePasseInteractor extends ILogedInteractor{

    public void executeRequest(RequestChangementMotDePasse requete);


    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void requestExecuted();
        public void requestFailed();
    }

}
