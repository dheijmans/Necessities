package com.dennisheijmans.necessities.tools;

public class Tool {

    public static boolean isLong(String string) {
        if (string == null) {
            return false;
        }
        try {
            long l = Long.parseLong(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
