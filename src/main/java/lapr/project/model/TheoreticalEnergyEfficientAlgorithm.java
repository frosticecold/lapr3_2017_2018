/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class TheoreticalEnergyEfficientAlgorithm implements PathAlgorithm {

    public static final String ALG_NAME = "Theoretical Most Energy Efficient Path";
    private static final int RESULTS_SIZE = 4;

    @Override
    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, double acceleration) {
        LinkedList<Junction> path = new LinkedList<>();
        LinkedList<Section> sectionpath = new LinkedList<>();
        double results[] = efficientPath(graph, start, end, v, path, sectionpath, acceleration);
        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, results, this.toString());
        alg.calculate();

        return alg;
    }

    private static double[] efficientPath(Graph<Junction, Section> graph, Junction vOrig, Junction vDest, Vehicle vehicle, LinkedList<Junction> shortPath, LinkedList<Section> sectionpath, double acceleration) {
        double results[] = {-1, -1, -1};//Results 0)Energy 1) Time 2)Grams
        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return results;
        }
        boolean[] visited = new boolean[graph.numVertices()];
        int[] pathKeys = new int[graph.numVertices()];
        double[] time = new double[graph.numVertices()];
        double[] energy = new double[graph.numVertices()];
        double[] velocity = new double[graph.numVertices()];
        double[] fuelcomsumption = new double[graph.numVertices()];
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            int key = graph.getKey(v);
            pathKeys[key] = -1;
            visited[key] = false;
            time[key] = Double.MAX_VALUE;
            energy[key] = Double.MAX_VALUE;
            fuelcomsumption[key] = Double.MAX_VALUE;
            velocity[key] = 0;;
        }

        efficientPathLength(graph, vOrig, vDest, vertices, visited, pathKeys, time, energy, velocity, fuelcomsumption, vehicle, acceleration);

        //double timePath = time[graph.getKey(vDest)];
        int key = graph.getKey(vDest);
        double timeresult = time[key];
        double energyresult = energy[key];
        double fuel = fuelcomsumption[key];

        results[0] = timeresult;
        results[1] = energyresult;
        results[2] = fuel;
        if (Double.MAX_VALUE - timeresult > 0) {
            getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return results;
        }
        return results;
    }

    private static <V, E> void efficientPathLength(Graph<Junction, Section> g, Junction vOrig, Junction vDest, Junction[] vertices,
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, double[] velocity, double[] fuelcomsumption, Vehicle vehicle, double acceleration) {

        Section section = new Section();
        int i = g.getKey(vOrig);
        energy[i] = 0;
        time[i] = 0;
        fuelcomsumption[i] = 0;
        Junction vOrigin;
        int kDest = g.getKey(vDest);
        while (i != -1) {

            vOrigin = vertices[i];
            int kOrg = g.getKey(vOrigin);
            visited[kOrg] = true;
            for (Edge<Junction, Section> edg : g.outgoingEdges(vOrigin)) {
                Junction vAdj = g.opposite(vOrigin, edg);
                int kAdj = g.getKey(vAdj);
                section = edg.getElement();
                double previous_velocity = velocity[kOrg];
//                double previous_torque = torque[kOrg];
//                double previous_rpm = rpm[kOrg];
//                double previous_gear = gear[kOrg];
                boolean finishline = false;
                if (g.getKey(edg.getVDest()) == kDest) {
                    finishline = true;

                }
                //Results
                //0) Time
                //1) Energy
                //2) Velocity
                //3) SFC(g)
                double results[] = calcEfficientPath(vDest, section, vehicle, acceleration, previous_velocity, finishline);
//                if (results[0] != -1) {
//                    if (!visited[kAdj] && energy[kAdj] > (energy[kOrg] + results[1])) {
//                        time[kAdj] = time[kOrg] + results[0];
//                        pathKeys[kAdj] = kOrg;
//                        energy[kAdj] = energy[kOrg] + results[1];
//                        velocity[kAdj] = results[2];
//                        fuelcomsumption[kAdj]=results[3];
//                    }
//                }
                if (results[0] > 0) {
                    if (!visited[kAdj] && energy[kAdj] > (energy[kOrg] + results[1])) {
                        time[kAdj] = time[kOrg] + results[0];
                        pathKeys[kAdj] = kOrg;
                        energy[kAdj] = energy[kOrg] + results[1];
                        velocity[kAdj] = results[2];
                        fuelcomsumption[kAdj] = fuelcomsumption[kOrg] + results[3];
                    }
                }
            }
            i = getVertMinDist(energy, visited);
        }
    }

    private static double[] calcEfficientPath(Junction vDest, Section section, Vehicle car, double acceleration, double previousVelocity, boolean finishline) {
        double[] results = new double[RESULTS_SIZE];
        double[] tempresults = new double[RESULTS_SIZE];
        //Results
        //0) Time
        //1) Energy
        //2) Velocity
        //3) Fuel in grams
        for (Segment seg : section.getSequenceOfSegments()) {
            boolean accel = false;
            boolean braking = false;
            double maximumvel = car.getMaximumPermitedVelocity2(seg, section.getTypology()) / 3.6;
            boolean islastSegment = false;
            if (finishline) {
                if (section.getEndingJunction().getName().equalsIgnoreCase(vDest.getName())) {
                    islastSegment = section.isLastSegment(seg);
                }
            }
            //If car needs acceleration
            if (previousVelocity < maximumvel) {
                braking = false;
                accel = true;
            } else {
                //If car needs braking
                if (previousVelocity > maximumvel) {
                    accel = false;
                    braking = true;
                }
            }
            //If car is accelerating
            if (accel) {
                if (car instanceof VehicleCombustion) {
                    tempresults = calcAcceleratingSegment(section, seg, car, previousVelocity, maximumvel, acceleration, islastSegment);
                } else {
                    tempresults = calcAcceleratingSegmentEletricCar(section, seg, ((VehicleElectric) car), previousVelocity, maximumvel, acceleration, islastSegment);
                }
            } else {
                //If car is braking
                if (braking) {
                    if (car instanceof VehicleCombustion) {
                        tempresults = calcBrakingSegment(section, seg, car, previousVelocity, maximumvel, acceleration * -1, islastSegment);
                    } else {
                        tempresults = calcBrakingSegmentEletric(section, seg, ((VehicleElectric) car), previousVelocity, maximumvel, acceleration * -1, islastSegment);
                    }
                } else {
                    tempresults = calcNoAcceleration(section, seg, car, previousVelocity, acceleration, islastSegment);
                }

            }

            if (tempresults[0] > 0) {
                //Results
                //0) Time
                //1) Energy
                //2) Velocity
                //3) Grams
                results[0] += tempresults[0];
                results[1] += tempresults[1];
                results[2] = tempresults[2];
                results[3] += tempresults[3];
                previousVelocity = results[2];
            }
        }
//        1) Energy
//        if (results[1] != -1) {
//            results[3] = PhysicsCalculus.calcEnergySpentPerGramOfFuel(car, results[1]);
//        }
        return results;
    }

    private static double[] calcAcceleratingSegment(Section section, Segment seg, Vehicle car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] tempresults = new double[RESULTS_SIZE];
        double[] carresult = new double[4];
        double[] brakingresult = {0, 0, 0, 0};
        //Results
        //0) Time (s)
        //1) Energy (g)
        //2) Velocity (m/s)
        //3) Grams

        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        boolean accelerating = true;
        double currentspeed = initialvelocity;
        double deltatimeacel = 1 / acceleration;
        if (initialvelocity <= 0) {
            if (car instanceof VehicleCombustion) {
                currentspeed = PhysicsCalculus.calcVelocityBasedOnRPMandGear(car, car.getMinRpm(), car.getGearbox().getGear(1).getRatio());
            }
        }

        //Do calculations while accelerating
        while (accelerating) {
            if (currentspeed >= maximumvelocity) {
                accelerating = false;
                result[2] = maximumvelocity;
                currentspeed = maximumvelocity;
                break;
            }
            for (; currentspeed <= maximumvelocity; currentspeed++) {
                double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, acceleration, currentspeed);
                //0) Gear
                //1) RPM
                //2) Torque
                //3) SFC
                if (motoresult[0] > 0) {
                    carresult = motoresult;
                    result[0] += 1 / acceleration;
                    double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                    result[1] += enginepower * (1 / acceleration);
                    result[2] = currentspeed;
                    double fuelComsumption = PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, deltatimeacel);
                    result[3] += fuelComsumption;
                }

                if (motoresult[0] < 0) {
                    currentspeed--;
                    result[2] = currentspeed;
                    accelerating = false;
                    break;
                }
            }
        }
        if (isLastSegment) {
            brakingresult = calcBrakingLastSegment(section, seg, car, currentspeed, acceleration);
            //0) Time        
            //1) Distance
            //2) Energy
            //3) FuelComsumption

        }
        //Do calculations when reached no acceleration
        double seglengthaccel = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialvelocity, currentspeed, acceleration);
        double segremaining = (seg.getLength() * 1000) - seglengthaccel - brakingresult[1];
        double deltatime = segremaining / currentspeed;
        double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        double carpower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);
        result[0] += deltatime + brakingresult[0];

