package com.example.cnep.cnepe_banking.Models;
import com.example.cnep.R;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.LocationSource;

/**
 * Created by Aghiles on 2017-05-26.
 */

public abstract class CustomedLocationSource implements LocationListener,LocationSource {

    protected OnLocationChangedListener listner;
    protected Context context;

    public CustomedLocationSource(Context context) {
        this.context = context;
    }

    public abstract Location getLastLocation();



    @Override
    public void onLocationChanged(Location location) {
        if(listner!=null)
            listner.onLocationChanged(location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String newStatus = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                newStatus = "OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                newStatus = "TEMPORARILY_UNAVAILABLE";
                break;
            case LocationProvider.AVAILABLE:
                newStatus = "AVAILABLE";
                break;
        }
        String msg = String.format(context.getResources().getString(R.string.provider_new_status), provider, newStatus);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

    }


        @Override
        public void onProviderDisabled(String provider) {
            String msg = String.format(context.getResources().getString(R.string.provider_disabled), provider);
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }










}
