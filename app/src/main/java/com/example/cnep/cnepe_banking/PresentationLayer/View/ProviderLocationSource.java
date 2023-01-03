package com.example.cnep.cnepe_banking.PresentationLayer.View;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import com.example.cnep.cnepe_banking.Models.CustomedLocationSource;
import com.google.android.gms.maps.LocationSource;

/**
 * Created by Aghiles on 2017-05-26.
 */

public class ProviderLocationSource extends CustomedLocationSource{
    private LocationManager locationManager;

    public ProviderLocationSource(Context context) {
        super(context);
        locationManager=(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public Location getLastLocation() {


        Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!=null)
        return location;
        location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.listner = onLocationChangedListener;
        LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        if (gpsProvider != null) {
            locationManager.requestLocationUpdates(gpsProvider.getName(), 0, 10, this);
        }

        LocationProvider networkProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
        ;
        if (networkProvider != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000 , 0, this);


        }
    }

    @Override
    public void deactivate() {

    }
}
