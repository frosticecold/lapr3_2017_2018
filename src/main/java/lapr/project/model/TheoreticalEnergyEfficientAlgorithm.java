/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.pathalgorithms.PathAlgorithm;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class TheoreticalEnergyEfficientAlgorithm implements PathAlgorithm {

    public static final String ALG_NAME = "Theoretical Most Energy Efficient Path";
    private static final int RESULTS_SIZE = 3;

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
        double results[] = {-1, -1};//Results 0)Energy 1) Time
        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return results;
        }
        boolean[] visited = new boolean[graph.numVertices()];
        int[] pathKeys = new int[graph.numVertices()];
        double[] time = new double[graph.numVertices()];
        double[] energy = new double[graph.numVertices()];
        double[] velocity = new double[graph.numVertices()];
        double[] torque = new double[graph.numVertices()];
        double[] rpm = new double[graph.numVertices()];
        double[] gear = new double[graph.numVertices()];
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            int key = graph.getKey(v);
            pathKeys[key] = -1;
            visited[key] = false;
            time[key] = Double.MAX_VALUE;
            energy[key] = Double.MAX_VALUE;
            velocity[key] = 0;;
        }

        efficientPathLength(graph, vOrig, vDest, vertices, visited, pathKeys, time, energy, velocity, vehicle, acceleration);

        //double timePath = time[graph.getKey(vDest)];
        results[0] = time[graph.getKey(vDest)];
        results[1] = energy[graph.getKey(vDest)];

        if (Double.MAX_VALUE - results[0] > 0) {
            getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return results;
        }
        return results;
    }

    private static <V, E> void efficientPathLength(Graph<Junction, Section> g, Junction vOrig, Junction vDest, Junction[] vertices,
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, double[] velocity, Vehicle vehicle, double acceleration) {

        Section section = new Section();
        int i = g.getKey(vOrig);
        energy[i] = 0;
        time[i] = 0;
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
                double results[] = calcEfficientPath(vDest, section, vehicle, acceleration, previous_velocity, finishline);
                if (results[0] != -1) {
                    if (!visited[kAdj] && energy[kAdj] > (energy[kOrg] + results[1])) {
                        time[kAdj] = time[kOrg] + results[0];
                        pathKeys[kAdj] = kOrg;
                        energy[kAdj] = energy[kOrg] + results[1];
                        velocity[kAdj] = results[2];
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
                tempresults = calcAcceleratingSegment(section, seg, car, previousVelocity, maximumvel, acceleration, islastSegment);
            } else {
                //If car is braking
                if (braking) {
                    tempresults = calcBrakingSegment(section, seg, car, previousVelocity, maximumvel, acceleration * -1, islastSegment);
                } else {
                    tempresults = calcNoAcceleration(section, seg, car, previousVelocity, acceleration, islastSegment);

                }
            }
            if (tempresults[0] != -1) {
                //Results
                //0) Time
                //1) Energy
                //2) Velocity
                results[0] += tempresults[0];
                results[1] += tempresults[1];
                results[2] = tempresults[2];
                previousVelocity = results[2];
            }
        }
        //1) Energy
        if (results[1] != -1) {
            results[1] = PhysicsCalculus.calcEnergySpentPerGramOfFuel(car, results[1]);
        }
        return results;
    }

    private static double[] calcAcceleratingSegment(Section section, Segment seg, Vehicle car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] tempresults = new double[RESULTS_SIZE];
        double[] carresult = new double[4];
        //Results
        //0) Time (s)
        //1) Energy (g)
        //2) Velocity (m/s)

        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        boolean accelerating = true;
        double currentspeed = initialvelocity;
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
                if (motoresult[0] != -1) {
                    carresult = motoresult;
                    result[0] += 1 / acceleration;
                    double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                    double fuelComsumption = PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, 1 / acceleration);
                    result[1] += fuelComsumption;
                    result[2] = currentspeed;
                }

                if (motoresult[0] == -1) {
                    currentspeed--;
                    result[2] = currentspeed;
                    accelerating = false;
                    break;
                }
            }
        }
        //Do calculations when reached no acceleration
        double seglengthaccel = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialvelocity, currentspeed, acceleration);
        double segremaining = (seg.getLength() * 1000) - seglengthaccel;
        double deltatime = segremaining / currentspeed;
        double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        double carpower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);
        result[0] += deltatime;
        double fuelcompsumption = PhysicsCalculus.calcFuelComsumption(motoresult[3], carpower, deltatime);
        result[1] += fuelcompsumption;
        result[2] = currentspeed;

        return result;
    }

    private static double[] calcNoAcceleration(Section section, Segment seg, Vehicle car, double currentspeed, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        //Results
        //0) Time
        //1) Energy
        //2) Velocity
        double breakingresults[] = {0, 0, 0};
        if (isLastSegment) {
            breakingresults = calcBrakingLastSegment(section, seg, car, currentspeed, acceleration);
            //0) Time        
            //1) Distance
            //2) FuelComsumption
        }

        double seglength = seg.getLength() * 1000;
        double deltatime = (seglength - breakingresults[1]) / currentspeed;
        double[] carresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC

        double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
        double fuelComsumption = PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, deltatime);
        result[0] = deltatime + breakingresults[0];
        result[1] += fuelComsumption + breakingresults[2];
        result[2] = currentspeed;
        return result;

    }

    private static double[] calcBrakingLastSegment(Section section, Segment seg, Vehicle car, double initialVelocity, double acceleration) {
        double result[] = {0, 0, 0};
        //0) Time        
        //1) Distance
        //2) FuelComsumption
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
            if (motoresult[0] != -1) {
                double enginepower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);
                double fuelComsumption = PhysicsCalculus.calcFuelComsumption(motoresult[3], enginepower, deltatimeaccel);
                result[2] += fuelComsumption;
            }

            if (motoresult[0] == -1) {
                break;
            }
        }
        result[0] = brakingtime;
        result[1] = brakingspace;
        return result;
    }

    private static double[] calcBrakingSegment(Section section, Segment seg, Vehicle car, double initialvelocity, double maximumvelocity, double acceleration, boolean isLastSegment) {
        double[] result = new double[RESULTS_SIZE];
        double[] tempresults = new double[RESULTS_SIZE];
        double[] carresult = new double[4];
        //Results
        //0) Time (s)
        //1) Energy (g)
        //2) Velocity (m/s)

        //Car result
        //0) Gear
        //1) RPM
        //2) Torque
        //3) SFC
        boolean braking = true;
        double currentspeed = initialvelocity;

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
                    result[0] += 1 / Math.abs(acceleration);
                    double enginepower = PhysicsCalculus.calcEnginePower(carresult[2], carresult[1]);
                    double fuelComsumption = PhysicsCalculus.calcFuelComsumption(carresult[3], enginepower, 1 / Math.abs(acceleration));
                    result[1] += fuelComsumption;
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
        //Do calculations with no acceleration
        double seglengthaccel = PhysicsCalculus.calcDistanceBasedOnInitialVelocityDesiredVelocityAcceleration(initialvelocity, currentspeed, acceleration);
        double segremaining = (seg.getLength() * 1000) - seglengthaccel;
        double deltatime = segremaining / currentspeed;
        double[] motoresult = PhysicsCalculus.calcIdealMotorForceBasedAcceleration(section, seg, car, 0, currentspeed);
        double carpower = PhysicsCalculus.calcEnginePower(motoresult[2], motoresult[1]);

        result[0] += deltatime;
        result[1] += PhysicsCalculus.calcFuelComsumption(motoresult[3], carpower, deltatime);
        result[2] = currentspeed;

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
