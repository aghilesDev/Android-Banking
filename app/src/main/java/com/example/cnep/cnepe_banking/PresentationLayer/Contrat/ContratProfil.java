package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.User;

/**
 * Created by Aghiles on 2017-05-01.
 */

public interface ContratProfil {

    public interface ActionView extends ContratLoged.ActionView
    {
        public void onInitialize();
    }

    public interface View extends  ContratLoged.View
    {
        public  void initializing(User user);
    }
}
