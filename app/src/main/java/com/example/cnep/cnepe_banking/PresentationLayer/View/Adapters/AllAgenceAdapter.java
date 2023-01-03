package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;
import com.example.cnep.R;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cnep.cnepe_banking.Models.AgenceViewModel;
import com.example.cnep.cnepe_banking.PresentationLayer.View.AgenceDetailledView;

/**
 * Created by Aghiles on 2017-04-24.
 */

public class AllAgenceAdapter extends BaseAdapter<AgenceViewModel> {

    private Context context;

    public AllAgenceAdapter(IArticleSender sender, Context context) {
        super(sender);
        this.context = context;
    }


    public void clear()
    {
        articles.clear();
    }

    @Override
    public int getListItem() {
        return R.layout.agence_cellule;
    }

    @Override
    public ArticleViewHolder getNewArticleViewHolder(View view) {
        return new MyViewHolder(view,context);
    }

    public static class MyViewHolder extends AgenceAdapter.MyViewHolder
    {
        public MyViewHolder(View itemView, Context context) {
            super(itemView, context);
        }

        @Override
        public void onClick(View v) {

            Intent intent=new Intent(context, AgenceDetailledView.class);
            intent.putExtra("agence",currentAgence);
            context.startActivity(intent);

        }
    }


}
