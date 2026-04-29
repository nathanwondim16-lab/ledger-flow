package com.pluralsight;

import java.time.format.DateTimeFormatter;

public class DateTimeFormats {
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("MM/dd/yy");
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("hh:mm a");


        private DateTimeFormats() {}
}
