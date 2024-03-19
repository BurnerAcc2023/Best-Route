package com.lucidity.BestRoute.util;

import com.lucidity.BestRoute.entity.Address;
import com.lucidity.BestRoute.entity.DeliveryExecutive;
import com.lucidity.BestRoute.entity.Node;

public final class HaversineDistanceUtils {
    private static final int EARTH_RADIUS = 6371;
    private static final int AVERAGE_SPEED = 20;

    public static int getTime(Node n1, Node n2) {
        Address address1 = n1.getAddress();
        Address address2 = n2.getAddress();
        double distance = getDistance(address1.getLatitude(), address1.getLongitude(), address2.getLatitude(), address2.getLongitude());
        return (int) Math.ceil(distance/AVERAGE_SPEED);
    }

    public static int getTime(DeliveryExecutive deliveryExecutive, Node node) {
        Address address = node.getAddress();
        double lat = deliveryExecutive.getCurrentLatitude();
        double lon = deliveryExecutive.getCurrentLongitude();

        double distance = getDistance(lat, lon, address.getLatitude(), address.getLongitude());
        return (int) Math.ceil(distance/AVERAGE_SPEED);
    }
    public static double getDistance(double lat1, double long1, double lat2, double long2) {

        double latDistance = toRad(lat2 - lat1);
        double longDistance = toRad(long2 - long1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private static double toRad(double value) {
        return value * Math.PI / 180;
    }
}
