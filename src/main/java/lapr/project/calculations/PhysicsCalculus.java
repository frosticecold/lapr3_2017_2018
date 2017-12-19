package lapr.project.calculations;

public class PhysicsCalculus {

    public static double GRAVITY = Constants.GRAVITY;

    public double forceActingOnVehicle(double motorForce, double rollingResistance, double gravitationalForce, double airDrag) {
        double F = motorForce - rollingResistance - gravitationalForce - airDrag;
        return F;
    }

    public double motorForceCalculation(double torqueFunction, double finalDriveRatio, double gearRatio, double tireRatio) {
        if (torqueFunction < 0 || finalDriveRatio < 0 || gearRatio < 0) {
            return -1;
        }
        if (tireRatio < 0) {
            return -1;
        }
        return (torqueFunction * finalDriveRatio * gearRatio) / tireRatio;
    }

    public double rollingResistanceCalculation(double rollingResistanceCoeficient, double mass, double angle) {
        if (rollingResistanceCoeficient < 0 || mass < 0 || angle < 0) {
            return -1;
        }
        return rollingResistanceCoeficient * mass * GRAVITY * Math.cos(angle);
    }

    public double gravitationalForceCalculation(double mass, double angle) {
        if (mass < 0 || angle < 0) {
            return -1;
        }
        return mass * GRAVITY * Math.sin(angle);
    }

    public double airDragCalculation(double velocityRelativeAir, double airDragCoeficient, double frontalArea, double airDensity) {
        if (velocityRelativeAir < 0 || airDragCoeficient < 0 || frontalArea < 0) {
            return -1;
        }
        if (airDensity < 0) {
            return -1;
        }
        return 0.5 * airDragCoeficient * frontalArea * airDensity * Math.pow(velocityRelativeAir, 2);
    }

    public double velocityRelativeAirCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio, double windSpeed) {
        if (rpm < 0 || finalDriveRatio < 0 || kGear < 0) {
            return -1;
        }
        if (tireRatio < 0 || windSpeed < 0) {
            return -1;
        }
        return 2 * Math.PI * rpm / 60 / finalDriveRatio / kGear * tireRatio + windSpeed / 3.6;
    }

    public double velocityRelativeGroundCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio) {
        if (rpm <0 || finalDriveRatio<0  ) {
            return -1 ;
        }
        if (kGear <0 || tireRatio  < 0 ) {
            return -1;
        }
        return (2 * Math.PI * rpm) / 60 / finalDriveRatio / kGear * tireRatio * 3.6;
    }

}
