package com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-31.
 */

public interface IListComptePresenter {

    public void getComptes(int codeAgence);
    public void setComptes(ArrayList<CompteViewModel> comptes);
}
