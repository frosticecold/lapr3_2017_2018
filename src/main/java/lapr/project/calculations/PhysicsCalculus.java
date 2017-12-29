package lapr.project.calculations;

import lapr.project.model.Gear;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleElectric;

public class PhysicsCalculus {

    private static final int MAX_THROTTLE = 0;
    private static final int MED_THROTTLE = 1;
    private static final int MIN_THROTTLE = 2;

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
        double velocity = vehicle.getRoadVelocityLimit(section.getTypology());//This velocity in KM/H
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
            if (slope > 0) {
                fa = rollingResistanceCalculationSlope(car.getRCC(), car.getTotalWeight(), slope);
                fa += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
            } else {
                fa = rollingResistanceCalculationSlope(car.getRCC(), car.getTotalWeight(), slope);
                fa += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
                double grav = car.getTotalWeight() * GRAVITY * Math.sin(Math.toRadians(slope));

                if (grav <= fa || car.getClass().equals(VehicleElectric.class)) {
                    fa -= grav;
                    if (car instanceof VehicleElectric) {
                        fa *= ((VehicleElectric) car).getEnergyRegenerationRatio();
                    }
                } else {
                    fa = 0;
                }

            }

        }
        return fa;
    }

    public static double[] calculateIdealMotorForce(Vehicle car, Segment s, final double neededForce, final double carvelocity) {
        if (car == null || s == null || neededForce < 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        double results[] = new double[4];
        //0) Throttle
        //1) gear
        //2) rpms
        //3) torque

        double generated_force[] = new double[3];
        double needed_sfc[] = new double[3];
        double max_velocity = 0;
        double minimum_force = Double.MAX_VALUE;
        double minimum_sfc = Double.MIN_VALUE;

        int gearSize = car.getGearbox().getNumberOfGears();
        double final_drive_ratio = car.getFinalDriveRatio();
        for (int gear = gearSize; gear > 0; gear--) {
            double gearRatio = car.getGearbox().getGear(gear).getRatio();
            for (double rpm = car.getMinRpm(); rpm <= car.getMaxRpm(); rpm += 20) {

                generated_force[0] = PhysicsCalculus.motorForceCalculation(car.getTorqueAtThrottle(Vehicle.THROTTLE_MAX, rpm), final_drive_ratio, gearRatio, car.getWheelSize());
                needed_sfc[0] = car.getAccelerator().getThrottleList().get(Vehicle.THROTTLE_MAX).getSFCByRPM(rpm);

                generated_force[1] = PhysicsCalculus.motorForceCalculation(car.getTorqueAtThrottle(Vehicle.THROTTLE_MEDIUM, rpm), final_drive_ratio, gearRatio, car.getWheelSize());
                needed_sfc[1] = car.getAccelerator().getThrottleList().get(Vehicle.THROTTLE_MEDIUM).getSFCByRPM(rpm);

                generated_force[2] = PhysicsCalculus.motorForceCalculation(car.getTorqueAtThrottle(Vehicle.THROTTLE_LOW, rpm), final_drive_ratio, gearRatio, car.getWheelSize());
                needed_sfc[2] = car.getAccelerator().getThrottleList().get(Vehicle.THROTTLE_LOW).getSFCByRPM(rpm);
                max_velocity = calcVelocityBasedOnRPMandGear(car, rpm, gearRatio);
                for (int i = 0; i < generated_force.length; i++) {
                    if (generated_force[i] >= neededForce) {
                        if (generated_force[i] < minimum_force) {
                            if (Math.abs(carvelocity - max_velocity) < 0.5) {
                                //if (needed_sfc[i] > minimum_sfc) {
                                minimum_sfc = needed_sfc[i];
                                minimum_force = generated_force[i];
                                results[THROTTLE_VEC] = convertThrottle(i);
                                results[GEAR_VEC] = gear;
                                results[RPM_VEC] = rpm;
                                results[TORQUE_VEC] = car.getTorqueAtThrottle(convertThrottle(i), rpm);
                            }
                        }
                    } else {
                        if (max_velocity > carvelocity) {
                            break;
                        }

                    }
                }

            }
        }

        return results;
    }

    private static int convertThrottle(final int i) {
        int throttle = 0;
        switch (i) {
            case MAX_THROTTLE:
                throttle = Vehicle.THROTTLE_MAX;
                break;
            case MED_THROTTLE:
                throttle = Vehicle.THROTTLE_MEDIUM;
                break;
            case MIN_THROTTLE:
                throttle = Vehicle.THROTTLE_LOW;
                break;
            default:
                throttle = -1;
                break;
        }
        return throttle;
    }
}
