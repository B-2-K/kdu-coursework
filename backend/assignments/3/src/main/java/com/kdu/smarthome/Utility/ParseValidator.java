package com.kdu.smarthome.Utility;

/**
 * Utility class for validating whether a string is parsable as a {@code Long}.
 */
public class ParseValidator {

    private ParseValidator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks whether a string is parsable as a {@code Long}.
     *
     * @param string The string to be checked for parseability.
     * @return {@code true} if the string is parsable as a {@code Long}, {@code false} otherwise.
     */
    public static boolean isParsable(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
