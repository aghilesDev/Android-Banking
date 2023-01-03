package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.AgenceDetailledInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IAgenceDetailleInteractor;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratAgenceDetaille;

/**
 * Created by Aghiles on 2017-04-29.
 */

public class AgenceDetailledPresenter extends BasePresenter<ContratAgenceDetaille.View> implements ContratAgenceDetaille.ActionView,IAgenceDetailleInteractor.CallBack {

    private IAgenceDetailleInteractor interactor;

    public AgenceDetailledPresenter(ContratAgenceDetaille.View view) {
        this.interactor = new AgenceDetailledInteractor(this);
        attachView(view);
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
    public void onInitialize(int codeAgence) {

        interactor.OnInitializing(codeAgence);
    }

    @Override
    public void toInitialize(AgenceViewModel agence) {

        view.itializing(agence);
    }
}
