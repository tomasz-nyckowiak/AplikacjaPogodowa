package com.weather.pogodynka.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IconsTest {

    @Test
    void getImageCodeTest() {
        //given
        String exampleCorrectIconURL = "http://openweathermap.org/img/wn/10d@2x.png";
        String exampleCorrectImageCode = "10d";

        //when
        String result = Icons.getImageCode(exampleCorrectImageCode);

        //then
        assertThat(result).isEqualTo(exampleCorrectIconURL);
    }
}