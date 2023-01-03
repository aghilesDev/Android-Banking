package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;

/**
 * Created by Aghiles on 2017-05-02.
 */

public interface IChangementinformationInteractor<T extends RequestChangementInformation> extends ILogedInteractor {

    public void executeRequest(T requete);


    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void requestExecuted();
        public void requestFailed();
    }
}
