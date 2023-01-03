package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListAllAgencesInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ListAllAgencesInteractor;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratAllAgences;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-23.
 */

public class ListAllAgencesPresenter extends BasePresenter<ContratAllAgences.View> implements ContratAllAgences.ActionView,IListAllAgencesInteractor.CallBack{

    IListAllAgencesInteractor interactor=new ListAllAgencesInteractor(this);
    private int page;

    public ListAllAgencesPresenter(ContratAllAgences.View view) {
        this.attachView(view);
        page=0;
    }

    @Override
    public void NoConnectionFound() {
        view.noConnection();

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void onInitialize() {

        page=0;
        interactor.onInitializing();

    }

    @Override
    public void LoadAgences(String wilaya) {
        page=0;
        if(wilaya.equalsIgnoreCase("Tous"))
            interactor.loadMoreAgences(page);
        else
            interactor.loadMoreAgences(wilaya,page);

    }

    @Override
    public void LoadMoreAgences(String wilaya) {

       if(wilaya.equalsIgnoreCase("Tous"))
            interactor.loadMoreAgences(page);
        else
            interactor.loadMoreAgences(wilaya,page);
    }

    @Override
    public void toInitialize(ArrayList<AgenceViewModel> agences, ArrayList<String> wilayas, boolean hasMore) {
        page++;
        wilayas.add(0,"Tous");//on ajoute la possibilité d'afficher toutes les agences.
        view.Initializing(agences,wilayas,hasMore);

    }

    @Override
    public void receiveMoreAgences(ArrayList<AgenceViewModel> agences, boolean hasMore) {
        page++;
        view.showMoreAgences(agences,hasMore);

    }
}
