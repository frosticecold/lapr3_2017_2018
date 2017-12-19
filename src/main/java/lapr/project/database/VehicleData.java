package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Accelerator;
import lapr.project.model.Gearbox;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCombustion;
import lapr.project.model.VehicleElectric;
import lapr.project.model.VehicleList;
import oracle.jdbc.OracleTypes;

public class VehicleData extends DataAccess<Vehicle> {

    public VehicleData(Connection connection) {
        super(connection);
    }

    public VehicleList get(String name) throws SQLException {
        if (connection == null) {
            return null;
        }
        VehicleList list = new VehicleList();
        List<SQLArgument> args = new ArrayList<>();
        args.add(new SQLArgument(name, OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getVehicle", args);
        while (rs.next()) {
            String vName = rs.getString("v_name");
            String vDescription = rs.getString("v_description");
            String vType = rs.getString("v_type");
            String vFuel = rs.getString("v_fuel");
            int vClass = rs.getInt("v_class");
            String vMotorization = rs.getString("v_motorization");
            double vWheelsize = rs.getDouble("v_wheelSize");
            double vDragCoefficient = rs.getDouble("v_dragCoefficient");
            double vFrontalArea = rs.getDouble("v_frontalArea");
            double vRollingRes = rs.getDouble("v_rollingResistanceCoef");
            double vMinRpm = rs.getDouble("v_minRpm");
            double vMaxRpm = rs.getDouble("v_maxRpm");
            double vFinalDriveRatio = rs.getDouble("v_finalDriveRatio");
            double vEnergyRegenerationRatio = rs.getDouble("v_energyRegenerationRatio");

            GearboxData g = new GearboxData(connection);
            Gearbox vGearbox = g.get(vName);

            AcceleratorData a = new AcceleratorData(connection);
            Accelerator vAccelerator = a.get(vName);

            if (vMotorization.equalsIgnoreCase("combustion")) {
                VehicleCombustion v = new VehicleCombustion();
                v.setName(vName);
                v.setDescription(vDescription);
                v.setType(vType);
                v.setFuel(vFuel);
                v.setVehicleClass(vClass);
                v.setMotorization(vMotorization);
                v.setWheelSize(vWheelsize);
                v.setDragCoefficient(vDragCoefficient);
                v.setFrontalArea(vFrontalArea);
                v.setRcc(vRollingRes);
                v.setMinRpm(vMinRpm);
                v.setMaxRpm(vMaxRpm);
                v.setFinalDriveRatio(vFinalDriveRatio);
                v.setGearbox(vGearbox);
                v.setAccelerator(vAccelerator);

                list.addVehicle(v);
            } else if (vMotorization.equalsIgnoreCase("electric")) {
                VehicleElectric ve = new VehicleElectric();
                ve.setName(vName);
                ve.setDescription(vDescription);
                ve.setType(vType);
                ve.setFuel(vFuel);
                ve.setVehicleClass(vClass);
                ve.setMotorization(vMotorization);
                ve.setWheelSize(vWheelsize);
                ve.setDragCoefficient(vDragCoefficient);
                ve.setFrontalArea(vFrontalArea);
                ve.setRcc(vRollingRes);
                ve.setMinRpm(vMinRpm);
                ve.setMaxRpm(vMaxRpm);
                ve.setFinalDriveRatio(vFinalDriveRatio);
                ve.setGearbox(vGearbox);
                ve.setAccelerator(vAccelerator);
                ve.setEnergyRegenerationRatio(vEnergyRegenerationRatio);

                list.addVehicle(ve);
            }

        }

        return list;
    }

}
