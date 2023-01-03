package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.CompteViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractComptes;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ListComptePresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.CompteAdapter;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-31.
 */

public class ListCompteView extends AppCompatActivity implements ContractComptes.View{

    private ContractComptes.ActionView presenter;
    private RecyclerView rv;
    private CompteAdapter adapter;
    private Button noConnection;
    private ProgressBar progress;

    private int codeAgence;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comptes);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_comptes);
        setSupportActionBar(toolbar);
        codeAgence=getIntent().getIntExtra("codeAgence",0);
        setTitle("comptes de la "+codeAgence);
        rv=(RecyclerView)findViewById(R.id.ListComptes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CompteAdapter(ListCompteView.this);
        rv.setAdapter(adapter);
        progress = (ProgressBar) findViewById(R.id.progress_comptes);
        noConnection=(Button)findViewById(R.id.NoConnectionComptes);
        noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
            }
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);

            }
        });
        presenter=new ListComptePresenter(this);
        initialize();



       Toast.makeText(getApplicationContext(), "code Agence: "+codeAgence, Toast.LENGTH_SHORT).show();
    }



    private  void initialize()
    {

        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        presenter.onIntialListRequest(codeAgence);;


    }


    @Override
    public void onInitialCompteShow(ArrayList<CompteViewModel> comptes) {

        adapter.onArticlesReceived(comptes,false);

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
