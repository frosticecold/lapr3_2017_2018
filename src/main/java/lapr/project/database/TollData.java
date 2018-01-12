package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pc asus
 */
public class TollData extends DataAccess<Double> {

    /**
     *
     * @param connection
     */
    public TollData(Connection connection) {
        super(connection);
    }

    /**
     *
     * @param pName
     * @param tollClass
     * @param sectionID
     * @param price
     * @throws SQLException
     */
    public void insert(String pName, int tollClass, int sectionID, double price) throws SQLException {
        List<SQLArgument> args1 = new ArrayList<>();

        args1.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args1.add(new SQLArgument(Integer.toString(tollClass), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Integer.toString(sectionID), OracleTypes.NUMBER));
        args1.add(new SQLArgument(Double.toString(price), OracleTypes.NUMBER));
        super.callProcedure("insertToll", args1);
    }

    public Map<Integer, Double> getRoadToll(int id) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(Integer.toString(id), OracleTypes.VARCHAR));

        ResultSet rs = super.callFunction("getRoadToll", args);
        return getToMapDatabase(rs);
    }

    public Map<Integer, Double> getSectionToll(int id) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(Integer.toString(id), OracleTypes.VARCHAR));

        ResultSet rs = super.callFunction("getSectionToll", args);
        return getToMapDatabase(rs);

    }

    private static Map<Integer, Double> getToMapDatabase(ResultSet rs) throws SQLException {
        Map<Integer, Double> map = new HashMap<>();
        if (!rs.next()) {
            return map;
        } else {
            rs.previous();
            while (rs.next()) {
                int tClass = rs.getInt("TOLL_CLASS");
                double price = rs.getDouble("PRICE");
                map.put(tClass, price);
            }
        }
        return map;
    }

}
