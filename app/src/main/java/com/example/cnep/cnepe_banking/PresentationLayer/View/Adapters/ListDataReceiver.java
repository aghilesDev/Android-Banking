package com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-04-20.
 */

public interface ListDataReceiver<V> {

    public void onArticlesReceived(ArrayList<V> articles, boolean hasMore);


}
