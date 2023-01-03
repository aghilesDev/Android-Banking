package com.example.cnep.cnepe_banking.Models;

import java.util.ArrayList;

/**
 * Created by Aghiles on 2017-05-23.
 */

public class ResponseAllAgences {

    private ArrayList<AgenceViewModel> agences;
    private boolean hasMore;

    public ResponseAllAgences(ArrayList<AgenceViewModel> agences, boolean hasMore) {
        this.agences = agences;
        this.hasMore = hasMore;
    }

    public ArrayList<AgenceViewModel> getAgences() {
        return agences;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}
