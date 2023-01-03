package com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-30.
 */

public interface IListAgenceInteractor extends ILogedInteractor {
        public void loadWilayas();
        public void LoadAgences();
        public void LoadAgences(String wilaya);
        public void LoadMoreAgences();
        public void LoadMoreAgences(String wilaya);

        public void loadAgencesRequest();


    public interface CallBack extends ILogedInteractor.CallBack
    {
        public void loadAgencesReponse(ArrayList<AgenceViewModel> agences);
    }
}
