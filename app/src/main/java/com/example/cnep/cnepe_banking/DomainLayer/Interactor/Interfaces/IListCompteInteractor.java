package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-31.
 */

public interface IListCompteInteractor extends ILogedInteractor{

    public void LoadCompteRequest(int codeAgence);

    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void  LoadComptesReponse(ArrayList<CompteViewModel> comptes);
    }
}
