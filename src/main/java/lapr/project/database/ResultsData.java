package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Junction;
import lapr.project.model.Project;
import lapr.project.model.Section;
import lapr.project.model.Vehicle;
import lapr.project.networkanalysis.AlgorithmResults;
import oracle.jdbc.OracleTypes;

public class ResultsData extends DataAccess<Map<Vehicle, List<AlgorithmResults>>> {

    public ResultsData(Connection connection) {
        super(connection);
    }

    private boolean exists(List<SQLArgument> args) throws SQLException {
        ResultSet rs = super.callFunction("checkResult", args);

        if(rs.next()) {
            System.out.println("exists");
            return true;
        }
        System.out.println("no exists");
        return false;
    }

    public void insertResults(Project p, Map<Vehicle, List<AlgorithmResults>> mapOfResults) throws SQLException {
        if (connection == null) {
            return;
        }

        List<SQLArgument> args1 = new ArrayList<>();
        for (Vehicle vehicle : mapOfResults.keySet()) {
            for (AlgorithmResults ar : mapOfResults.get(vehicle)) {
                args1.clear();
                args1.add(new SQLArgument(p.getName(), OracleTypes.VARCHAR));
                args1.add(new SQLArgument(vehicle.getName(), OracleTypes.VARCHAR));
                args1.add(new SQLArgument(Double.toString(ar.getCost()), OracleTypes.NUMBER));
                args1.add(new SQLArgument(Double.toString(ar.getTravelTime()), OracleTypes.NUMBER));
                args1.add(new SQLArgument(Double.toString(ar.getEnergy()), OracleTypes.NUMBER));
                args1.add(new SQLArgument(Double.toString(ar.getDistance()), OracleTypes.NUMBER));
                args1.add(new SQLArgument(ar.getAlgorithmType(), OracleTypes.VARCHAR));
                System.out.println("ar: " + args1);

                if (exists(args1)) {
                    continue;
                } else {
                    super.callProcedure("insertResults", args1);

                    List<SQLArgument> args2 = new ArrayList<>();

                    for (Section section : ar.getSectionPath()) {
                        args2.clear();
                        args2.add(new SQLArgument(Integer.toString(section.getSectionID()), OracleTypes.NUMBER));
                        System.out.println(args2);
                        super.callProcedure("insertResultsSection", args2);
                    }

                    for (Junction junction : ar.getJunctionPath()) {
                        args2.clear();
                        args2.add(new SQLArgument(junction.getName(), OracleTypes.VARCHAR));
                        System.out.println(args2);
                        super.callProcedure("insertResultsJunction", args2);
                    }
                }
            }
        }
    }
}
