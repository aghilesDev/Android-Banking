package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
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

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContratAllAgences;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ListAllAgencesPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.AllAgenceAdapter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.IArticleSender;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-23.
 */

public class ListAllAgencesView extends AppCompatActivity implements ContratAllAgences.View,IArticleSender,View.OnClickListener,AdapterView.OnItemSelectedListener {

    private Spinner dropdown;
    private String currentWillaya="Tous";
    private RecyclerView rv;
    private AllAgenceAdapter adapter;
    private  ContratAllAgences.ActionView presenter;
    private View search;
    private Button noConnection;
    private ProgressBar progress;

    private boolean viewInitialized=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.agences);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        setTitle("Agences");
        rv=(RecyclerView)findViewById(R.id.List);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AllAgenceAdapter(this,this);
        rv.setAdapter(adapter);


        progress = (ProgressBar) findViewById(R.id.progress_agences);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {

                displayData();

            }
        });

        search=findViewById(R.id.SeachBarAgences);
        search.setVisibility(View.GONE);
        noConnection=(Button)findViewById(R.id.NoConnectionAgences);

        noConnection.setOnClickListener(this);

        dropdown = (Spinner)findViewById(R.id.spinner);
        dropdown.setOnItemSelectedListener(this);


        presenter=new ListAllAgencesPresenter(this);
        this.initialize();
    }


    private  void watingData()
    {
        search.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }
    private void displayData()
    {
        rv.setVisibility(View.VISIBLE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        search.setVisibility(View.VISIBLE);

    }




    private  void initialize()
    {
        this.watingData();
        presenter.onInitialize();


    }

    @Override
    public void LoadNext() {
        presenter.LoadMoreAgences(currentWillaya);

    }

    @Override
    public void noConnection() {
        search.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);

    }

    @Override
    public void Initializing(ArrayList<AgenceViewModel> agences, ArrayList<String> wilayas, boolean hasMore) {
        adapter.onArticlesReceived(agences,hasMore);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_customed, wilayas);
        dropdown.setAdapter(adapter);
        viewInitialized=true;

    }

    @Override
    public void showMoreAgences(ArrayList<AgenceViewModel> agences, boolean hasMore) {


        adapter.onArticlesReceived(agences,hasMore);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==noConnection.getId())
        {
            if(!viewInitialized)
                this.initialize();
            else {
                this.watingData();
                presenter.LoadMoreAgences(currentWillaya);
            }



        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String willaya=(String)dropdown.getSelectedItem();
        if(willaya.equalsIgnoreCase(this.currentWillaya))
            return;
        currentWillaya=willaya;
        this.watingData();
        adapter.clear();
        presenter.LoadAgences(currentWillaya);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
