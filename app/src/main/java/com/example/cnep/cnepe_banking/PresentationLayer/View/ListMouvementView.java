package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.MouvementViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractMouvements;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ListMouvementPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.MouvementAdapter;

import java.util.ArrayList;


/**
 * Created by Aghiles on 2017-04-19.
 */

public class ListMouvementView extends AppCompatActivity implements ContractMouvements.View {
    private ContractMouvements.ActionView presenter;
    private MouvementAdapter adapter;
    private RecyclerView rv;
    private Button noConnection;
    private ProgressBar progress;
    private int compteId;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouvements);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_mouvements);
        setSupportActionBar(toolbar);
        setTitle("Mes Mouvements");
        rv=(RecyclerView)findViewById(R.id.ListMouvement);
        rv.setLayoutManager(new LinearLayoutManager(this));

        compteId= getIntent().getIntExtra("compteId",0);
        Toast.makeText(getApplicationContext(),"numero de compte: "+compteId,Toast.LENGTH_SHORT).show();
        this.adapter=new MouvementAdapter(ListMouvementView.this);
        rv.setAdapter(adapter);

        progress = (ProgressBar) findViewById(R.id.progress2);
        noConnection=(Button)findViewById(R.id.NoConnectionMouvements);
        this.noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                rv.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);

            }
        });

        presenter=new ListMouvementPresenter(this);
        this.initialize();
    }


    private  void initialize()
    {

        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        presenter.onIntialRequest(compteId);


    }

    @Override
    public void onIntialReponse(ArrayList<MouvementViewModel> mouvements) {
        adapter.onArticlesReceived(mouvements,false);



    }

    @Override
    public void noConnection() {
        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);

    }

    @Override
    public void logOut() {
        Intent intent=new Intent(this,LoginView.class);
        Toast.makeText(this,"votre session à expirée",Toast.LENGTH_SHORT).show();
        startActivity(intent);

        finishAffinity();
    }
}
