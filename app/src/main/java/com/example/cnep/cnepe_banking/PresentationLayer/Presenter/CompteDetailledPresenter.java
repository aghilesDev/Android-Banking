package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.CompteDetailledInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.ICompteDetailledInteractor;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.RequestCommande;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratCompteDetailled;

/**
 * Created by Aghiles on 2017-04-30.
 */

public class CompteDetailledPresenter extends BasePresenter<ContratCompteDetailled.View> implements ContratCompteDetailled.ActionView,ICompteDetailledInteractor.CallBack{

    ICompteDetailledInteractor interactor;

    public CompteDetailledPresenter(ContratCompteDetailled.View view) {
        attachView(view);
        interactor=new CompteDetailledInteractor(this);
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
    public void onInitialize(String numCompte) {
        interactor.OnInitializing(numCompte);

    }

    @Override
    public void actionCommande(RequestCommande requete) {
        interactor.executeCommande(requete);
    }

    @Override
    public void logedOut() {
        view.logOut();

    }

    @Override
    public void toInitialize(CompteViewModel compte) {
        view.itializing(compte);

    }

    @Override
    public void executedCommande() {
        view.displayExecutedCommande();

    }

    @Override
    public void failedCommande() {
        view.diplayFailedCommande();

    }

    @Override
    public void toLogOut() {
        interactor.loginOut();

    }
}
