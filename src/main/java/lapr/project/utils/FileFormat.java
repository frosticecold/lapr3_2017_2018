package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import lapr.project.model.*;
import lapr.project.utils.graphbase.Graph;

public interface FileFormat {

    public List<Vehicle> importVehicles(File file) throws FileNotFoundException, ImportException;

    public RoadNetwork importNetwork(File file) throws FileNotFoundException, ImportException;

}
