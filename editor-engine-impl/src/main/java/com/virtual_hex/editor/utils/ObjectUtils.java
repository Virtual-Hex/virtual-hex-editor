package com.virtual_hex.editor.utils;

public class ObjectUtils {

    /**
     * Supports checking if an object is null or empty.
     *
     * Checks if the object is null.
     *  - If it is a string then if its empty or 0 length.
     *  - If an array checks for 0 length and if string array checks the first value
     *  to see if it is null or 0 length.

     *
     * @param o
     * @return
     * @throws IllegalAccessException
     */
    public static boolean isObjectNullOrEmpty(Object o) throws IllegalAccessException {
        if(o == null){
            return true;
        } else {
            if(o instanceof String){
                String oString = (String) o;
                return oString.isEmpty();
            } else if (o instanceof String[]){
                String[] oStringArray = (String[]) o;
                if(oStringArray.length == 0){
                   return true;
                } else {
                    String s = oStringArray[0];
                    return s == null || s.isEmpty();
                }
            } else if(o instanceof Object[]){
                Object[] oObjectArray = (Object[]) o;
                if(oObjectArray.length == 0){
                    return true;
                } else {
                    return oObjectArray[0] == null;
                }
            } else {
                return false;
            }
        }
    }


}
