package com.weather.pogodynka.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Destination {
    @SerializedName("name")
    private String name;

    @SerializedName("local_names")
    private Map<String, Object> names;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("country")
    private String country;

    //private Arrays[] tab;

    public Destination(String name, Map<String, Object> names, double lat, double lon, String country) {
        this.name = name;
        this.names = names;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getNames() {
        return names;
    }

    public void setNames(Map<String, Object> names) {
        this.names = names;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static String[] gettingDestinationOutput(StringBuffer content) {
        Destination[] destination = new Gson().fromJson(content.toString(), Destination[].class);
        //Destination destination = new Gson().fromJson(content.toString(), Destination.class);
        //tab[] = destination.country;

        String country = destination[0].getCountry();
        String namePL = destination[0].getNames().get("pl").toString();

        double latitude = destination[0].getLat();
        double longitude = destination[0].getLon();

        String lat = WeatherData.getCoordinates(latitude);
        String lon = WeatherData.getCoordinates(longitude);

        String[] output = new String[4];
        output[0] = country;
        output[1] = namePL;
        output[2] = lat;
        output[3] = lon;

        return output;
    }

    /*public Destination myDestinationObject(Object object) {
        object =
    }*/
}
