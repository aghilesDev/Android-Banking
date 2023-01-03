package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

/**
 * Created by Aghiles on 2017-04-29.
 */

public interface ContratAgenceDetaille {

    public interface ActionView extends ContratConnected.ActionView
    {
        public void onInitialize(int codeAgence);

    }


    public interface View extends  ContratConnected.View
    {
        public void itializing(AgenceViewModel agence);
    }

}
