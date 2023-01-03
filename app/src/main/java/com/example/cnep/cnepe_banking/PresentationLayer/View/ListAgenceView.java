package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractAgences;

import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ListAgencePresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.AgenceAdapter;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-30.
 */

public class ListAgenceView extends AppCompatActivity implements ContractAgences.View,AdapterView.OnItemSelectedListener {


    private  Spinner dropdown;
    private String willaya;
    private RecyclerView rv;
    private AgenceAdapter adapter;
    private  ContractAgences.ActionView presenter;
    private View search;
    private Button noConnection;
    ProgressBar progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agences);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        setTitle("Mes Agences");
        rv=(RecyclerView)findViewById(R.id.List);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AgenceAdapter(ListAgenceView.this);
        rv.setAdapter(adapter);


        progress = (ProgressBar) findViewById(R.id.progress_agences);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {

                rv.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                search.setVisibility(View.VISIBLE);

            }
        });

        search=findViewById(R.id.SeachBarAgences);
        search.setVisibility(View.GONE);
        noConnection=(Button)findViewById(R.id.NoConnectionAgences);

        noConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
            }
        });

        dropdown = (Spinner)findViewById(R.id.spinner);
        dropdown.setOnItemSelectedListener(this);


        presenter=new ListAgencePresenter(this);
        this.initialize();

    }



    private  void initialize()
    {
        search.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        presenter.onIntialListRequest();


    }


    @Override
    public void onInitialAgenceShow(ArrayList<AgenceViewModel> agences, ArrayList<String> wilayas) {

        adapter.addArticles(agences);
        wilayas.add(0,"Tous");//on ajoute la possibilité d'afficher toutes les agences.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_customed, wilayas);
        dropdown.setAdapter(adapter);


    }

    @Override
    public void showsAgences(ArrayList<AgenceViewModel> agences) {
        adapter.setList(agences);

    }


    @Override
    public void noConnection() {
        search.setVisibility(View.GONE);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        willaya=(String)dropdown.getSelectedItem();
        if(willaya.equalsIgnoreCase("Tous"))//si tout est selectionner on affiche toutes les agences.

            presenter.allAgencesRequest();
        else
            presenter.AgencesRequest(willaya);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
