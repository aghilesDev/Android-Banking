package com.example.cnep.cnepe_banking.Models;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Aghiles on 2017-04-23.
 */

public class ConnectionChecker {

    private Activity activity;

    public ConnectionChecker(Activity activity) {
        this.activity = activity;
    }

    public boolean isOnline()
    {
        ConnectivityManager connMgr=(ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
}
