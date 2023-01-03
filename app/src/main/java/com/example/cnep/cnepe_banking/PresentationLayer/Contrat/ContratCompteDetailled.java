package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.RequestCommande;

/**
 * Created by Aghiles on 2017-04-30.
 */

public interface ContratCompteDetailled {

    public interface ActionView extends ContratLoged.ActionView
    {
        public void onInitialize(String rib);
        public void actionCommande(RequestCommande requete);

    }


    public interface View extends  ContratLoged.View
    {
        public void itializing(CompteViewModel compte);
        public void displayExecutedCommande();
        public void diplayFailedCommande();
    }
}