//        result[1] += fuelcompsumption + brakingresult[2];
        result[1] += (carpower * deltatime) + brakingresult[2];
        result[2] = currentspeed;
        double fuelcompsumption = PhysicsCalculus.calcFuelComsumption(motoresult[3], carpower, deltatime);
        result[3] += fuelcompsumption + brakingresult[3];
        return result;
    }

    private static double[] calcAcceleratingSegmentEletricCar(Section section, Segment seg, VehicleElectric car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] carresult = new double[4];
        double[] brakingresult = {0, 0, 0, 0};
        double currentspeed = initialvelocity;
        //Results
        //0) Time (s)
        //1) Energy (J)
        //2) Velocity (m/s)
        //3) Grams

        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        //Do calculations while accelerating
        boolean accelerating = true;
        double deltatime = Math.abs(1 / acceleration);
        while (accelerating) {
            if (currentspeed >= maximumvelocity) {
                accelerating = false;
                result[2] = maximumvelocity;
                currentspeed = maximumvelocity;
                break;
            }
            for (; currentspeed <= maximumvelocity; currentspeed++) {
                double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, acceleration, currentspeed);
                //0) Gear
                //1) RPM
                //2) Torque
                //3) SFC
                if (motoresult[0] > 0) {
                    if (motoresult[1] > 0) {
                        carresult = motoresult;
                        result[0] += deltatime;
                        double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                        result[1] += enginepower * deltatime;
                        result[2] = currentspeed;
                    }
                }

                if (motoresult[0] < 0) {
                    currentspeed--;
                    result[2] = currentspeed;
                    accelerating = false;
                    break;
                }
            }
        }
        double accelLength = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialvelocity, currentspeed, acceleration);
        if (isLastSegment) {
            brakingresult = calcBrakingLastSegmentEletric(section, seg, car, currentspeed, acceleration);
            //0) Time        
            //1) Distance
            //2) Energy

        }
        double seglength = (seg.getLength() * 1000);
        carresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        double segremaining = seglength - accelLength - brakingresult[1];
        double deltatimeremaining = segremaining / currentspeed;
        double carpower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
        double energy = carpower * deltatimeremaining;

        //Acceltime + remaining time + braking time
        result[0] += deltatimeremaining + brakingresult[0];

        //Accel energy spent + energy spend with no accel + braking energy spent
        result[1] += energy + brakingresult[2];
        result[2] = currentspeed;

        return result;
    }

    private static double[] calcNoAcceleration(Section section, Segment seg, Vehicle car, double currentspeed, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        //Results
        //0) Time
        //1) Energy
        //2) Velocity
        double brakingresults[] = {0, 0, 0, 0};
        if (isLastSegment) {
            //0) Time        
            //1) Distance
            //2) FuelComsumption
            if (car instanceof VehicleCombustion) {
                brakingresults = calcBrakingLastSegment(section, seg, car, currentspeed, acceleration);
            } else {
                brakingresults = calcBrakingLastSegmentEletric(section, seg, car, currentspeed, acceleration);
            }

        }

        double seglength = seg.getLength() * 1000;
        double deltatime = (seglength - brakingresults[1]) / currentspeed;
        double[] carresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC

        double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
        double energy = enginepower * deltatime;
//        if (car instanceof VehicleCombustion) {
//            energy = PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, deltatime);
//        } else {
//            energy = enginepower * deltatime;
//        }
        result[0] = deltatime + brakingresults[0];
        result[1] += energy + brakingresults[2];
        result[2] = currentspeed;
        result[3] = carresult[3];
        return result;

    }

    private static double[] calcBrakingSegment(Section section, Segment seg, Vehicle car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] tempresults = new double[RESULTS_SIZE];

        //Results
        //0) Time (s)
        //1) Energy (g)
        //2) Velocity (m/s)
        //3) Grams
        double[] carresult = new double[4];
        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        double[] brakinglastsegment = {0, 0, 0, 0};
        //0) Time        
        //1) Distance
        //2) Energy
        //3) Grams
        boolean braking = true;
        double currentspeed = initialvelocity;
        double deltatimeacel = 1 / Math.abs(acceleration);
        //Do calculations while accelerating
        while (braking) {
            if (currentspeed <= maximumvelocity) {
                braking = false;
                result[2] = maximumvelocity;
                currentspeed = maximumvelocity;
                break;
            }
            for (; currentspeed >= maximumvelocity; currentspeed--) {
                double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, acceleration, currentspeed);
                //0) Gear
                //1) RPM
                //2) Torque
                //3) SFC
                if (motoresult[0] > 0) {
                    carresult = motoresult;
                    result[0] += deltatimeacel;
                    double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                    result[1] += enginepower * deltatimeacel;
                    result[2] = currentspeed;
                    result[3] += PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, deltatimeacel);
                }

                if (motoresult[0] < 0) {
                    currentspeed++;
                    result[2] = currentspeed;
                    braking = false;
                    break;
                }
            }
        }
        if (isLastSegment) {
            brakinglastsegment = calcBrakingLastSegment(section, seg, car, currentspeed, acceleration);
            //0) Time        
            //1) Distance
            //2) Energy
            //3) Grams
        }
        //Do calculations with no acceleration
        double seglengthaccel = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialvelocity, currentspeed, acceleration);
        double segremaining = (seg.getLength() * 1000) - seglengthaccel - brakinglastsegment[1];
        double deltatime = segremaining / currentspeed;
        double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        double carpower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);

        result[0] += deltatime + brakinglastsegment[0];
        result[1] += carpower * segremaining;
        result[2] = currentspeed;
        result[3] += PhysicsCalculus.calcFuelComsumption(motoresult[3], carpower, deltatime) + brakinglastsegment[3];

        return result;
    }

    private static double[] calcBrakingSegmentEletric(Section section, Segment seg, VehicleElectric car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] carresult = new double[4];
        double[] brakingresult = {0, 0, 0, 0};
        double currentspeed = initialvelocity;
        //Results
        //0) Time (s)
        //1) Energy (J)
        //2) Velocity (m/s)

        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        //Do calculations while accelerating
        double brakingTime = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(currentspeed, 0, acceleration);
        double brakingLength = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(currentspeed, 0, acceleration);
        double deltatime = Math.abs(1 / acceleration);
        //double neededForce = PhysicsCalculus.calcForceInSegment(seg, car, section);
        //result[1] += neededForce * brakingLength;
        boolean braking = true;
        //Do calculations while accelerating
        while (braking) {
            if (currentspeed <= maximumvelocity) {
                braking = false;
                result[2] = maximumvelocity;
                currentspeed = maximumvelocity;
                break;
            }
            for (; currentspeed >= maximumvelocity; currentspeed--) {
                double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, acceleration, currentspeed);
                //0) Gear
                //1) RPM
                //2) Torque
                //3) SFC
                if (motoresult[0] != -1) {
                    carresult = motoresult;
                    result[0] += deltatime;
                    if (motoresult[1] > 0) {
                        double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                        result[1] += enginepower * deltatime;
                    }
                    result[2] = currentspeed;
                }

                if (motoresult[0] == -1) {
                    currentspeed++;
                    result[2] = currentspeed;
                    braking = false;
                    break;
                }
            }
        }

        //Energy
        result[1] *= (1 - car.getEnergyRegenerationRatio());
        //If needed to brake in last segment
        if (isLastSegment) {
            brakingresult = calcBrakingLastSegmentEletric(section, seg, car, currentspeed, acceleration);
            //0) Time        
            //1) Distance
            //2) Energy
            //3) Grams
            brakingresult[2] *= (1 - car.getEnergyRegenerationRatio());

        }

        double seglength = (seg.getLength() * 1000);
        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        double segremaining = seglength - brakingLength - brakingresult[1];
        double deltatimeremaining = segremaining / currentspeed;
        double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        double carpower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);
        double energy = carpower * deltatimeremaining;

        result[0] += deltatimeremaining + brakingresult[0] + brakingTime;
        //braking energy + energy + braking result;
        result[1] += energy + brakingresult[2];
        result[2] = currentspeed;

        return result;
    }

    private static double[] calcBrakingLastSegment(Section section, Segment seg, Vehicle car, double initialVelocity, double acceleration) {
        double result[] = {0, 0, 0, 0};
        //0) Time        
        //1) Distance
        //2) Energy
        //3) FuelComsumption
        if (acceleration > 0) {
            acceleration = acceleration * -1;
        }
        double brakingspace = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialVelocity, 0, acceleration);
        double brakingtime = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, 0, acceleration);
        double minvel = PhysicsCalculus.calcVelocityBasedOnRPMandGear(car, car.getMinRpm(), car.getGearbox().getLowestGearRatio());
        double deltatimeaccel = Math.abs(1 / acceleration);
        for (double vel = initialVelocity; vel >= minvel; vel--) {
            double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, acceleration, vel);
            //0) Gear
            //1) RPM
            //2) Torque
            //3) SFC
            if (motoresult[0] > 0) {
                double enginepower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);
                result[2] += (enginepower * deltatimeaccel);
                result[3] += PhysicsCalculus.calcFuelComsumption(motoresult[3], enginepower, deltatimeaccel);
            }

            if (motoresult[0] < 0) {
                break;
            }
        }
        result[0] = brakingtime;
        result[1] = brakingspace;
        return result;
    }

    private static double[] calcBrakingLastSegmentEletric(Section section, Segment seg, Vehicle car, double initialVelocity, double acceleration) {
        double result[] = {0, 0, 0, 0};
        //0) Time        
        //1) Distance
        //2) Energy
        //3) Grams
        if (acceleration > 0) {
            acceleration = acceleration * -1;
        }
        double brakingspace = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialVelocity, 0, acceleration);
        double brakingtime = PhysicsCalculus.calcTimeBasedOnInitialVelocityDesiredVelocityAndAcceleration(initialVelocity, 0, acceleration);

        result[0] = brakingtime;
        result[1] = brakingspace;
        return result;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static void getPath(Graph<Junction, Section> g, Junction vOrig, Junction vDest, Junction[] verts, int[] pathKeys, LinkedList<Junction> path, LinkedList<Section> sectionpath) {

        if (!vOrig.equals(vDest)) {
            path.push(vDest);

            int vKey = g.getKey(vDest);
            int prevVKey = pathKeys[vKey];
            Junction vPrevious;
            vPrevious = verts[prevVKey];
            if (g.getEdge(vDest, vPrevious).getElement().getDirection() == Section.Direction.REVERSE) {
                sectionpath.push(g.getEdge(vDest, vPrevious).getElement());
            } else {
                sectionpath.push((g.getEdge(vPrevious, vDest)).getElement());
            }

            getPath(g, vOrig, vPrevious, verts, pathKeys, path, sectionpath);
        } else {
            path.push(vOrig);
        }
    }

    private static int getVertMinDist(double[] dist, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int indice = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && (dist[i] < min)) {
                min = dist[i];
                indice = i;
            }
        }

        return indice;
    }

    @Override
    public String toString() {
        return ALG_NAME;
    }

}
