package com.weather.pogodynka.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrentTest {

    /*@Test
    void gettingCurrentWeatherOutputTest() {

        String myPath = "src/test/resources/current.json";

        //given
        Path of = Path.of(myPath);
        try {
            CurrentWeatherHelperData currentWeather = new CurrentWeatherHelperData(
                    7, 999, 85, 9.2, "bezchmurnie");

            Gson gson = new Gson();
            //String myPath = "src/test/resources/current.json";
            Writer writer = Files.newBufferedWriter(of);
            //File file = new File(myPath);

            gson.toJson(currentWeather, writer);
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //when

        //StringBuffer contentWeather = new Gson().fromJson(currentWeather);

        //then

    }*/

    @Test
    void gettingCurrentWeatherOutputTest() {
        String myPath = "src/test/resources/current.json";

        String[] output;
        StringBuffer sb;

        // given
        try {
            //Gson gson = new Gson();

            creatingDataFileWithCurrentForecast();

            Reader reader = Files.newBufferedReader(Paths.get(myPath));
            sb = new StringBuffer();
            sb.append(reader);

            //CurrentWeatherHelperData outputWeather = gson.fromJson(reader, CurrentWeatherHelperData.class);
            reader.close();
            //StringBuffer content = new StringBuffer(outputWeather.toString());

            //when
            System.out.println(sb);
            output = Current.gettingCurrentWeatherOutput(sb);
            //System.out.println(Arrays.toString(forecast));

            // then
            System.out.println(output[0]);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void creatingDataFileWithCurrentForecast() {
        String myPath = "src/test/resources/current.json";

        try {
            CurrentWeatherHelperData currentWeather = new CurrentWeatherHelperData(
                    7, 999, 85, 9.2, "bezchmurnie");

            Gson gson = new Gson();

            Writer writer = Files.newBufferedWriter(Paths.get(myPath));

            gson.toJson(currentWeather, writer);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}