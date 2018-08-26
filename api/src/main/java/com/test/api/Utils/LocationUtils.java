package com.test.api.Utils;

public class LocationUtils {

    private static final long SPEED = 50; //km/h

    public static int getDistance(int locationA, int locationB) {
        return Math.abs(locationA - locationB);
    }

    //Suppose distance is in kilometers
    public static Long calculateTime(int distance) {
        return distance/SPEED;
    }
}
