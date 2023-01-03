package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import android.app.Activity;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListAgenceInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ListAgenceInteractor;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.Models.ConnectionChecker;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractAgences;

import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.AgenceAdapter;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Aghiles on 2017-03-30.
 */

public class ListAgencePresenter extends BasePresenter<ContractAgences.View> implements ContractAgences.ActionView,IListAgenceInteractor.CallBack {

    private AgenceAdapter adapter;
    private IListAgenceInteractor interactor;
    private ArrayList<AgenceViewModel> agences;
    private ConnectionChecker connectionChecker;


    public ListAgencePresenter(ContractAgences.View view) {

        interactor=new ListAgenceInteractor(this);
        this.attachView(view);
        connectionChecker=new ConnectionChecker((Activity)view);

    }

    //nouvelles version

    @Override
    public void onIntialListRequest() {
        interactor.loadAgencesRequest();

    }

    @Override
    public void allAgencesRequest() {
        this.view.showsAgences(this.agences);
    }

    @Override
    public void AgencesRequest(String wilaya) {
        ArrayList<AgenceViewModel> agencesWilaya=new ArrayList<>();
        for(AgenceViewModel agence:this.agences)
        {
            if(agence.getWilaya().equalsIgnoreCase(wilaya))
                agencesWilaya.add(agence);
        }
        this.view.showsAgences(agencesWilaya);

    }

    @Override
    public void loadAgencesReponse(ArrayList<AgenceViewModel> agences) {
        this.agences=agences;
        HashSet<String> wilayas=new HashSet<>();
        for(AgenceViewModel agence:this.agences)
        {
            wilayas.add(agence.getWilaya());
        }
        ArrayList<String> wilayasList= new ArrayList<>();
        wilayasList.addAll(wilayas);

        this.view.onInitialAgenceShow(agences,wilayasList);
    }

    @Override
    public void logedOut() {
        view.logOut();

    }



    @Override
    public void NoConnectionFound() {
        view.noConnection();
    }

    @Override
    public boolean isConnected() {
        return connectionChecker.isOnline();

    }

    @Override
    public void toLogOut() {

        interactor.loginOut();

    }


}
