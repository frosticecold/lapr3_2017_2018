/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.calculations;

public class UnitConversion {

    public static final double MILES_TO_KM = 1.609344;
    public static final double KM_PER_HOUR_TO_M_PER_SECOND = 0.277777778;
    public static final double M_PER_SECOND_TO_KM_PER_HOUR = 3.6;
    public static final int KM_TO_METERS = 1000;
    private static final int HOUR_IN_SECONDS = 3600;
    private static final String CENTIMETERS = "cm";
    private static final String METERS = "m";
    private static final String KILOMETERS = "km";
    private static final String KILOGRAMS = "kg";
    private static final String GRAMS = "g";
    private static final String KILOMETERS_PER_HOUR = "km/h";
    private static final String METERS_PER_SECOND = "m/s";
    private static final double MEGA_JOULE = Math.pow(10, 6);

    public static double convertKilometersToMeters(String distance) {
        double lengthKilometers = Double.parseDouble(
                distance.replaceAll("[^0-9.]", ""));
        return lengthKilometers * 1000;
    }

    public static double convertJoulesToMegaJoules(double energy) {
        return energy / MEGA_JOULE;
    }

    public static double convertJoulesToLitres(String fuel, double energy) {
        if (fuel.equalsIgnoreCase("gasoline")) {
            return convertJoulesToMegaJoules(energy) / Constants.GASOLINE_ENERGY_PER_LITRE;
        } else {
            if (fuel.equalsIgnoreCase("diesel")) {
                return convertJoulesToMegaJoules(energy) / Constants.DIESEL_ENERGY_PER_LITRE;
            }
        }
        return convertJoulesToMegaJoules(energy);
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
        return grams / 1000;
    }

    public static double convertKmToMeters(double km) {
        return km * 1000;
    }

    public static double convertToPretendedUnity(String number, String pretended) {
        String[] numberSplit = number.split(" ");
        switch (pretended) {
            case (KILOMETERS): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(CENTIMETERS)) {
                    return Double.parseDouble(numberSplit[0]) / 100000;
                } else if (numberSplit[1].equalsIgnoreCase(METERS)) {
                    return Double.parseDouble(numberSplit[0]) / 1000;
                }
                break;
            }
            case (METERS): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(CENTIMETERS)) {
                    return Double.parseDouble(numberSplit[0]) / 100;
                } else if (numberSplit[1].equalsIgnoreCase(KILOMETERS)) {
                    return Double.parseDouble(numberSplit[0]) * 1000;
                }
                break;
            }
            case (CENTIMETERS): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(METERS)) {
                    return Double.parseDouble(numberSplit[0]) * 100;
                } else if (numberSplit[1].equalsIgnoreCase(KILOMETERS)) {
                    return Double.parseDouble(numberSplit[0]) * 100000;
                }
                break;
            }
            case (KILOGRAMS): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(GRAMS)) {
                    return Double.parseDouble(numberSplit[0]) / 1000;
                }
                break;
            }
            case (GRAMS): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(KILOGRAMS)) {
                    return Double.parseDouble(numberSplit[0]) * 1000;
                }
                break;
            }
            case (METERS_PER_SECOND): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(KILOMETERS_PER_HOUR)) {
                    return Double.parseDouble(numberSplit[0]) * KM_PER_HOUR_TO_M_PER_SECOND;
                }
                break;
            }
            case (KILOMETERS_PER_HOUR): {
                if (numberSplit[1].equalsIgnoreCase(pretended)) {
                    return Double.parseDouble(numberSplit[0]);
                } else if (numberSplit[1].equalsIgnoreCase(METERS_PER_SECOND)) {
                    return Double.parseDouble(numberSplit[0]) * M_PER_SECOND_TO_KM_PER_HOUR;
                }
                break;
            }
        }
        return Double.parseDouble(numberSplit[0]);
    }

    public static String convertSecondstoHoursMinSec(double seconds) {
        String fullTime = "";

        int hours = ((int) seconds / 3600);
        int min = ((int)seconds % 3600) / 60;
        int sec = (int)seconds % 60;
        fullTime = String.format("%02d:%02d:%02d", hours,min,sec);
        return fullTime;
    }
}
