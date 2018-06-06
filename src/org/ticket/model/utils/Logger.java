package org.ticket.model.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static LOG_LEVEL loglLevel = LOG_LEVEL.NORMAL;

    public enum LOG_LEVEL {
        NORMAL,
        WARNING,
        DEBUG,
        CRITICAL
    }

    private Logger() {}

    /**
     * Initializes the log level
     * @param lv
     */
    public static void set(LOG_LEVEL lv) {
        if (lv != null) {
            loglLevel = lv;
        }
    }

    public static void log(String message, LOG_LEVEL lv) {

        if (lv.equals(LOG_LEVEL.DEBUG)) {
            System.out.println(getDateTime() + " [DEBUG] " + message);
        } else {
            System.out.println(getDateTime() + " [" + loglLevel + "] " + message);
        }
    }

    private static String getDateTime() {
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = new Date();
        return dt.format(d);
    }
}
