package com.example.dagger2_api_login.untils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String PATTERN_DATE_FORMAT = "dd/MM/yyyy";
    private static final String PATTERN_TIME_FORMAT = "HH:mm";
    private static final String PATTERN_DATE_TIME_FORMAT = "dd/MM/yyyy - HH:mm";

    public static String convertMiliToDate(long second) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault());
        return format.format(new Date(second));
    }

    public static String convertMiliToTime(long second) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_TIME_FORMAT, Locale.getDefault());
        return format.format(new Date(second));
    }

    public static String convertMiliToDateTime(long second) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_DATE_TIME_FORMAT, Locale.getDefault());
        return format.format(new Date(second));
    }
}
