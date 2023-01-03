package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.MouvementViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-19.
 */

public interface ContractMouvements {

    public interface ActionView extends ContratLoged.ActionView
    {
        public void onIntialRequest(int numeroCompte);
    }
    public interface View extends ContratLoged.View
    {
        public void onIntialReponse(ArrayList<MouvementViewModel> mouvements);

    }
}
