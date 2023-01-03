package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import android.content.Intent;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IloginInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.LoginInteractor;
import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.example.cnep.cnepe_banking.Models.User;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractLogin;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces.ILoginPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Interfaces.IloginView;

/**
 * Created by Aghiles on 2017-03-25.
 */

public class LoginPresenter extends BasePresenter<ContractLogin.View> implements ContractLogin.ActionView,LoginInteractor.CallBack {

    private IloginInteractor interactor;


    public LoginPresenter(ContractLogin.View view)
    {
        this.view=view;
        this.interactor=new LoginInteractor(this);
    }

    @Override
    public void attempToLogin(RequestLogin requestLogin) {

        if(requestLogin.isValide())
        interactor.loginCase(requestLogin);
        else
            view.loginFailed();
    }



    @Override
    public void loginAuthorized() {
        view.loginSucced();

    }

    @Override
    public void loginNotAuthorized() {
        view.loginFailed();

    }

    @Override
    public void NoConnectionFound() {
        view.noConnection();
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
