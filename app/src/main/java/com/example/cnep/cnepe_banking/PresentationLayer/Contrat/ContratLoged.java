package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;



/**
 * Created by Aghiles on 2017-04-23.
 */

public interface ContratLoged{


    public interface ActionView extends ContratConnected.ActionView
    {
        public void toLogOut();
    }

    public interface View extends ContratConnected.View
    {
        public void logOut();
    }


}
