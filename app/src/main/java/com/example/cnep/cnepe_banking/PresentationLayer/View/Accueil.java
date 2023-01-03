package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.Models.RequestChangementAdresse;
import com.google.gson.Gson;

public class Accueil extends AppCompatActivity implements View.OnClickListener {

    private  boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Button bCompte=(Button)findViewById(R.id.bComptes);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_accueille);
        setSupportActionBar(toolbar);
        setTitle("Accueil");
        connected=getIntent().getBooleanExtra("identifier",false);
        bCompte.setOnClickListener(this);
        Button bCredits=(Button)findViewById(R.id.bCredits);
        bCredits.setOnClickListener(this);
        Button bProfile=(Button)findViewById(R.id.bProfile);
        bProfile.setOnClickListener(this);
        Button bMaps=(Button)findViewById(R.id.bMaps);
        bMaps.setOnClickListener(this);



        Button bAgences=(Button)findViewById(R.id.bAgences);
        bAgences.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        if(connected) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Êtes-vous sur de vouloir quitter l'application ?");
            builder.setCancelable(false);
            builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });

            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog, final int id) {

                    dialog.cancel();

                }
            });
            builder.show();
        }else
            super.onBackPressed();
    }

    public void redirectToLogin()
    {
       Intent it=new Intent(Accueil.this,LoginView.class);
        startActivity(it);
        finishAffinity();

    }

    @Override
    public void onClick(View v) {
        Intent it=null;

        switch (v.getId())
        {
            case R.id.bComptes:{

                if(!connected)
                    redirectToLogin();
                else
                {
                it=new Intent(Accueil.this,ListAgenceView.class);
                startActivity(it);
                }
            break;
        }
        case R.id.bCredits:{

            if(!connected)
                redirectToLogin();
            else {
                it = new Intent(Accueil.this, ListCreditView.class);
                startActivity(it);
            }

            break;
        }
        case R.id.bAgences:{
            it=new Intent(Accueil.this,ListAllAgencesView.class);
            startActivity(it);
            break;
        }
        case R.id.bProfile:{
            if(!connected)
                redirectToLogin();
            else {
                it = new Intent(Accueil.this, ProfileView.class);
                startActivity(it);
            }
            break;
        }
        case R.id.bMaps:{
            it=new Intent(Accueil.this,MapsActivity.class);
            startActivity(it);
            break;
        }

        }





    }
}
