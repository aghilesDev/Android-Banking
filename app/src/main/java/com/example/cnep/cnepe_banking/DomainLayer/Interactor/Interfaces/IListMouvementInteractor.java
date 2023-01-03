package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.MouvementViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-19.
 */

public interface IListMouvementInteractor extends ILogedInteractor{
    public void loadMouvementsRequest(int numeroCompte);

    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void loadMouvementsReponse(ArrayList<MouvementViewModel> mouvements);
    }
}
