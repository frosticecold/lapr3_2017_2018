/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.calculations;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class UnitConverstion {

    public static final double MILES_TO_KM = 1.609344;
    public static final int KM_TO_METERS = 1000;
    private static final int HOUR_IN_SECONDS = 3600;

    public static double convertKilometersToMeters(String distance) {
        double lengthKilometers = Double.parseDouble(
                distance.replaceAll("[^0-9.]", ""));
        return lengthKilometers * 1000;
    }

    public static double convertKilometersPerHourToMetersPerSecond(String velocity) {
        double velocityKilometersPerHour = Double.parseDouble(
                velocity.replaceAll("[^0-9.]", ""));

        return velocityKilometersPerHour * KM_TO_METERS / HOUR_IN_SECONDS;
    }

    public static double convertMilesPerHourToKilometersPerHour(String velocity) {
        double velocityMilesPerHour = Double.parseDouble(velocity.replaceAll("[^0-9.]", ""));
        return velocityMilesPerHour * MILES_TO_KM;

    }

    public static double convertMilesToKilometers(String distance) {
        double lengthMiles = Double.parseDouble(distance.replaceAll("[^0-9.]", ""));
        return lengthMiles * MILES_TO_KM;
    }

    public static double convertGramsToKiloGrams(String weight) {
        double grams = Double.parseDouble(weight.replaceAll("[^0-9.]", ""));
        return grams * 1000;

    }
}
