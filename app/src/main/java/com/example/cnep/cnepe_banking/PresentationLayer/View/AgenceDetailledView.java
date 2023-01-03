package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratAgenceDetaille;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.AgenceDetailledPresenter;


/**
 * Created by Aghiles on 2017-04-26.
 */

public class AgenceDetailledView extends AppCompatActivity implements View.OnClickListener,ContratAgenceDetaille.View {

    private AgenceViewModel agence;
    private int codeAgence=0;
    private ContratAgenceDetaille.ActionView presenter;
    private TextView numAgence;
    private TextView intituleAgence;
    private TextView willayaAgence;
    private TextView adresseAgence;
    private TextView telephoneAgence;
    private TextView emailAgence;
    private Button bCall;
    private Button bMessage;
    private Button bLocation;
    private Button bNoConnection;
    private View cadre;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agence_detaillled);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_agencedetaille);
        setSupportActionBar(toolbar);
        setTitle("Detaille Agence");



        numAgence = (TextView) findViewById(R.id.numAgence);
        intituleAgence = (TextView) findViewById(R.id.intituléAgence);
        willayaAgence = (TextView) findViewById(R.id.willayaAgence);
        adresseAgence = (TextView) findViewById(R.id.adresseAgence);
        telephoneAgence = (TextView) findViewById(R.id.telephoneAgence);
        emailAgence = (TextView) findViewById(R.id.emailAgence);
        bCall = (Button) findViewById(R.id.bCallAgence);
        bMessage = (Button) findViewById(R.id.bMessageAgence);
        bLocation = (Button) findViewById(R.id.bLocationAgence);
        bNoConnection= (Button) findViewById(R.id.NoConnectionAgence);
        cadre=(View)findViewById(R.id.constraintLayout);
        progressBar=(ProgressBar)findViewById(R.id.progress_agence);

        bCall.setOnClickListener(this);
        bLocation.setOnClickListener(this);
        bMessage.setOnClickListener(this);
        bNoConnection.setOnClickListener(this);
        presenter=new AgenceDetailledPresenter(this);

        agence = (AgenceViewModel) getIntent().getSerializableExtra("agence");

        if(agence!=null) {
            codeAgence = agence.getagenceid();
            diplayData();
        }
        else {
            codeAgence=getIntent().getIntExtra("codeAgence",0);

            this.initialize(codeAgence);
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCallAgence: {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + agence.getTelephone()));
                startActivity(callIntent);
                break;
            }
            case R.id.bMessageAgence: {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse("mailto:"+agence.getEmail());
                intent.setData(uri);
                startActivity(intent);

                break;
            }
            case R.id.bLocationAgence:{

// Retrieve latitude and longitude
                String latitude = "43.565715431592736";
                String longitude = "1.398482322692871";
// Format the associated uri
                Uri uriGeo = Uri.parse("geo:" + latitude + "," + longitude);
// Declare the associated Intent
                Intent intentGeo = new Intent(Intent.ACTION_VIEW, uriGeo);
                startActivity(intentGeo);
                break;
            }
            case R.id.NoConnectionAgence: {
                initialize(codeAgence);
                break;
            }
        }
    }


    private void initialize(int codeAgence)
    {
        watingData();
        presenter.onInitialize(codeAgence);
    }

    @Override
    public void noConnection() {
        cadre.setVisibility(View.GONE);
        bCall.setVisibility(View.GONE);
        bLocation.setVisibility(View.GONE);
        bMessage.setVisibility(View.GONE);
        bNoConnection.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void itializing(AgenceViewModel agence) {
        this.agence=agence;
        diplayData();

    }


    private void watingData() {
        cadre.setVisibility(View.GONE);
        bCall.setVisibility(View.GONE);
        bLocation.setVisibility(View.GONE);
        bMessage.setVisibility(View.GONE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);


    }
    private void diplayData(){
        cadre.setVisibility(View.VISIBLE);
        bCall.setVisibility(View.VISIBLE);
       // bLocation.setVisibility(View.VISIBLE);
        bMessage.setVisibility(View.VISIBLE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        numAgence.setText(agence.getagenceid() + "");
        intituleAgence.setText(agence.getLibelle());
        willayaAgence.setText(agence.getWilaya());
        adresseAgence.setText(agence.getAdresse());
        telephoneAgence.setText(agence.getTelephone());
        emailAgence.setText(agence.getEmail());


    }
}
