package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;
import com.example.cnep.R;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cnep.cnepe_banking.Models.CreditView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-06.
 */

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.MyViewHolder> {
    private ArrayList<CreditView> list= new ArrayList<>();
    private Context context;
    public CreditAdapter()
    {
        super();

    }


    public void AddArticles(ArrayList<CreditView> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.credit_cellule,parent,false);

        return new MyViewHolder(view,context);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CreditView compte=list.get(position);
        holder.display(compte);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView numeroDecompte;
        private TextView montantAccordée;
        private TextView montantRestant;
        private TextView datePrelevement;
        private TextView retardEcheance;

        private CreditView currentCredit;
        private Context context;
        public MyViewHolder(final View itemView, final Context context) {
            super(itemView);
            numeroDecompte=(TextView) itemView.findViewById(R.id.numeroDeCompte);
            montantAccordée=(TextView) itemView.findViewById(R.id.MontantAccordé);
            montantRestant=(TextView) itemView.findViewById(R.id.MontantResatant);
            datePrelevement=(TextView) itemView.findViewById(R.id.datePrélevement);
            retardEcheance=(TextView) itemView.findViewById(R.id.retardEchéance);

            this.context=context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*
                    Intent it=new Intent(context, ListCompteView.class);

                    it.putExtra("codeAgence",currentAgence.getId());

                    context.startActivity(it);
                    */
                }
            });
        }

        public void display(CreditView credit) {
            currentCredit=credit;
            this.numeroDecompte.setText(currentCredit.getCompteId());
            this.montantAccordée.setText(currentCredit.getMontantAcordé()+"");
            this.montantRestant.setText(currentCredit.getMontantRestant()+"");
            this.datePrelevement.setText(currentCredit.getDatePrelevement());
            this.retardEcheance.setText(currentCredit.getRetard());

        }
    }


}
