package com.weather.pogodynka;

public class Constants {
    private static String secretKey = "";
    public static final String PATTERN = "^([a-zA-Z\u0080-\u024F]+(?:(\\.) |-| |'))*[a-zA-Z\u0080-\u024F]*$";
    // Above is regular expression for city name, accepts only letters, spaces and dashes(-).
    // ^ - matches the start of a string without consuming any characters
    // [a-zA-Z-ɏ] - matches a single character in the set - common and special letters (case sensitive)
    // + - quantifier - matches the previous token between one and unlimited times, as many times as possible
    // (?:(\\.) |-| |') - non-capturing group
    // * - quantifier - matches the previous token between zero and unlimited times, as many times as possible
    // $ - matches the end of a string without consuming any characters

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        Constants.secretKey = secretKey;
    }
}
