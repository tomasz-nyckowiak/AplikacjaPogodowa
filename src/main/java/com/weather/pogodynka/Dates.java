package com.weather.pogodynka;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Dates {
    private String[] days = new String[5];

    public String[] getForecastDays() {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i <= 4; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            String nextDay = dateFormat.format(calendar.getTime());
            days[i] = nextDay;
        }
        return days;
    }

    public String setTodayDate() {
        String pattern = "EEEEE, dd MMMMM, HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("pl", "PL"));

        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
