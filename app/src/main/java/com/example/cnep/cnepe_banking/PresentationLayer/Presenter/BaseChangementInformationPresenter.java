package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

import com.example.cnep.cnepe_banking.DomainLayer.Interactor.ChangementInformationInteractor;
import com.example.cnep.cnepe_banking.DomainLayer.Interactor.Interfaces.IChangementinformationInteractor;
import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratChangementinformation;

/**
 * Created by Aghiles on 2017-05-02.
 */

public abstract class BaseChangementInformationPresenter extends BasePresenter<ContratChangementinformation.View> implements ContratChangementinformation.ActionView<RequestChangementInformation>,IChangementinformationInteractor.CallBack{

    IChangementinformationInteractor interactor;


    public BaseChangementInformationPresenter(IChangementinformationInteractor interactor,ContratChangementinformation.View view) {
        this.interactor = interactor;
        attachView(view);
    }

    public BaseChangementInformationPresenter(ContratChangementinformation.View view) {
        this.interactor=new ChangementInformationInteractor(this);
        attachView(view);
    }

    @Override
    public void NoConnectionFound() {
        view.noConnection();

    }

    @Override
    public void toLogOut() {
        interactor.loginOut();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void AcitonRequest(RequestChangementInformation requete) {

        if (!requete.informationIsValide())
        {
            String message="";
            switch (requete.getTypeRequete())
            {
                case RequestChangementInformation._ADRESSE:{
                    message="Adresse invalide";
                    break;
                }
                case RequestChangementInformation._EMAIL:{
                    message="email invalide";
                    break;
                }
                case RequestChangementInformation._TELEPHONE:{
                    message="telephone invalide";
                    break;
                }
                default: {
                    break;
                }
            }

            view.displayRequestFailed(message);
            return;

        }


        if (!requete.motDePasseIsValide())
        {
            view.displayRequestFailed("Mot de passe invalide");
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
        if (isAttached())
            view.displayRequestExecuted();

    }

    @Override
    public void requestFailed() {
        if (isAttached())
            view.displayRequestFailed("Mot de passe érroné");
    }
}
