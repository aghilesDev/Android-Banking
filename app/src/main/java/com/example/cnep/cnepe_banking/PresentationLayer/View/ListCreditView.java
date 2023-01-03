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

import com.example.cnep.cnepe_banking.Models.CreditView;
import com.example.cnep.cnepe_banking.PresentationLayer.Contrat.ContractCredits;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.Interfaces.IListCreditPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.Presenter.ListCreditPresenter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.CreditAdapter;
import com.example.cnep.cnepe_banking.PresentationLayer.View.Interfaces.IListCreditView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-06.
 */

public class ListCreditView extends AppCompatActivity implements IListCreditView, ContractCredits.View {
    private ContractCredits.ActionView presenter;
    private RecyclerView rv;
    private CreditAdapter adapter;
    private Button noConnection;
    private ProgressBar progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_credits);
        setSupportActionBar(toolbar);
        setTitle("Mes credits");

        rv=(RecyclerView)findViewById(R.id.listCredits);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CreditAdapter();
        rv.setAdapter(adapter);
        progress = (ProgressBar) findViewById(R.id.progress_credits);
        noConnection=(Button)findViewById(R.id.NoConnectionCredits);
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

        presenter=new ListCreditPresenter(this);
        this.initialize();

    }




    private  void initialize()
    {

        rv.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        presenter.onIntialListRequest();;


    }
    @Override
    public void setAdapter(CreditAdapter adapter) {
        rv.setAdapter(adapter);
    }

    @Override
    public void onInitialCreditShow(ArrayList<CreditView> credits) {
        adapter.AddArticles(credits);
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
