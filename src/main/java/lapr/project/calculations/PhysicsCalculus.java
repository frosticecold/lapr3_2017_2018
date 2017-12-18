package lapr.project.calculations;

public class PhysicsCalculus {

    public static double GRAVITY = Constants.GRAVITY;

    public double forceActingOnVehicle(double motorForce, double rollingResistance, double gravitationalForce, double airDrag) {
        double F = motorForce - rollingResistance - gravitationalForce - airDrag;
        return F;
    }

    public double motorForceCalculation(double torqueFunction, double finalDriveRatio, double gearRatio, double tireRatio) {
        double motorForce = (torqueFunction * finalDriveRatio * gearRatio) / tireRatio;
        return motorForce;
    }

    public double rollingResistanceCalculation(double rollingResistanceCoeficient, double mass, double angle) {
        double rollingResistance = rollingResistanceCoeficient * mass * GRAVITY * Math.cos(angle);
        return rollingResistance;
    }

        public double gravitationalForceCalculation(double mass, double angle) {
        double gravitationalForce = mass * GRAVITY * Math.sin(angle);
        return gravitationalForce;
    }

    public double airDragCalculation(double velocityRelativeAir, double airDragCoeficient, double frontalArea, double airDensity) {
        double airDrag = 0.5 * airDragCoeficient * frontalArea * airDensity * Math.pow(velocityRelativeAir, 2);
        return airDrag;
    }

    public double velocityRelativeAirCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio, double windSpeed) {
        double velocityRelativeAir = 2 * Math.PI * rpm / 60 / finalDriveRatio / kGear * tireRatio + windSpeed / 3.6;
        return velocityRelativeAir;
    }

    public double velocityRelativeGroundCalculation(double rpm, double finalDriveRatio, double kGear, double tireRatio) {
        double velocityRelativeGround = (2 * Math.PI * rpm) / 60 / finalDriveRatio / kGear * tireRatio * 3.6;
        return velocityRelativeGround;
    }

}
