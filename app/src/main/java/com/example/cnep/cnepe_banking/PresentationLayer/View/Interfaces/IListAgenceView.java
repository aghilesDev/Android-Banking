package com.example.cnep.cnepe_banking.PresentationLayer.View.Interfaces;

import com.example.cnep.cnepe_banking.PresentationLayer.View.Adapters.AgenceAdapter;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-03-30.
 */

public interface IListAgenceView {
  //  public void addToList();
    public void setAdapter(AgenceAdapter adapter);
    public void setWilayas(ArrayList<String> wilayas);
}
