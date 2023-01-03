package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IProfilInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ProfileIntractor;
import com.example.cnep.cnepe_banking.Models.User;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratProfil;

/**
 * Created by Aghiles on 2017-05-01.
 */

public class ProfilePresenter extends BasePresenter<ContratProfil.View> implements ContratProfil.ActionView,IProfilInteractor.CallBack{

    private IProfilInteractor interactor;

    public ProfilePresenter(ContratProfil.View view) {
        this.interactor = new ProfileIntractor(this);
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
    public void onInitialize() {
        interactor.onInitializing();

    }

    @Override
    public void toInitialize(User user) {
        view.initializing(user);

    }

    @Override
    public void logedOut() {
        view.logOut();

    }
}
