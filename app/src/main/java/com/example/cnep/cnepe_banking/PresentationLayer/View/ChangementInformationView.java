package com.example.cnep.cnepe_banking.PresentationLayer.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cnep.R;
import com.example.cnep.cnepe_banking.Models.RequestChangementEmail;
import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;
import com.example.cnep.cnepe_banking.Models.RequestChangementTelephone;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratChangementinformation;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ChangementInformationPresenter;

/**
 * Created by Aghiles on 2017-05-02.
 */

public class ChangementInformationView extends AppCompatActivity implements ContratChangementinformation.View,View.OnClickListener {


    ContratChangementinformation.ActionView presenter;


    private TextView tInforamtion;
    private EditText inforamtion;
    private EditText motDePasse;
    private TextView tMotDePasse;
    private Button bValider;
    ProgressBar progressBar;

    Integer typeInformation=null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changement_information);

        typeInformation=0;
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_changementInformation);
        setSupportActionBar(toolbar);
        setTitle("Changement "+getTypeInformation());

        tInforamtion=(TextView)findViewById(R.id.informationAChanger_changementInformation);
        tMotDePasse=(TextView)findViewById(R.id.tMotDePasseChangementInformation);
        motDePasse=(EditText) findViewById(R.id.motDePasseChangementInformation);
        inforamtion=(EditText) findViewById(R.id.information_changementInformation);
        bValider=(Button) findViewById(R.id.bValider) ;
        progressBar=(ProgressBar) findViewById(R.id.progressBar_ChangementInformation);


        bValider.setOnClickListener(this);
        tInforamtion.setText(getTypeInformation()+":");


        presenter=new ChangementInformationPresenter(this);
        initialize();

    }

    protected String  getTypeInformation()
    {
        if(typeInformation==0)
            typeInformation=getIntent().getIntExtra("tyeInformation",0);

        switch (typeInformation)
        {
            case RequestChangementInformation._TELEPHONE :
            {
                return "Telephone";

            }
            case RequestChangementInformation._EMAIL:
            {
                return "Email";

            }
            default:
            {
                return "Inconnu";

            }

        }

    }




    public  void waitingReponse()
    {
        tInforamtion.setVisibility(View.GONE);
        tMotDePasse.setVisibility(View.GONE);
        motDePasse.setVisibility(View.GONE);
        inforamtion.setVisibility(View.GONE);
        bValider.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

    }

    public void initialize()
    {
        tInforamtion.setVisibility(View.VISIBLE);
        tMotDePasse.setVisibility(View.VISIBLE);
        motDePasse.setVisibility(View.VISIBLE);
        inforamtion.setVisibility(View.VISIBLE);
        bValider.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void noConnection() {
        tInforamtion.setVisibility(View.VISIBLE);
        tMotDePasse.setVisibility(View.VISIBLE);
        motDePasse.setVisibility(View.VISIBLE);
        inforamtion.setVisibility(View.VISIBLE);
        bValider.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,"Pas de connexion",Toast.LENGTH_SHORT).show();


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
        String message="changement effectué";
        if(typeInformation==RequestChangementInformation._EMAIL)
            message+=". Veuillez confirmer votre Email";

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Intent result=new Intent();
        result.putExtra("information",inforamtion.getText().toString());
        setResult(RESULT_OK,result);
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

        RequestChangementInformation requete=null;
        switch (typeInformation)
        {
            case RequestChangementInformation._TELEPHONE :
            {
                requete=new RequestChangementTelephone(info,mp);
                break;
            }
            case RequestChangementInformation._EMAIL:
            {
                requete=new RequestChangementEmail(info,mp);
                break;

            }
            default:
            {
                Toast.makeText(this,"Une Erreur c'est produite",Toast.LENGTH_SHORT);
                break;

            }

        }
        if(requete!=null) {
         waitingReponse();
            presenter.AcitonRequest(requete);
        }
    }
}
