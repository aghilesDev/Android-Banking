package com.example.cnep.cnepe_banking.Models;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.LocationSource;

/**
 * Created by Aghiles on 2017-05-26.
 */

public class GoogleApiLocationSource extends CustomedLocationSource implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener/*, ResultCallback<LocationSettingsResult>*/ {


    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    public GoogleApiLocationSource(Context context) {
        super(context);
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
        createLocationRequest();
        addLocationRequest();


    }


    private void addLocationRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);


        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        // result.setResultCallback(this);

    }


    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    protected void startLocationUpdates() {


        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (LocationListener) this);
    }


    //CustomedLocationSource
    @Override
    public Location getLastLocation() {

        return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

//ConnectionCallBaks
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("test","connection reussit");
        System.out.println("connection reussit");

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
//OnConnectionFailedListener
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("test","connection echouée");
        System.out.println("connection echouée");
    }

    //LocationSource

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.listner=onLocationChangedListener;
        startLocationUpdates();


    }

    @Override
    public void deactivate() {

    }
//ResultCallback<LocationSettingsResult>
   /* @Override
    public void onResult(@NonNull LocationSettingsResult result) {
        final Status status = result.getStatus();
        final LocationSettingsStates locationSettingsStates = result.getLocationSettingsStates();

        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                // All location settings are satisfied. The client can
                // initialize location requests here.
                 ...
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    status.startResolutionForResult(
                            context,
                            REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    // Ignore the error.
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Location settings are not satisfied. However, we have no way
                // to fix the settings so we won't show the dialog.
                 ...
                break;
        }

    }*/
}
