package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;
import com.example.cnep.R;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.View.ListCompteView;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-30.
 */

public class AgenceAdapter extends RecyclerView.Adapter<AgenceAdapter.MyViewHolder> {

    ArrayList<AgenceViewModel> list=new ArrayList<>();
    private Context context;
    public AgenceAdapter(Context context)
    {
        super();
        this.context=context;

    }

    public void addArticles(ArrayList<AgenceViewModel> agences)
    {
        this.list.addAll(agences);
        notifyDataSetChanged();
    }


    public void setList(ArrayList<AgenceViewModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.agence_cellule,parent,false);

        return new MyViewHolder(view,context);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AgenceViewModel agence=list.get(position);
        holder.display(agence);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends BaseAdapter.ArticleViewHolder<AgenceViewModel> implements View.OnClickListener{
        private final TextView codeAgence;
        private final TextView libelléAgence;
        protected AgenceViewModel currentAgence;

        protected Context context;
        public MyViewHolder(final View itemView, final Context context) {
            super(itemView);
            codeAgence=(TextView)itemView.findViewById(R.id.codeAgence);
            libelléAgence=(TextView)itemView.findViewById(R.id.libelléAgence);

            this.context=context;
            itemView.setOnClickListener(this);
        }

        public void display(AgenceViewModel agence) {
            currentAgence=agence;
            codeAgence.setText(agence.getagenceid()+"");
            libelléAgence.setText(agence.getLibelle());
        }

        @Override
        public void onClick(View v) {
            Intent it=new Intent(context, ListCompteView.class);

            it.putExtra("codeAgence",currentAgence.id());

            context.startActivity(it);

        }
    }
}