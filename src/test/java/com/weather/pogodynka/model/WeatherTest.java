package com.weather.pogodynka.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherTest {

    String[] days = new String[5];

    @Test
    void gettingFullWeatherOutputForDay5() throws IOException {

        //given
        Path filePath = Path.of("src/test/resources/weatherDataOutput.json");
        String content = Files.readString(filePath);

        StringBuffer sb = new StringBuffer();
        sb.append(content);

        //when
        String[] output = Weather.gettingWeatherOutput(sb);

        for (int i = 0; i < output.length; i++) {
            days[i] = output[i];
        }

        String temperature = "7" + "\u2103";
        String pressure = "1018" + "hPa";
        String humidity = "82" + "%";
        String windSpeed = "5.2" + "m/s";
        String description = "słabe opady deszczu";
        String day5Forecast = temperature + ", " + pressure + ", " + humidity + ", " + windSpeed + ", " + description;

        //then
        assertThat(day5Forecast).isEqualTo(days[4]);
    }

    @Test
    void gettingSomeWeatherOutputForDay4() throws IOException {

        //given
        Path filePath = Path.of("src/test/resources/weatherDataOutput.json");
        String content = Files.readString(filePath);

        StringBuffer sb = new StringBuffer();
        sb.append(content);

        //when
        String[] output = Weather.gettingWeatherOutput(sb);

        for (int i = 0; i < output.length; i++) {
            days[i] = output[i];
        }

        String temperature = "10" + "\u2103";
        String humidity = "44" + "%";

        //then
        assertEquals(temperature, days[3].substring(days[3].indexOf(0) + 1, days[3].indexOf(',')));
        assertThat(humidity).isEqualTo(days[3].substring(days[3].indexOf("44"), days[3].indexOf('%') + 1));
    }

    @Test
    void gettingSomeWeatherOutputForDay1() throws IOException {

        //given
        Path filePath = Path.of("src/test/resources/weatherDataOutput.json");
        String content = Files.readString(filePath);

        StringBuffer sb = new StringBuffer();
        sb.append(content);

        //when
        String[] output = Weather.gettingWeatherOutput(sb);

        for (int i = 0; i < output.length; i++) {
            days[i] = output[i];
        }

        String description = "pochmurnie";

        //then
        assertEquals(description, days[0].substring(days[0].indexOf("pochmurnie")));
    }
}