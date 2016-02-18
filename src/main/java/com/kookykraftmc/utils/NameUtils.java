package com.kookykraftmc.utils;

public class NameUtils {

    private static NameUtils instance = new NameUtils();

    public NameUtils() {

    }

    public static NameUtils getInstance() {
        return instance;
    }

    /**
     * Fix the given text with making the first letter captializsed and the rest not.
     *
     * @param text the text fixing.
     * @param replaceUnderscore True to replace all _ with a space, false otherwise.
     * @return The new fixed text.
     */
    public String capitalizeString(String text, boolean replaceUnderscore) {
        if (text.isEmpty()) {
            return text;
        }

        if (text.length() == 1) {
            return text.toUpperCase();
        }

        String toReturn = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();

        if (replaceUnderscore) {
            toReturn = toReturn.replace("_", " ");
        }

        return toReturn;
    }
}
