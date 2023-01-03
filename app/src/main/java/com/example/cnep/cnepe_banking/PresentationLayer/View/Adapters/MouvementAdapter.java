package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;
import com.example.cnep.R;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.cnep.cnepe_banking.Models.MouvementViewModel;

/**
 * Created by Aghiles on 2017-04-19.
 */

public class MouvementAdapter extends BaseAdapter<MouvementViewModel> {


    private Context context;
    public MouvementAdapter(Context context)
    {
        super();
        this.context=context;

    }

    @Override
    public int getListItem() {
        return R.layout.mouvement_cellule;
    }

    @Override
    public ArticleViewHolder getNewArticleViewHolder(View view) {
        return new MyViewHolder(view,context);
    }


    public class MyViewHolder extends ArticleViewHolder<MouvementViewModel> {

        //declaration de champ
        private TextView dateCredit;
        private TextView intituleCredit;
        private TextView montantCredit;
        private MouvementViewModel currentMouvement;
        private Context context;
        public MyViewHolder(final View itemView, final Context context) {
            super(itemView);
            /*affectation des champ*/
            this.dateCredit = (TextView) itemView.findViewById(R.id.TDateMouvement);
            this.intituleCredit = (TextView) itemView.findViewById(R.id.TIntituleMouvement);
            this.montantCredit = (TextView) itemView.findViewById(R.id.TMontantMouvement);

            this.context = context;
        }

        public void display(MouvementViewModel mouvement) {
            currentMouvement=mouvement;
            /* affectation contenu*/
            this.dateCredit.setText(this.currentMouvement.getDate());
            this.intituleCredit.setText(this.currentMouvement.getIntitule());
            this.montantCredit.setText(this.currentMouvement.getMontant()+"");
              }
    }
}
