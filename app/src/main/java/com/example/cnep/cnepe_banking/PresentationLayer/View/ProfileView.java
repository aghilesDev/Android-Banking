package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.RequestChangementInformation;
import com.example.cnep.cnepe_banking.Models.User;
import com.example.cnep.cnepe_banking.Models.UserMoral;
import com.example.cnep.cnepe_banking.Models.UserParticulier;
import com.example.cnep.cnepe_banking.Models.UserProfessionnel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratProfil;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ProfilePresenter;


/**
 * Created by Aghiles on 2017-05-01.
 */

public class ProfileView extends AppCompatActivity implements ContratProfil.View,View.OnClickListener {


    private ContratProfil.ActionView presenter;
    private User user;
    private View cadre;
    private ProgressBar progressBar;
    private Button bNoConnection;
    private  Button bTelephone;
    private  Button bEmail;
    private  Button bAdresse;
    private  Button bMotDePasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        setTitle("Profile");
        cadre=findViewById(R.id.cadre_profile);
        bNoConnection=(Button)findViewById(R.id.NoConnectionProfile);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_profile);
        bMotDePasse=(Button)findViewById(R.id.bMotDePasseUser);
        bTelephone=(Button)findViewById(R.id.bTelephoneUser);
        bEmail=(Button)findViewById(R.id.bEmailUser);
        bAdresse=(Button)findViewById(R.id.bAdresseUser);
        presenter= new ProfilePresenter(this);


        bTelephone.setOnClickListener(this);
        bEmail.setOnClickListener(this);
        bMotDePasse.setOnClickListener(this);
        initialize();


    }



    private void watingData()
    {
        cadre.setVisibility(View.GONE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        bMotDePasse.setVisibility(View.GONE);
       // bAdresse.setVisibility(View.GONE);
        bEmail.setVisibility(View.GONE);
        bTelephone.setVisibility(View.GONE);

    }


    private void initialize()
    {
        watingData();
        presenter.onInitialize();

    }

    private  void displayData(User user)
    {
        cadre.setVisibility(View.VISIBLE);
        bNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        bMotDePasse.setVisibility(View.VISIBLE);
        //bAdresse.setVisibility(View.VISIBLE);
        bEmail.setVisibility(View.VISIBLE);
        bTelephone.setVisibility(View.VISIBLE);
        displayUser(user);



    }


    private void displayUser(User user)
    {
        TextView nom=(TextView)findViewById(R.id.nomUser);
        TextView prenom=(TextView)findViewById(R.id.prenomUser);
        TextView adresse=(TextView)findViewById(R.id.adresseUser);
        TextView email=(TextView)findViewById(R.id.emailUser);
        TextView telephone=(TextView)findViewById(R.id.telephoneUser);
        TextView nif=(TextView)findViewById(R.id.nifUer);
        TextView nis=(TextView)findViewById(R.id.nisUser);
        TextView tNom=(TextView)findViewById(R.id.tNomUser);
        TextView tPrenom=(TextView)findViewById(R.id.tPrenomUser);
        TextView tNif=(TextView)findViewById(R.id.tNif);
        TextView tNis=(TextView)findViewById(R.id.tNis);

        email.setText(user.getEmail());
        adresse.setText(user.getAdresse());
        telephone.setText(user.getTelephone());


        switch (user.getType())
        {
            case User.PARTICULIER:{
                UserParticulier userParticulier=(UserParticulier)user;
                tNif.setVisibility(View.GONE);
                tNis.setVisibility(View.GONE);
                nif.setVisibility(View.GONE);
                nis.setVisibility(View.GONE);
                tNom.setText("Nom:");
                tPrenom.setVisibility(View.VISIBLE);
                prenom.setVisibility(View.VISIBLE);


                nom.setText(userParticulier.getNom());
                prenom.setText(userParticulier.getPrenom());

            break;
            }
            case User.PROFESSIONNEL:{
                UserProfessionnel userProfessionnel=(UserProfessionnel)user;
                tNif.setVisibility(View.VISIBLE);
                tNis.setVisibility(View.VISIBLE);
                nif.setVisibility(View.VISIBLE);
                nis.setVisibility(View.VISIBLE);
                tNom.setText("Nom:");
                tPrenom.setVisibility(View.VISIBLE);
                prenom.setVisibility(View.VISIBLE);

                nom.setText(userProfessionnel.getNom());
                prenom.setText(userProfessionnel.getPrenom());
                nif.setText(userProfessionnel.getNif());
                nis.setText(userProfessionnel.getNis());

                break;
            }
            case User.MORAL:{
                UserMoral userMoral=(UserMoral)user;
                tNif.setVisibility(View.VISIBLE);
                tNis.setVisibility(View.VISIBLE);
                nif.setVisibility(View.VISIBLE);
                nis.setVisibility(View.VISIBLE);
                tNom.setText("Raison Social:");
                tPrenom.setVisibility(View.GONE);
                prenom.setVisibility(View.GONE);

                nom.setText(userMoral.getRaison_Social());
                nif.setText(userMoral.getNif());
                nis.setText(userMoral.getNis());




                break;
            }
        }

    }




    @Override
    public void noConnection() {

        cadre.setVisibility(View.GONE);
        bNoConnection.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        bMotDePasse.setVisibility(View.GONE);
        // bAdresse.setVisibility(View.GONE);
        bEmail.setVisibility(View.GONE);
        bTelephone.setVisibility(View.GONE);

    }

    @Override
    public void logOut() {
        Intent intent=new Intent(this,LoginView.class);
        Toast.makeText(this,"votre session à expirée",Toast.LENGTH_SHORT).show();
        startActivity(intent);

        finishAffinity();
    }

    @Override
    public void initializing(User user) {
        this.user=user;
        displayData(user);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bAdresseUser:
            {
                break;
            }
            case R.id.bEmailUser:
            {

                Intent intent= new Intent(this,ChangementInformationView.class);
                intent.putExtra("tyeInformation", RequestChangementInformation._EMAIL);

                startActivityForResult(intent,RequestChangementInformation._EMAIL);
                break;
            }
            case R.id.bMotDePasseUser:
            {
                Intent intent= new Intent(this,ChangementMotDePasseView.class);
                startActivity(intent);
                break;
            }
            case R.id.bTelephoneUser:
            {
                Intent intent= new Intent(this,ChangementInformationView.class);
                intent.putExtra("tyeInformation", RequestChangementInformation._TELEPHONE);
                startActivityForResult(intent,RequestChangementInformation._TELEPHONE);
                break;
            }
            default:
            {

                break;
            }


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            String nouvelleInfo=data.getStringExtra("information");
            switch (requestCode) {
                case RequestChangementInformation._EMAIL: {


                    break;
                }
                case RequestChangementInformation._TELEPHONE: {
                    TextView telephone=(TextView)findViewById(R.id.telephoneUser);
                    telephone.setText(nouvelleInfo);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
}
