/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.pathalgorithms;

import java.util.LinkedList;

import lapr.project.model.Junction;
import lapr.project.model.PhysicsCalculus;
import static lapr.project.model.PhysicsCalculus.GEAR_VEC;
import static lapr.project.model.PhysicsCalculus.RPM_VEC;
import static lapr.project.model.PhysicsCalculus.THROTTLE_VEC;
import static lapr.project.model.PhysicsCalculus.TORQUE_VEC;
import static lapr.project.model.PhysicsCalculus.calcVelocityBasedOnRPMandGear;
import lapr.project.model.Section;
import lapr.project.model.Segment;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import lapr.project.utils.Session;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;

public class FastestPathAlgorithm implements PathAlgorithm {

    private static final int MAX_THROTTLE = 0;
    private static final int MED_THROTTLE = 1;
    private static final int MIN_THROTTLE = 2;

    @Override
    public AlgorithmResults bestPath(Graph<Junction, Section> graph, Junction start, Junction end, Vehicle v, double acceleration) {

        LinkedList<Junction> path = new LinkedList<>();
        LinkedList<Section> sectionpath = new LinkedList<>();
        double results[] = shortestPath(graph, start, end, v, path, sectionpath);
        AlgorithmResults alg = new AlgorithmResults(Session.getActiveProject(), path, sectionpath, v, results, this.toString());
        alg.calculate();

        return alg;
    }

    private static double[] shortestPath(Graph<Junction, Section> graph, Junction vOrig, Junction vDest, Vehicle vehicle, LinkedList<Junction> shortPath, LinkedList<Section> sectionpath) {
        double results[] = {-1, -1};//Results 0)Time 1) Energy
        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return results;
        }
        boolean[] visited = new boolean[graph.numVertices()];
        int[] pathKeys = new int[graph.numVertices()];
        double[] time = new double[graph.numVertices()];
        double[] energy = new double[graph.numVertices()];
        Junction[] vertices = graph.allkeyVerts();
        shortPath.clear();
        sectionpath.clear();

        for (Junction v : graph.vertices()) {
            time[graph.getKey(v)] = Double.MAX_VALUE;
            pathKeys[graph.getKey(v)] = -1;
            visited[graph.getKey(v)] = false;
        }

        shortestPathLength(graph, vOrig, vertices, visited, pathKeys, time, energy, vehicle);

        //double timePath = time[graph.getKey(vDest)];
        results[0] = time[graph.getKey(vDest)];
        results[1] = energy[graph.getKey(vDest)];

        if (Double.MAX_VALUE - results[0] > 0) {
            getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath, sectionpath);
            return results;
        }
        return results;
    }

    private static <V, E> void shortestPathLength(Graph<Junction, Section> g, Junction vOrig, Junction[] vertices,
            boolean[] visited, int[] pathKeys, double[] time, double[] energy, Vehicle vehicle) {

        Section section = new Section();
        int i = g.getKey(vOrig);
        time[i] = 0;
        Junction vOrigin;
        while (i != -1) {

            vOrigin = vertices[i];
            int kOrg = g.getKey(vOrigin);
            visited[kOrg] = true;

            for (Edge<Junction, Section> edg : g.outgoingEdges(vOrigin)) {

                Junction vAdj = g.opposite(vOrigin, edg);
                int kAdj = g.getKey(vAdj);
                section = edg.getElement();
                //Results
                //0) Time
                //1) Work
                double results[] = calcFastestTime(section, vehicle);
                if (!visited[kAdj] && time[kAdj] > (time[kOrg] + results[0])) {
                    time[kAdj] = time[kOrg] + results[0];
                    pathKeys[kAdj] = kOrg;
                    energy[kAdj] = energy[kAdj] + results[1];
                    //velocity[kAjd] = result[1];
                }
            }
            i = getVertMinDist(time, visited);
        }
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

    private static double[] calcFastestTime(Section section, Vehicle car) {

        //Vector with results
        //0) Time
        //1) Work
        double results[] = new double[2];
        double final_time = 0;
        double energy = 0;

        for (Segment segment : section.getSequenceOfSegments()) {
            double neededForce = PhysicsCalculus.calcForceInSegment(segment, car, section);
            double carVelRelativeToAir = PhysicsCalculus.calcMaximumVelocity(segment, car, section); // Function returns velocity in m/s
            double carVelRelativeToGround = car.getMaximumPermitedVelocity(section.getTypology()) / 3.6; //Function velocity is in KM/H,divide by 3.6 to get m/s
            double time_segment = (segment.getLength() * 1000) / (carVelRelativeToGround);
            double[] ideal_motor_force = calculateIdealMotorForce(car, segment, neededForce, carVelRelativeToAir);
            double power_generated = PhysicsCalculus.calcEnginePower(ideal_motor_force[PhysicsCalculus.TORQUE_VEC], ideal_motor_force[PhysicsCalculus.RPM_VEC]);
            energy += (power_generated * time_segment);
            final_time += time_segment;
        }
        results[0] = final_time;
        results[1] = energy;

        return results;

    }

    public String toString() {
        return "Fastest Path";
    }

    private static double[] calculateIdealMotorForce(Vehicle car, Segment s, final double neededForce, final double carvelocity) {
        if (car == null || s == null ) {
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
                    } //else {
//                        if (carvelocity > max_velocity) {
//                            break;
//                        }

                    //}
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
