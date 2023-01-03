package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;

/**
 * Created by Aghiles on 2017-05-02.
 */

public interface ContratChangementinformation {

    public interface ActionView<T extends  RequestChangementInformation> extends ContratLoged.ActionView
    {
        public void AcitonRequest(T requete);
    }

    public interface View extends ContratLoged.View
    {
        public void displayRequestExecuted();
        public void displayRequestFailed(String message);
    }
}
