package com.outreach.interviews.map.models;

public class GeoCode {
    public double lat;

    public double lng;

    public GeoCode(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String toString() {
        return String.format("[%f, %f]", this.lat, this.lng);
    }
}