package lapr.project.calculations;

import lapr.project.model.Gear;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;

public class PhysicsCalculus {

    public static double GRAVITY = Constants.GRAVITY;

    public static double forceActingOnVehicle(double motorForce, double rollingResistance, double gravitationalForce, double airDrag) {
        double F = motorForce - rollingResistance - gravitationalForce - airDrag;
        return F;
    }

    public static double motorForceCalculation(double torqueFunction, double finalDriveRatio, double gearRatio, double tireRatio) {
        if (torqueFunction < 0 || finalDriveRatio < 0 || gearRatio < 0) {
            return -1;
        }
        if (tireRatio < 0) {
            return -1;
        }
        return (torqueFunction * finalDriveRatio * gearRatio) / tireRatio;
    }

    public static double rollingResistanceCalculation(double rollingResistanceCoeficient, double mass, double angle) {
        if (rollingResistanceCoeficient < 0 || mass < 0 || angle < 0) {
            return -1;
        }
        return rollingResistanceCoeficient * mass * GRAVITY * Math.cos(angle);
    }

    public static double gravitationalForceCalculation(double mass, double angle) {
        if (mass < 0 || angle < 0) {
            return -1;
        }
        return mass * GRAVITY * Math.sin(angle);
    }

    public static double airDragCalculation(double velocityRelativeAir, double airDragCoeficient, double frontalArea, double airDensity) {
        if (velocityRelativeAir < 0 || airDragCoeficient < 0 || frontalArea < 0) {
            return -1;
        }
        if (airDensity < 0) {
            return -1;
        }
        return 0.5 * airDragCoeficient * frontalArea * airDensity * Math.pow(velocityRelativeAir, 2);
    }

    public static double velocityRelativeAirCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio, double windSpeed) {
        if (rpm < 0 || finalDriveRatio < 0 || kGear < 0) {
            return -1;
        }
        if (tireRatio < 0 || windSpeed < 0) {
            return -1;
        }
        return 2 * Math.PI * rpm / 60 / finalDriveRatio / kGear * tireRatio + windSpeed / 3.6;
    }

    public static double velocityRelativeGroundCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio) {
        if (rpm < 0 || finalDriveRatio < 0) {
            return -1;
        }
        if (kGear < 0 || tireRatio < 0) {
            return -1;
        }
        return (2 * Math.PI * rpm) / 60 / finalDriveRatio / kGear * tireRatio * 3.6;
    }

    public static double[] calculateRPMandGearRatio(Vehicle vehicle, double velocity) {
        double results[] = new double[2];
        double rpm = Double.POSITIVE_INFINITY;
        double gear_ratio = 0;
        double first_part = (velocity * 60 * vehicle.getFinalDriveRatio()) / (Math.PI * vehicle.getWheelSize());
        for (Gear g : vehicle.getGearbox().getGearList()) {
            double copy = first_part;
            copy = copy * g.getM_ratio();
            if (copy > vehicle.getMinRpm() && copy < vehicle.getMaxRpm()) {
                if (copy < rpm) {
                    rpm = copy;
                    gear_ratio = g.getM_ratio();
                }
            }
        }
        results[0] = rpm;
        results[1] = gear_ratio;
        return results;
    }

    public static double getMaximumVelocityIn(Segment segment, Vehicle vehicle, Section section) {
        double velocity = vehicle.getRoadVelocityLimit(section.getTypology());//This velocity in KM/H
        velocity = velocity / 3.6;
        double windSpeed = segment.getWindSpeed() * Math.cos(Math.toRadians(segment.getWindDirection()));

        velocity -= windSpeed;
        double maxVelocity = segment.getMaximumVelocity();
        velocity = Math.min(velocity, (maxVelocity > 0) ? maxVelocity : velocity);
        return velocity;
    }
}
