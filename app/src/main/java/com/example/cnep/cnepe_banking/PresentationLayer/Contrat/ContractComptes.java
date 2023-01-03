package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-18.
 */

public interface ContractComptes {


    public interface ActionView extends ContratLoged.ActionView
    {
        public  void onIntialListRequest(int codeAgence);
    }


    public interface View extends ContratLoged.View
    {
        public void onInitialCompteShow(ArrayList<CompteViewModel> comptes);
    }


}
