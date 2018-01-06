package lapr.project.calculations;

import lapr.project.model.Gear;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleElectric;

public class PhysicsCalculus {

    public final static double GRAVITY = Constants.GRAVITY;
    public static final double AIR_DENSITY = 1.225;
    public static final int THROTTLE_VEC = 0;
    public static final int GEAR_VEC = 1;
    public static final int RPM_VEC = 2;
    public static final int TORQUE_VEC = 3;

    public static double forceActingOnVehicle(double motorForce, double rollingResistance, double airDrag) {
        if (motorForce < 0 || rollingResistance < 0 || airDrag < 0) {
            return -1;
        }
        return (motorForce - rollingResistance - airDrag);

    }

    public static double forceActingOnVehicleOnSlope(double motorForce, double rollingResistance, double gravitationalForce, double airDrag) {
        double F = motorForce - rollingResistance - gravitationalForce - airDrag;
        return F;
    }

    public static double motorForceCalculation(double torqueFunction, double finalDriveRatio, double gearRatio, double wheelSize) {
        if (torqueFunction < 0 || finalDriveRatio < 0 || gearRatio < 0) {
            return -1;
        }
        if (wheelSize < 0) {
            return -1;
        }
        return (torqueFunction * finalDriveRatio * gearRatio) / (wheelSize / 2);
    }

    public static double rollingResistanceCalculation(double rollingResistanceCoeficient, double mass) {
        if (rollingResistanceCoeficient < 0 || mass < 0) {
            return -1;
        }
        return rollingResistanceCoeficient * mass * GRAVITY;
    }

    public static double rollingResistanceCalculationSlope(double rollingResistanceCoeficient, double mass, double angle) {
        if (rollingResistanceCoeficient < 0 || mass < 0 || angle < 0) {
            return -1;
        }
        return rollingResistanceCoeficient * mass * GRAVITY * Math.cos(Math.toRadians(angle));
    }

    public static double gravitationalForceCalculation(double mass, double angle) {
        if (mass < 0 || angle < 0) {
            return -1;
        }
        return mass * GRAVITY * Math.sin(angle);
    }

    public static double airDragCalculation(double velocityRelativeAir, double airDragCoeficient, double frontalArea) {
        if (velocityRelativeAir < 0 || airDragCoeficient < 0 || frontalArea < 0) {
            return -1;
        }
        return 0.5 * airDragCoeficient * frontalArea * AIR_DENSITY * Math.pow(velocityRelativeAir, 2);
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
            copy = copy * g.getRatio();
            if (copy > vehicle.getMinRpm() && copy < vehicle.getMaxRpm()) {
                if (copy < rpm) {
                    rpm = copy;
                    gear_ratio = g.getRatio();
                }
            }
        }
        results[0] = rpm;
        results[1] = gear_ratio;
        return results;
    }

    public static double calcVelocityBasedOnRPMandGear(Vehicle car, double rpm, double gear_ratio) {

        double result = (Math.PI * car.getWheelSize() * rpm) / (60 * car.getFinalDriveRatio() * gear_ratio);

        return result;
    }

    public static double calcEnginePower(double torque, double rpm) {
        if (torque <= 0 || rpm <= 0) {
            throw new IllegalArgumentException(("Invalid paratemeter."));
        }
        double result = (2 * Math.PI * torque * rpm) / 60;
        return result;

    }

    public static double calcMaximumVelocity(Segment segment, Vehicle vehicle, Section section) {
        double velocity = vehicle.getMaximumPermitedVelocity(section.getTypology());//This velocity in KM/H
        velocity = velocity / 3.6;  // convert to m/s
        double windSpeed = segment.getWindSpeed() * Math.cos(Math.toRadians(segment.getWindDirection()));

        if (windSpeed < 0) {
            windSpeed = 0;
        }
        velocity -= windSpeed;
        double maxVelocity = segment.getMaximumVelocity() / 3.6; // convert KM/H to m/s
        velocity = Math.min(velocity, (maxVelocity > 0) ? maxVelocity : velocity);
        return velocity;
    }

    public static double calcForceInSegment(Segment segment, Vehicle car, Section section) {
        double fa = 0;
        double vel = calcMaximumVelocity(segment, car, section);
        double slope = segment.calculateSlope();
        if (Math.abs(slope) < 0.005) {
            fa = rollingResistanceCalculation(car.getRCC(), car.getTotalWeight());
            fa += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
        } else {
            //if (slope > 0) {
            fa = rollingResistanceCalculationSlope(car.getRCC(), car.getTotalWeight(), slope);
            fa += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
            double grav = car.getTotalWeight() * GRAVITY * Math.sin(Math.toRadians(slope));
            fa += grav;
//            } else {
//                fa = rollingResistanceCalculationSlope(car.getRCC(), car.getTotalWeight(), slope);
//                fa += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
//                double grav = car.getTotalWeight() * GRAVITY * Math.sin(Math.toRadians(slope));
//
//                if (grav <= fa || car.getClass().equals(VehicleElectric.class)) {
//                    fa -= grav;
//                    if (car instanceof VehicleElectric) {
//                        fa *= ((VehicleElectric) car).getEnergyRegenerationRatio();
//                    }
//                } else {
//                    fa = 0;
//                }
//
//            }

        }
        if (fa <= 0) {
            fa = 0;
        }
        return fa;
    }

    public static double calcTimeBasedOnVelocityAndAcceleration(double desiredVelocity, double initialVelocity, double acceleration) {
        double time = (desiredVelocity - initialVelocity) / Math.abs(acceleration);
        return time;
    }

    public static double calcTimeBasedOnDistanceAndAcceleration(double initialVelocity, double distance, double acceleration) {
        double firstpart = 2 * (distance - initialVelocity) / acceleration;
        double result = Math.sqrt(firstpart);
        return result;
    }

    public static double calcDistance(double initialvelocity, double acceleration, double time) {
        double distance = (initialvelocity * time) + (0.5 * acceleration * time * time);
        return distance;
    }

    public static double calcVelocityBasedOnInitialVelocityAccelerationTime(double initialVelocity, double acceleration, double time) {

        return initialVelocity + (acceleration * time);
    }
}
