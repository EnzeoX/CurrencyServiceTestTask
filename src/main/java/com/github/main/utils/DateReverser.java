package com.github.main.utils;

public class DateReverser {

    public static String reverseDate(String date) {
        //DATE REVERSER from DDMMYYYY to YYYYMMDD
        String[] arr = date.split("\\.");
        String buffer = arr[0];
        arr[0] = arr[2];
        arr[2] = buffer;
        StringBuilder builder = new StringBuilder();
        builder.append(arr[0]).append(arr[1]).append(arr[2]);
        return builder.toString();
    }
}
