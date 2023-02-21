package com.weather.pogodynka.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentTest {

    @Test
    void gettingCurrentWeatherOutputTest() throws IOException {

        //given
        Path filePath = Path.of("src/test/resources/weatherDataOutput.json");
        String content = Files.readString(filePath);

        String[] output;
        StringBuffer sb = new StringBuffer();
        sb.append(content);

        //when
        output = Current.gettingCurrentWeatherOutput(sb);
        String temperature = "10" + "\u2103";
        String pressure = "1010" + "hPa";
        String humidity = "59" + "%";
        String windSpeed = "6.2" + "m/s";
        String description = "zachmurzenie umiarkowane";

        //then
        assertThat(temperature).isEqualTo(output[0]);
        assertThat(pressure).isEqualTo(output[1]);
        assertThat(humidity).isEqualTo(output[2]);
        assertThat(windSpeed).isEqualTo(output[3]);
        assertThat(description).isEqualTo(output[4]);
    }
}