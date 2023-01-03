package com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces;

import com.example.cnep.cnepe_banking.Models.CreditView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-06.
 */

public interface IListCreditPresenter {
    public  void getCredits();
    public void getMoreCredits();
    public void sendCredits(ArrayList<CreditView> credits);
    public void sendMoreCredits(ArrayList<CreditView> credits);
}
