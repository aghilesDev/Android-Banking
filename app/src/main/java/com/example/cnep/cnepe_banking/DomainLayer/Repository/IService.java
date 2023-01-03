package com.example.cnep.cnepe_banking.DomainLayer.Repository;

import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.ErrorException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.MotDePasseInvalideException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NoConnectionException;
import com.example.cnep.cnepe_banking.DomainLayer.Exceptions.NotAuthorizedException;
import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.CreditView;
import com.example.cnep.cnepe_banking.Models.MouvementViewModel;
import com.example.cnep.cnepe_banking.Models.RequestChangementEmail;
import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;
import com.example.cnep.cnepe_banking.Models.RequestChangementTelephone;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCarte;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCheque;
import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.example.cnep.cnepe_banking.Models.ResponseAllAgences;
import com.example.cnep.cnepe_banking.Models.ResponseLogin;
import com.example.cnep.cnepe_banking.Models.User;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-05-14.
 */

public interface IService {




    public ArrayList<CompteViewModel> getUserComptes(int codeAgence) throws NoConnectionException,NotAuthorizedException,ErrorException;
    public CompteViewModel getCompte(String rib) throws NoConnectionException,NotAuthorizedException,ErrorException;
    public ArrayList<MouvementViewModel> getMouvements(int idCompte)throws NoConnectionException,NotAuthorizedException,ErrorException;
    public ArrayList<String> getWilayas() throws NoConnectionException,ErrorException;
    public ResponseAllAgences getAllAgences(int page) throws NoConnectionException,ErrorException;//type de retour a modifier
    public ResponseAllAgences getAllAgences(String wilaya,int page) throws NoConnectionException,ErrorException;
    public ArrayList<AgenceViewModel> getUserAgences() throws NoConnectionException,NotAuthorizedException,ErrorException;
    public AgenceViewModel getAgence(int codeAgence) throws NoConnectionException,ErrorException;
    public ArrayList<CreditView> getCredits() throws NoConnectionException,NotAuthorizedException,ErrorException;
    public User getProfile() throws NoConnectionException,NotAuthorizedException,ErrorException;
    public int postRequestCheque(RequestCommandeCheque requete) throws NoConnectionException,NotAuthorizedException,ErrorException,MotDePasseInvalideException;
    public int postRequestCarte(RequestCommandeCarte requete)throws NoConnectionException,NotAuthorizedException,ErrorException,MotDePasseInvalideException;

    public int postRequestChangeEmail(RequestChangementEmail request) throws NoConnectionException,NotAuthorizedException,ErrorException,MotDePasseInvalideException;
    public int postRequestChangeTelephone(RequestChangementTelephone request) throws NoConnectionException,NotAuthorizedException,ErrorException,MotDePasseInvalideException;
    public int postRequestChangeMotDePasse(RequestChangementMotDePasse request) throws NoConnectionException,NotAuthorizedException,ErrorException,MotDePasseInvalideException;

    public ResponseLogin postRequestLogIn(RequestLogin requestLogin) throws NoConnectionException,MotDePasseInvalideException,ErrorException;
    public int postRequestLogOut() throws NoConnectionException,NotAuthorizedException,ErrorException;
}
