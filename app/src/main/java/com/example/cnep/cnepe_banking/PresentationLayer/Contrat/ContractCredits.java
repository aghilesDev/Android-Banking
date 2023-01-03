package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.CreditView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-18.
 */

public interface ContractCredits {

    public interface ActionView extends ContratLoged.ActionView
    {
        public  void onIntialListRequest();
    }


    public interface View extends ContratLoged.View
    {
        public void onInitialCreditShow(ArrayList<CreditView> credits);
    }
}
