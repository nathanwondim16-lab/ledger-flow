package com.pluralsight;

import java.time.format.DateTimeFormatter;

/**
 * Utility class for commonly used date and time formats in the application.
 *
 * This class provides shared DateTimeFormatter instances to ensure
 * consistent formatting and parsing of dates and times across the system.
 *
 * Formats:
 * - DATE: MM/dd/yy (e.g., 04/27/26)
 * - TIME: hh:mm a (e.g., 03:15 PM)
 */
public class DateTimeFormats {

    // Formatter for dates in MM/dd/yy format.
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("MM/dd/yy");

    // Formatter for times in 12-hour format with AM/PM
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("hh:mm a");


    /**
     * Private constructor to prevent instantiation.
     * This class is intended to be used as a static utility holder only.
     */
    private DateTimeFormats() {}
}
