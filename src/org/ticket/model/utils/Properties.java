package org.ticket.model.utils;

import com.sun.istack.internal.NotNull;

public class Properties {

    private static final long MAJOR = 0;
    private static final long MINOR = 1;
    private static final long PATCH = 0;

    private Properties() {}

    public static String version() {
        return MAJOR + "." + MINOR + "." + PATCH;
    }

    public static String major() {
        return stringVersion(MAJOR);
    }

    public String minor() {
        return stringVersion(MINOR);
    }

    public String patch() {
        return stringVersion(PATCH);
    }

    @NotNull
    private static String stringVersion(long number) {
        return String.valueOf(number);
    }
}
