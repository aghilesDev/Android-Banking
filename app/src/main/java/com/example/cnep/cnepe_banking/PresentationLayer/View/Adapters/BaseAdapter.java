package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;
import com.example.cnep.R;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-20.
 */

public abstract class BaseAdapter<V>extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ListDataReceiver<V> {

    private static final int VIEW_TYPE_ARTICLE = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    protected final ArrayList<V> articles=new ArrayList<>();
    private IArticleSender sender;




    private boolean hasMore = false;

    public BaseAdapter() {
        super();
    }

    public BaseAdapter(IArticleSender sender) {
        super();
        this.sender = sender;
    }

    public abstract int getListItem();
    public abstract ArticleViewHolder getNewArticleViewHolder(View view);

    @Override
    public void onArticlesReceived(ArrayList<V> articles, boolean hasMore) {
        this.articles.addAll(articles);
        this.hasMore = hasMore;
       notifyDataSetChanged();


    }




    @Override
    public int getItemCount() {
        return this.articles.size() + (this.hasMore ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < this.articles.size())
            return VIEW_TYPE_ARTICLE;
        else
            return VIEW_TYPE_PROGRESS;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_ARTICLE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(this.getListItem(), parent, false);
                return getNewArticleViewHolder(view);
            }
            case VIEW_TYPE_PROGRESS: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress, parent, false);
                return new ProgressViewHolder(view);
            }
            default:
                throw new IllegalStateException("Unknown type" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ArticleViewHolder)
            ((ArticleViewHolder) holder).display(this.articles.get(position));
        else
        if(holder instanceof ProgressViewHolder)
            if(sender!=null)
            sender.LoadNext();
            else
                this.onArticlesReceived(new ArrayList<V>(),false);

    }



    public static abstract class ArticleViewHolder<V> extends RecyclerView.ViewHolder {



        public ArticleViewHolder(View view) {
            super(view);
        }

        public abstract void display(V article);
    }



    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }




}
