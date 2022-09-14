package com.weather.pogodynka;

public class UserDefaultLocation {
    private static String userDefaultLocation;

    public String getUserDefaultLocation() {
        return userDefaultLocation;
    }

    public void settingUserLocation(String location) {
        userDefaultLocation = location;
    }
}
