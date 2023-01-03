package com.example.cnep.cnepe_banking.PresentationLayer.Presenter;

/**
 * Created by Aghiles on 2017-04-18.
 */

public class BasePresenter<V> {

    protected V view;

    public final void attachView(V view)
    {
        this.view=view;
    }

    public final void dettachView()
    {
        this.view=null;
    }

    public final boolean isAttached()
    {
        return (this.view!=null);
    }
}
