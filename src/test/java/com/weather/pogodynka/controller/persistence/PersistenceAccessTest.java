package com.weather.pogodynka.controller.persistence;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistenceAccessTest {

    PersistenceAccess persistenceAccess = new PersistenceAccess();

    @Test
    void loadUserCityNameFromFileTest() {
        //given
        String myExampleCity = "Katowice";
        String myPath = "src/test/resources/fileTest.txt";

        //when
        File file = new File(myPath);
        String read = persistenceAccess.loadUserCityNameFromFile(file);

        //then
        assertEquals(myExampleCity, read);
    }


}