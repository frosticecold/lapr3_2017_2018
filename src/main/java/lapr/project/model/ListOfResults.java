/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lapr.project.networkanalysis.AlgorithmResults;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ListOfResults {

    private Map<Vehicle, List<AlgorithmResults>> mapOfResults;

    public ListOfResults() {
        mapOfResults = new LinkedHashMap<>();
    }   

    public boolean addResult(Vehicle v, AlgorithmResults result) {
        boolean added = false;
        if (mapOfResults.containsKey(v)) {
            if(!mapOfResults.get(v).contains(result)){
            added = mapOfResults.get(v).add(result);
            }
        } else {
            ArrayList<AlgorithmResults> list = new ArrayList<>();
            list.add(result);
            mapOfResults.put(v, list);
            added = true;
        }
        return added;
    }

    public Map<Vehicle, List<AlgorithmResults>> getMapOfResults() {
        return mapOfResults;
    }

    public Iterator<Vehicle> vehicleIterator() {
        return mapOfResults.keySet().iterator();
    }

    public Iterable<AlgorithmResults> getListOfResults(Vehicle car) {
        if (mapOfResults.containsKey(car)) {
            return mapOfResults.get(car);
        } else {
            return new ArrayList<>();
        }

    }

    public void clearList() {
        mapOfResults.clear();
    }

}
