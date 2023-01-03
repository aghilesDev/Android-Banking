package com.example.cnep.cnepe_banking.PresentationLayer.View;
import com.example.cnep.R;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.widget.Toast;

import com.example.cnep.cnepe_banking.Models.CustomedLocationSource;
import com.example.cnep.cnepe_banking.Models.GoogleApiLocationSource;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback{

    private GoogleMap mMap;
    private CustomedLocationSource source;
    private Context context;
    boolean mLocationPermissionGranted=false;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context=this;
        source = new ProviderLocationSource(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(checkPermissiom())
        {
            getLocation();
            initializeMarkes();
        }
        else
        {
            askPermissions();
            if(checkPermissiom())
            {
                getLocation();
                initializeMarkes();
            }else
            {
                Toast.makeText(this,"Pour accéder à la localisation ,veuillez nous accorder les permissions nécéssaires",Toast.LENGTH_SHORT);
                finish();
            }

        }





        /*initializeMarkes();
        getDeviceLocation();*/

    }



    public void SourceLocationError() {

    }

    public void initializeMarkes() {
        ArrayList<Marker> markers=new ArrayList<>();
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.721611, 3.176711)).title("BAB EZZOUAR").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.791301, 3.060303)).title("BAB EL OUED").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.729874, 3.053083)).title("BIR KHADEM").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.777337, 3.058016)).title("ES SAFIR").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.778386, 3.057707)).title("BEN M'HIDI 1").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.783131, 3.062533)).title("CHE GUEVARA").snippet("Agence")));
        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(36.774384, 3.058892)).title("BEN M'HIDI 2").snippet("Agence")));

       // markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng()).title("")));

    }

  



    private boolean checkPermissiom()
    {


        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else
            return false;

    }


    private void askPermissions()
    {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }

    }

    public void getLocation()
    {
        // mMap.setLocationSource(source);

        mMap.setMyLocationEnabled(true);

        Location loc = source.getLastLocation();

        if (loc != null) {
            LatLng lat = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lat,15));
        }else{  Toast.makeText(this,"Erreur provenant du fournisseur de localisation,Veuillez reessayer plus tard",Toast.LENGTH_SHORT);}
            /*mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);*/
    }
















}
