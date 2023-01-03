package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.Models.RequestCommande;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCarte;
import com.example.cnep.cnepe_banking.Models.RequestCommandeCheque;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratCompteDetailled;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.CompteDetailledPresenter;

/**
 * Created by Aghiles on 2017-04-30.
 */

public class CompteDetailledView extends AppCompatActivity implements ContratCompteDetailled.View,View.OnClickListener,DialogInterface.OnClickListener {

    ContratCompteDetailled.ActionView presenter;
    private CompteViewModel compte;
    private String numCompte;

    private TextView compteId;
    private TextView rib;
    private TextView type;
    private TextView solde;
    private TextView DateMaj;
    private View cadre;
    private Button bMouvement;
    private Button bCommande;
    private Button noConnection;
    private ProgressBar progressBar;

    private String motDePasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte_detailled);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_compteDetaille);
        setSupportActionBar(toolbar);
        setTitle("Detaille Compte");


        this.compteId=(TextView)findViewById(R.id.compte_Id);
        this.rib=(TextView)findViewById(R.id.rib_);
        this.type=(TextView)findViewById(R.id.Type_);
        this.solde=(TextView)findViewById(R.id.solde_);
        this.DateMaj=(TextView)findViewById(R.id.Date_Maj_Compte);
        this.bMouvement=(Button)findViewById(R.id.bMouvement);
        this.bCommande=(Button)findViewById(R.id.bCommande);
        cadre=(View)findViewById(R.id.constraintlayout);
        progressBar=(ProgressBar)findViewById(R.id.progress_Compte);
        noConnection=(Button)findViewById(R.id.NoConnectionCompte);



        noConnection.setOnClickListener(this);
        bMouvement.setOnClickListener(this);
        bCommande.setOnClickListener(this);


        presenter=new CompteDetailledPresenter(this);
        compte=(CompteViewModel) getIntent().getSerializableExtra("compte");
        if(compte!=null) {
            numCompte=compte.getCompteId();
            diplayData();
        }else {
            numCompte=getIntent().getStringExtra("compteId");
            initialize(numCompte);
        }
    }

    private void initialize(String numCompte)
    {
        watingData();
        presenter.onInitialize(numCompte);
    }

    private void watingData() {
       /* cadre.setVisibility(View.GONE);
        bCall.setVisibility(View.GONE);
        bLocation.setVisibility(View.GONE);
        bMessage.setVisibility(View.GONE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);*/
        cadre.setVisibility(View.GONE);
        bCommande.setVisibility(View.GONE);
        bMouvement.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);



    }
    private void diplayData(){
       /* cadre.setVisibility(View.VISIBLE);
        bCall.setVisibility(View.VISIBLE);
        bLocation.setVisibility(View.VISIBLE);
        bMessage.setVisibility(View.VISIBLE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        numAgence.setText(agence.getId() + "");
        intituleAgence.setText(agence.getLibelle());
        willayaAgence.setText(agence.getWilaya());
        adresseAgence.setText(agence.getAdresse());
        telephoneAgence.setText(agence.getTelephone());
        emailAgence.setText(agence.getEmail());*/
        cadre.setVisibility(View.VISIBLE);
        bCommande.setVisibility(View.VISIBLE);
        bMouvement.setVisibility(View.VISIBLE);
        noConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        this.compteId.setText(compte.getCompteId());
        this.rib.setText(compte.getRib());
        this.type.setText(compte.getType());
        this.solde.setText(compte.getSolde()+" DZD");
        this.DateMaj.setText(compte.getDateMaj());
        boolean commandable=false;
        if (compte.typeCompte.equalsIgnoreCase("éPARGNE")) {
            bCommande.setText("COMMANDE Carte");
            commandable=true;
        }else
            if(compte.typeCompte.equalsIgnoreCase("CHèQUE"))
            {
                bCommande.setText("COMMANDE CHEQUIER");
                commandable=true;
            }

        if(!commandable||compte.isCommande())
        {
            bCommande.setEnabled(false);
        }



    }



    @Override
    public void noConnection() {

        cadre.setVisibility(View.GONE);
        bCommande.setVisibility(View.GONE);
        bMouvement.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void itializing(CompteViewModel compte) {

    }

    @Override
    public void displayExecutedCommande() {
        compte.setCommande(true);
        diplayData();
        Toast.makeText(getApplicationContext(), "votre commande a été effectuée", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void diplayFailedCommande() {
        diplayData();
        Toast.makeText(getApplicationContext(), "mot de passe invalide", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void logOut() {
        Intent intent=new Intent(this,LoginView.class);
        Toast.makeText(this,"votre session à expirée",Toast.LENGTH_SHORT).show();
        startActivity(intent);

        finishAffinity();
    }

    @Override
    public void onClick(View v) {



        switch (v.getId())
        {
            case R.id.bCommande:
            {
                String commande="";
                int typeCompte=0;

                if (compte.typeCompte.equalsIgnoreCase("éPARGNE")) {
                    commande="carte épargne";
                    typeCompte= RequestCommande._CARTE;
                }else
                if(compte.typeCompte.equalsIgnoreCase("CHèQUE"))
                {
                    commande="chéquier";
                    typeCompte= RequestCommande._CHEQUE;
                }

                afficheDialogInterface(commande,typeCompte);

                break;
            }
            case R.id.NoConnectionCompte:
            {
                initialize(numCompte);
                break;
            }
            case R.id.bMouvement:
                Intent it=new Intent(this, ListMouvementView.class);

                it.putExtra("compteId",compte.getId());

                startActivity(it);
            {
                break;
            }
        }

    }



    private void afficheDialogInterface(final String commande,final int typeCompte)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(commande.toUpperCase());
        builder.setMessage("Vous n'avez pas de "+commande+" pour ce compte.\nVoulez vous en commandez?\nMot de passe:");
        final EditText eMotDePasse=new EditText(this);
        eMotDePasse.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder.setView(eMotDePasse);

// Set up the buttons
       builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               motDePasse=eMotDePasse.getText().toString();
               if(motDePasse.isEmpty()) {

                   dialog.cancel();
                   Toast.makeText(getApplicationContext(), "mot de passe invalide", Toast.LENGTH_SHORT).show();
                   return;
               }
               watingData();
               dialog.cancel();
               RequestCommande requete;
               if (typeCompte==RequestCommande._CARTE)
                   requete=new RequestCommandeCarte(compte.getId(),motDePasse);
                   else
                   requete=new RequestCommandeCheque(compte.getId(),motDePasse);
               presenter.actionCommande(requete);
               motDePasse="";

           }
       });
        builder.setNegativeButton("non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
