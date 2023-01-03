package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import android.app.Activity;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListMouvementInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ListMouvementInteractor;
import com.example.cnep.cnepe_banking.Models.ConnectionChecker;
import com.example.cnep.cnepe_banking.Models.MouvementViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractMouvements;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-19.
 */

public class ListMouvementPresenter extends BasePresenter<ContractMouvements.View> implements ContractMouvements.ActionView,IListMouvementInteractor.CallBack {

    private IListMouvementInteractor interactor;
    private ConnectionChecker connectionChecker;

    public ListMouvementPresenter(ContractMouvements.View view) {
        this.attachView(view);
        interactor= new ListMouvementInteractor(this);
        connectionChecker=new ConnectionChecker((Activity)view);
    }

    @Override
    public void onIntialRequest(int numeroCompte) {

        interactor.loadMouvementsRequest(numeroCompte);

    }

    @Override
    public void loadMouvementsReponse(ArrayList<MouvementViewModel> mouvements) {

        view.onIntialReponse(mouvements);

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

    @Override
    public void logedOut() {
    view.logOut();
    }
}
