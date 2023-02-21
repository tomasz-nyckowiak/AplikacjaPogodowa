package com.weather.pogodynka.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class DestinationTest {

    @Test
    void gettingDestinationOutputTest() throws IOException {

        //given
        Path filePath = Path.of("src/test/resources/destination.json");
        String content = Files.readString(filePath);

        String[] output;
        StringBuffer sb = new StringBuffer();
        sb.append(content);

        //when
        output = Destination.gettingDestinationOutput(sb);
        String country = "JP";
        String namePL = "Tokio";
        String lat = "35.68";
        String lon = "139.76";

        //then
        assertThat(country).isEqualTo(output[0]);
        assertThat(namePL).isEqualTo(output[1]);
        assertThat(lat).isEqualTo(output[2]);
        assertThat(lon).isEqualTo(output[3]);
    }
}