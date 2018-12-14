package com.indigo.indie.hitcher;

/**
 * Created by Indie on 12/3/2018.
 */

public class HitchHotspot {

    private int id;
    private String destination;
    private double latitude;
    private double longitude;
    private int averageTime;

    public HitchHotspot(){

    }

    public HitchHotspot(String destination, double latitude, double longitude, int averageTime){
        this.destination = destination;;
        this.latitude = latitude;
        this.longitude = longitude;
        this.averageTime = averageTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public String getDestination() {
        return destination;
    }
}
