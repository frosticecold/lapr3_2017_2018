package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lapr.project.model.Junction;
import lapr.project.model.ListOfResults;
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

        return rs.next();
    }

    public ListOfResults get(Project p, String pName) throws SQLException {
        if (connection == null) {
            return new ListOfResults();
        }
        ListOfResults list = new ListOfResults();

        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        try (ResultSet rs = super.callFunction("getResultados", args)) {
            while (rs.next()) {
                int idRes = rs.getInt("ID_RESULTADOS");
                String vName = rs.getString("NAME");
                double cost = rs.getDouble("COST");
                double travelTime = rs.getDouble("TRAVEL_TIME");
                double energy = rs.getDouble("ENERGY");
                double distance = rs.getDouble("DISTANCE");
                String algorithm = rs.getString("ALGORITHM");

                Vehicle v = p.getListVehicles().getVehicleByName(vName);

                LinkedList<Junction> junctionPath = new LinkedList<>();

                List<SQLArgument> argsR = new ArrayList<>();
                argsR.add(new SQLArgument(Integer.toString(idRes), OracleTypes.NUMBER));
                ResultSet rs1 = super.callFunction("getResultadosJunction", argsR);
                while (rs1.next()) {
                    String junc = rs1.getString("NAME");

                    junctionPath.add(p.getJunction(junc));
                }

                LinkedList<Section> fastestPath = new LinkedList<>();

                ResultSet rs2 = super.callFunction("getResultadosSection", argsR);
                while (rs2.next()) {
                    Junction j1 = p.getJunction(rs2.getString("bJUNC"));
                    Junction j2 = p.getJunction(rs2.getString("eJUNC"));

                    fastestPath.add(p.getSection(j1, j2));
                }

                double[] results = {travelTime, energy};

                AlgorithmResults ar = new AlgorithmResults(p, junctionPath, fastestPath, v, results, algorithm);
                ar.setCost(cost);
                ar.setDistance(distance);

                list.addResult(v, ar);
            }
        }

        return list;
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

                if (!exists(args1)) {
                    super.callProcedure("insertResults", args1);

                    insertResultsSection(p.getName(), ar.getSectionPath());

                    insertResultsJunction(p.getName(), ar.getJunctionPath());
                }
            }
        }
    }

    private void insertResultsSection(String pName, LinkedList<Section> list) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        for (Section section : list) {
            args.clear();
            args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(Integer.toString(section.getSectionID()), OracleTypes.NUMBER));
            super.callProcedure("insertResultsSection", args);
        }
    }

    private void insertResultsJunction(String pName, LinkedList<Junction> list) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();
        for (Junction junction : list) {
            args.clear();
            args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
            args.add(new SQLArgument(junction.getName(), OracleTypes.VARCHAR));
            super.callProcedure("insertResultsJunction", args);
        }
    }
}
