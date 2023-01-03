package com.example.cnep.cnepe_banking.PresentationLayer.Contrat;

import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.squareup.okhttp.Request;

/**
 * Created by Aghiles on 2017-04-19.
 */

public interface ContractLogin {

    public interface ActionView extends ContratConnected.ActionView
    {

        public void attempToLogin(RequestLogin requestLogin);
    }

    public interface View extends ContratConnected.View
    {
        public void loginSucced();
        public void loginFailed();
    }
}
