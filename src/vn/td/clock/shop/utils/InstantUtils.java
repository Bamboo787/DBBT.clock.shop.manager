package vn.td.clock.shop.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    private static final String PATTERN_FORMAT = "dd/MM/yyyy HH:mm";

    public static String instantToString(Instant instant) {
        return instantToString(instant, null);
    }

    public static String instantToString(Instant instant, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : PATTERN_FORMAT).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
