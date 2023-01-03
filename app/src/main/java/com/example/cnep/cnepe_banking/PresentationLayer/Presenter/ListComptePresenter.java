package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import android.app.Activity;
import android.content.Context;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IListCompteInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ListCompteInteractor;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.ConnectionChecker;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractComptes;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-31.
 */

public class ListComptePresenter extends BasePresenter<ContractComptes.View> implements ContractComptes.ActionView,IListCompteInteractor.CallBack {

    private IListCompteInteractor interactor;
    private Context context;
    private ConnectionChecker connectionChecker;

    public ListComptePresenter(ContractComptes.View view) {
        this.attachView(view);
        this.interactor=new ListCompteInteractor(this);
        connectionChecker=new ConnectionChecker((Activity)view);
    }



    @Override
    public void LoadComptesReponse(ArrayList<CompteViewModel> comptes) {
        view.onInitialCompteShow(comptes);

    }

    @Override
    public void onIntialListRequest(int codeAgence) {
        interactor.LoadCompteRequest(codeAgence);

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
