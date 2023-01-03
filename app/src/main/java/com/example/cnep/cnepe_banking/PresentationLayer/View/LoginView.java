package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.RequestLogin;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractLogin;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces.ILoginPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.LoginPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Interfaces.IloginView;

public class LoginView extends AppCompatActivity implements ContractLogin.View,View.OnClickListener {


    ContractLogin.ActionView presenter=new LoginPresenter(this);

    private EditText identifiantClient;
    private EditText motDePasse;

    private  TextView tIdentifiant;
    private  TextView tMotDePasse;
    private  TextView tmemoriser;
    private ProgressBar progressBar;
    private CheckBox memoriserIdentifiant;
    private Button bConnexion;
    private Button bSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user_physique);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        setTitle("Identification");


        this.identifiantClient=(EditText)findViewById(R.id.identifiantClient);
        this.motDePasse=(EditText)findViewById(R.id.motDePasse);
        tmemoriser=(TextView)findViewById(R.id.textView);
        tIdentifiant=(TextView)findViewById(R.id.textView5);
        tMotDePasse=(TextView)findViewById(R.id.textView4);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_login);
        this.memoriserIdentifiant=(CheckBox) findViewById(R.id.memoriserIdentifiant);
        bConnexion=(Button)findViewById(R.id.connexion);
        bConnexion.setOnClickListener(this);
        bSkip=(Button)findViewById(R.id.bSkip);
        bSkip.setOnClickListener(this);

    }



    public void waitingResponse()
    {
        identifiantClient.setVisibility(View.GONE);
        motDePasse.setVisibility(View.GONE);
        tIdentifiant.setVisibility(View.GONE);
        tMotDePasse.setVisibility(View.GONE);
        tmemoriser.setVisibility(View.GONE);
        memoriserIdentifiant.setVisibility(View.GONE);
        bConnexion.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        bSkip.setVisibility(View.GONE);
    }

    public void reponseReceived()
    {
        identifiantClient.setVisibility(View.VISIBLE);
        motDePasse.setVisibility(View.VISIBLE);
        tIdentifiant.setVisibility(View.VISIBLE);
        tMotDePasse.setVisibility(View.VISIBLE);
        tmemoriser.setVisibility(View.VISIBLE);
        memoriserIdentifiant.setVisibility(View.VISIBLE);
        bConnexion.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        bSkip.setVisibility(View.VISIBLE);

    }


    @Override
    public void loginSucced() {
        Toast.makeText(getApplicationContext(), "login succeed", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginView.this,Accueil.class);
        intent.putExtra("identifier",true);
        startActivity(intent);
        finish();

    }

    @Override
    public void loginFailed() {
        reponseReceived();
        Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noConnection() {
        reponseReceived();
        Toast.makeText(getApplicationContext(), "pas de connexion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.connexion:{
            RequestLogin requestLogin= new RequestLogin(identifiantClient.getText().toString(),motDePasse.getText().toString());
            waitingResponse();
            presenter.attempToLogin(requestLogin);
            break;
        }
            case R.id.bSkip:{
                Intent intent=new Intent(LoginView.this,Accueil.class);
                intent.putExtra("identifier",false);
                startActivity(intent);
                break;
            }
        }
    }
}
