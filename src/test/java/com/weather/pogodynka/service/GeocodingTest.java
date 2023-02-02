package com.weather.pogodynka.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GeocodingTest {

    //Geocoding geocoding = new Geocoding();

    @Test
    void getDestinationTest() {
        //given
        Geocoding geocoding = mock(Geocoding.class);

        String someRandomSecretKey = "xyz00f7feeb86d6511a8bce5d0bd3abc";
        String exampleCorrectIconURL = "http://openweathermap.org/img/wn/10d@2x.png";
        String exampleCorrectImageCode = "10d";

        //given(geocoding.getDestination()).willReturn(15);



        //when
        //String result = geocoding.getDestination();

        //then
    }
}