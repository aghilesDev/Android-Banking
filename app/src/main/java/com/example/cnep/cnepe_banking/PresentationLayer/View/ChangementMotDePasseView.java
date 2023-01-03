package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cnep.cnepe_banking.Models.RequestChangementMotDePasse;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratChangementinformation;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ChangementMotDePassePresenter;

/**
 * Created by Aghiles on 2017-05-09.
 */

public class ChangementMotDePasseView extends AppCompatActivity implements ContratChangementinformation.View,View.OnClickListener  {

    ContratChangementinformation.ActionView presenter;


    private TextView tInforamtion;
    private EditText inforamtion;
    private EditText motDePasse;
    private TextView tMotDePasse;
    private EditText confirantionMotDePasse;
    private TextView tConfiramtionMotDePasse;
    private Button bValider;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changement_mot_de_passe);


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_changementMotDePasse);
        setSupportActionBar(toolbar);
        setTitle("Changement mot de passe");

        tInforamtion=(TextView)findViewById(R.id.informationAChanger_changementMotDePasse);
        tMotDePasse=(TextView)findViewById(R.id.tMotDePasseChangementMotDePasse);
        motDePasse=(EditText) findViewById(R.id.motDePasseChangementMotDePasse);
        inforamtion=(EditText) findViewById(R.id.information_changementMotDePasse);
        confirantionMotDePasse=(EditText) findViewById(R.id.confirmation);
        tConfiramtionMotDePasse=(TextView) findViewById(R.id.tconfirmation);
        bValider=(Button) findViewById(R.id.bValiderChangementMotDePasse) ;
        progressBar=(ProgressBar) findViewById(R.id.progressBar_ChangementMotDePasse);
        presenter= new ChangementMotDePassePresenter(this);
        bValider.setOnClickListener(this);
        initialize();
    }



    public  void waitingReponse()
    {
        tInforamtion.setVisibility(View.GONE);
        tMotDePasse.setVisibility(View.GONE);
        motDePasse.setVisibility(View.GONE);
        inforamtion.setVisibility(View.GONE);
        confirantionMotDePasse.setVisibility(View.GONE);
        tConfiramtionMotDePasse.setVisibility(View.GONE);
        bValider.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

    }

    public void initialize()
    {
        tInforamtion.setVisibility(View.VISIBLE);
        tMotDePasse.setVisibility(View.VISIBLE);
        motDePasse.setVisibility(View.VISIBLE);
        inforamtion.setVisibility(View.VISIBLE);
        confirantionMotDePasse.setVisibility(View.VISIBLE);
        tConfiramtionMotDePasse.setVisibility(View.VISIBLE);
        bValider.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void noConnection() {
        tInforamtion.setVisibility(View.VISIBLE);
        tMotDePasse.setVisibility(View.VISIBLE);
        motDePasse.setVisibility(View.VISIBLE);
        inforamtion.setVisibility(View.VISIBLE);
        confirantionMotDePasse.setVisibility(View.VISIBLE);
        tConfiramtionMotDePasse.setVisibility(View.VISIBLE);
        bValider.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,"Pas de connexion",Toast.LENGTH_SHORT);
    }

    @Override
    public void logOut() {

        Intent intent=new Intent(this,LoginView.class);
        Toast.makeText(this,"votre session à expirée",Toast.LENGTH_SHORT).show();
        startActivity(intent);

        finishAffinity();

    }

    @Override
    public void displayRequestExecuted() {

        // initialize();
        Toast.makeText(this,"modification enregistrée",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void displayRequestFailed(String message) {

        initialize();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        String mp=motDePasse.getText().toString();
        String info=inforamtion.getText().toString();
        String confiramtion=confirantionMotDePasse.getText().toString();
        RequestChangementMotDePasse requete=new RequestChangementMotDePasse(info,mp,confiramtion);
        waitingReponse();
        presenter.AcitonRequest(requete);

    }
}
