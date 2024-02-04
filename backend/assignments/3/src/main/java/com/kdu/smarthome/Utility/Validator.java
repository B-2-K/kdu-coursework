package com.kdu.smarthome.Utility;

public class Validator {

    private Validator(){}

    /**
     * Validates if id is parsable or not
     * @param string
     * @return
     */
    public static boolean isParsable(String string){
        try{
            Long.parseLong(string);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
