package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.CreditView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-06.
 */

public interface IListCreditInteractor extends ILogedInteractor{
    public void LoadCreditsRequest();


    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void LoadCreditsReponse(ArrayList<CreditView> credits);

    }
}
