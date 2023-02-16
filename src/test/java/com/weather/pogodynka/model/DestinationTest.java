package com.weather.pogodynka.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weather.pogodynka.service.Icons;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DestinationTest {

    @Test
    void gettingDestinationOutputTest() {
        //given
        String myPath = "src/test/resources/destination.json";

        String[] output;
        StringBuffer sb = new StringBuffer();

        try {
            //Gson gson = new Gson();

            creatingDestinationDataFile();
            Reader reader = Files.newBufferedReader(Paths.get(myPath));

            //sb = new StringBuffer();
            sb.append(reader);
            System.out.println(sb);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //StringBuffer sb = new StringBuffer();
        //sb.append("Test");

        //when
        output = Destination.gettingDestinationOutput(sb);
        String country = "JP";
        String namePL = "Tokio";

        //then
        assertThat(country).isEqualTo(output[0]);
    }

    private void creatingDestinationDataFile() {

        String myPath = "src/test/resources/destination.json";

        try {
            Gson gson = new Gson();

            JsonObject object = new JsonObject();
            object.addProperty("name", "Tokio");
            object.addProperty("country", "JP");

            JsonArray array = new JsonArray();
            array.add(object);

            //JsonObject myObject = new JsonObject();
            //myObject.addProperty("test", array.toString());

            Writer writer = Files.newBufferedWriter(Paths.get(myPath));

            gson.toJson(array, writer);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}