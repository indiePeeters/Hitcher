package com.indigo.indie.hitcher;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Indie on 11/23/2018.
 */

public class locationService extends Service {
    Location lastLocation;
    LocationManager locationManager = null;
    LocalBroadcastManager broadcastManager;
    public Location getLastLocation(){
        return lastLocation;
    }

    private class LocationListener implements android.location.LocationListener
    {

        public LocationListener(String provider)
        {
            lastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            lastLocation.set(location);
            Intent intent = new Intent();
            intent.setAction("com.indigo.indie.hitcher");
            intent.putExtra("latitude", lastLocation.getLatitude());
            intent.putExtra("longitude", lastLocation.getLongitude());
            broadcastManager.sendBroadcast(intent);
        }

        @Override
        public void onProviderDisabled(String provider)
        {

        }

        @Override
        public void onProviderEnabled(String provider)
        {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
    }

    LocationListener[] locationListeners;

    @Override
    public void onCreate() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        locationListeners = new LocationListener[] {
                new LocationListener(LocationManager.GPS_PROVIDER),
                new LocationListener(LocationManager.NETWORK_PROVIDER)
        };
        if(locationManager == null) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        }
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

        } else{
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, locationListeners[1]);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListeners[0]);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            for (int i = 0; i < locationListeners.length; i++) {
                locationManager.removeUpdates(locationListeners[i]);
            }
        }
    }
}
