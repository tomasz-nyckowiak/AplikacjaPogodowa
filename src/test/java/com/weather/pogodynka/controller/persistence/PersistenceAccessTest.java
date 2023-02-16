package com.weather.pogodynka.controller.persistence;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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

    @Test
    void shouldExpectedNullIfThereAreNoFile() {
        //given
        String myPath = "src/test/resources/myFile.txt";

        //when
        File file = new File(myPath);
        String read = persistenceAccess.loadUserCityNameFromFile(file);

        //then
        assertThat(read).isEqualTo(null);
    }

    @Test
    void isCityNameFromFileValidTest() {
        //given
        String myExampleCity = "Berlin";
        String myPath = "src/test/resources/cityValidation.txt";

        //when
        boolean isCityNameValid = persistenceAccess.isCityNameFromFileValid();

        //then
        assertThat(isCityNameValid).isTrue();
    }


}