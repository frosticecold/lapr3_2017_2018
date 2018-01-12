package lapr.project.model;

import lapr.project.calculations.Constants;

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
        return mass * GRAVITY * Math.sin(Math.toRadians(angle));
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

    public static double[] calcVelocityHighestGearLowestThrotlle(Vehicle car) {
        Regime lowestSFCRegime = car.getThrottles().get(25).getRegimeList().get(0);
        for (Regime regime : car.getThrottles().get(25).getRegimeList()) {
            if (regime.getSFC() < lowestSFCRegime.getSFC()) {
                lowestSFCRegime = regime;
            }
        }
        double gearRatio = car.getGearbox().getLowestGearRatio();
        double minvelocity = calcVelocityBasedOnRPMandGear(car, lowestSFCRegime.getRpmLow(), gearRatio);
        double velocity = calcVelocityBasedOnRPMandGear(car, lowestSFCRegime.getRpmHigh(), gearRatio);
        double result[] = {minvelocity, velocity};
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
        double force = 0;
        double vel = calcMaximumVelocity(segment, car, section);
        double slope = segment.calculateSlope();
        if (Math.abs(slope) < 0.005) {
            force = rollingResistanceCalculation(car.getRCC(), car.getTotalWeight());
            force += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
        } else {
            //if (slope > 0) {
            force = rollingResistanceCalculationSlope(car.getRCC(), car.getTotalWeight(), slope);
            force += airDragCalculation(vel, car.getDragCoefficient(), car.getFrontalArea());
            double grav = car.getTotalWeight() * GRAVITY * Math.sin(Math.toRadians(slope));
            force += grav;
        }
//        if (force <= 0) {
//            force = 0;
//        }
        return force;
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

    public static double calcRPMBasedOnVelocityGear(double velocity, double gearRatio, double finalDriveRatio, double wheelsize) {
        double numerator = (velocity * 60 * finalDriveRatio * gearRatio);
        double denominator = (Math.PI * wheelsize);
        double wrpm = numerator / denominator;
        return Math.round(wrpm);
    }

    public static double[] calcIdealMotorForceBasedAcceleration(Section section, Segment segment, Vehicle car, double acceleration, double velocity) {
        double fma = car.getTotalWeight() * acceleration;
        double otherforces = calcForceInSegment(segment, car, section);
        double sumforces = fma + otherforces;
        double results[] = {-1, -1, -1, -1, -1};
        double minimum_force = Double.POSITIVE_INFINITY;
        double minimum_gear = Double.NEGATIVE_INFINITY;
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        //4) Final drive ratio
        boolean valid = true;
        int throttle_index = 0;
        int gear_index = car.getGearbox().getNumberOfGears();

        if (car instanceof VehicleCombustion) {
            if (Math.abs(velocity) < 0.05) {
                velocity = PhysicsCalculus.calcVelocityBasedOnRPMandGear(car, car.getMinRpm(), car.getGearbox().getGear(1).getRatio());
            }
        }
        while (valid) {
            if (throttle_index == Vehicle.THROTTLES.length) {
                throttle_index = 0;
                gear_index--;
            }
            if (gear_index < 1) {
                break;
            }
            int throttle = Vehicle.THROTTLES[throttle_index];
            double gearRatio = car.getGearbox().getGear(gear_index).getRatio();
            double wrpm = PhysicsCalculus.calcRPMBasedOnVelocityGear(velocity, gearRatio, car.getFinalDriveRatio(), car.getWheelSize());
            if (Math.abs(wrpm - car.getMinRpm()) <= 0.5) {
                wrpm = car.getMinRpm();
            } else {
                if (Math.abs(wrpm - car.getMaxRpm()) <= 0.5) {
                    wrpm = car.getMaxRpm();
                }
            }
            if (wrpm >= car.getMinRpm() && wrpm <= car.getMaxRpm()) {
                double torque = car.getTorqueAtThrottle(throttle, wrpm);
                double motorforce = PhysicsCalculus.motorForceCalculation(torque, car.getFinalDriveRatio(), gearRatio, car.getWheelSize());

                if (motorforce > sumforces) {
                    //if (motorforce < minimum_force) {
                    if (gear_index > minimum_gear) {
                        minimum_force = motorforce;
                        minimum_gear = gear_index;
                        results[0] = gear_index;
                        results[1] = wrpm;
                        results[2] = torque;

                        results[4] = car.getFinalDriveRatio();
                        if (car instanceof VehicleCombustion) {
                            results[3] = car.getThrottles().get(throttle).getSFCByRPM(wrpm);
                        }
                    }

                }
            }
            throttle_index++;
        }
        return results;
    }

    public static double calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(double initialVelocity, double desiredVelocity, double acceleration) {
        return (desiredVelocity - initialVelocity) / acceleration;
    }

    public static double calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(double initialVelocity, double desiredVelocity, double acceleration) {
        double numerator = (desiredVelocity * desiredVelocity) - (initialVelocity * initialVelocity);
        return numerator / (2 * acceleration);

    }

    public static double calcFuelComsumption(double sfc, double motorPower, double time) {
        return sfc * (motorPower * time) / (36 * Math.pow(10, 5));
    }

    public static double calcEnergySpentPerGramOfFuel(Vehicle car, double energy) {
        if (car instanceof VehicleCombustion) {
            String typeOfFuel = ((VehicleCombustion) car).getFuel();
            if (typeOfFuel.equalsIgnoreCase(Vehicle.FUEL_DIESEL)) {
                return energy * (Constants.FUEL_DIESEL * 1000);
            } else {
                if (typeOfFuel.equalsIgnoreCase(Vehicle.FUEL_GASOLINE)) {
                    return energy * Constants.FUEL_GASOLINE * 1000;
                }

            }

        }
        return energy;
    }
}
