package lapr.project.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

}
