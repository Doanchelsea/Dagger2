package com.example.dagger2_api_login.untils;

import android.text.format.DateFormat;

import java.text.DecimalFormat;

public class FormatUtils {
    private static final String PATTERN_ESTIMATED_PRICE = "###,###,###";
    private static final String PATTERN_ESTIMATED_DISTANCE = "###,###.#";
    private static final String PATTERN_ESTIMATED_DURATION = "###,###,###";
    private static final String PATTERN_DATE = "HH:mm - dd/MM/yyyy";

    public static String convertEstimatedPrice(double estimatedPrice) {
        DecimalFormat format = new DecimalFormat(PATTERN_ESTIMATED_PRICE);
        return format.format(estimatedPrice);
    }

    public static String convertEstimatedDuration(double estimatedDuration) {
        DecimalFormat format = new DecimalFormat(PATTERN_ESTIMATED_DURATION);
        return format.format(estimatedDuration);
    }

    public static String convertEstimatedDistance(double estimatedDistance) {
        DecimalFormat format = new DecimalFormat(PATTERN_ESTIMATED_DISTANCE);
        return format.format(estimatedDistance);
    }

    public static CharSequence convertDate(long date) {
        return DateFormat.format(PATTERN_DATE, date);
    }

    public static String convertLocationToString(double latitude, double longitude) {
        return String.format("%s,%s", latitude, longitude);
    }

    /**
     * @param objectId TP1565342407585299300
     * @return TRIP1565342407585299300
     * <p>
     * objectId.substring(0,1) => 1565342407585299300
     */
    public static String convertObjectIdToTripId(String objectId) {
        return String.format("TRIP%s ", objectId.substring(2));
    }
}
