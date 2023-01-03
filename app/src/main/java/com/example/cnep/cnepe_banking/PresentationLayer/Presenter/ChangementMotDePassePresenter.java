package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import android.view.View;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ChangementMotDePasseInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IChangementMotDePasseInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IChangementinformationInteractor;
import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratChangementinformation;

/**
 * Created by Aghiles on 2017-05-09.
 */

public class ChangementMotDePassePresenter extends BasePresenter<ContratChangementinformation.View>implements ContratChangementinformation.ActionView<RequestChangementMotDePasse>,IChangementMotDePasseInteractor.CallBack{

    IChangementMotDePasseInteractor interactor;

    public ChangementMotDePassePresenter(ContratChangementinformation.View view) {
        this.interactor = new ChangementMotDePasseInteractor(this);
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
    public void toLogOut() {
        interactor.loginOut();
    }

    @Override
    public void AcitonRequest(RequestChangementMotDePasse requete) {

        if (!requete.informationIsValide())
        {
            view.displayRequestFailed("nouveau mot de passe invalide");
            return;
        }
        if (!requete.motDePasseIsValide())
        {
            view.displayRequestFailed("ancien mot de passe invalide");
            return;
        }
        if (!requete.estConfirmee())
        {
            view.displayRequestFailed("le nouveau mot de passe et la confirmation sont différent");
            return;
        }
        interactor.executeRequest(requete);
    }

    @Override
    public void logedOut() {
        view.logOut();
    }

    @Override
    public void requestExecuted() {
        view.displayRequestExecuted();
    }

    @Override
    public void requestFailed() {
        view.displayRequestFailed("mot de passe incorrecte");
    }
}
