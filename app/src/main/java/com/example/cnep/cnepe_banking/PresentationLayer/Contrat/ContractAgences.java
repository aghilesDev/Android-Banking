package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-18.
 */

public interface ContractAgences {

    public interface ActionView extends ContratLoged.ActionView
    {
        public  void onIntialListRequest();
        public void allAgencesRequest();
        public  void AgencesRequest(String wilaya);
    }

    public interface  View extends ContratLoged.View
    {
        public void onInitialAgenceShow(ArrayList<AgenceViewModel> agences, ArrayList<String> wilayas);
        public  void showsAgences(ArrayList<AgenceViewModel> agences);

    }



}
