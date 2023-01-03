package com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces;

import android.content.Intent;

import com.example.cnep.cnepe_banking.Models.User;

/**
 * Created by Aghiles on 2017-03-25.
 */

public interface ILoginPresenter {
    public void attempToLogin(String identifiantClient,String motDepasse);
    public void attempToLogin(String identifiantClient,String email,String motDepasse);
    public Intent getModels(Intent intent);
    public void loginFailed();
    public void loginSucced();
    public void loginSucceed(User user);

}
