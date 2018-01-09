package lapr.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            String vName = rs.getString("name");
            String vDescription = rs.getString("description");
            String vType = rs.getString("vtype");
            String vFuel = rs.getString("fuel");
            double vMass = rs.getDouble("mass");
            double vLoad = rs.getDouble("load");
            int vClass = rs.getInt("id_tollclass");
            String vMotorization = rs.getString("motorization");
            double vWheelsize = rs.getDouble("wheel_size");
            double vDragCoefficient = rs.getDouble("drag_coefficient");
            double vFrontalArea = rs.getDouble("frontal_area");
            double vRollingRes = rs.getDouble("rolling_coefficient");
            double vMinRpm = rs.getDouble("minRpm");
            double vMaxRpm = rs.getDouble("maxRpm");
            double vFinalDriveRatio = rs.getDouble("final_Ratio");
            double vEnergyRegenerationRatio = rs.getDouble("energy_regen");

            GearboxData g = new GearboxData(connection);
            Gearbox vGearbox = g.get(vName);

            AcceleratorData a = new AcceleratorData(connection);
            Accelerator vAccelerator = a.get(vName);

            Map<String, Double> mapRoadVelocityLimit = new HashMap<>();
            List<SQLArgument> args1 = new ArrayList<>();
            args1.add(new SQLArgument(vName, OracleTypes.VARCHAR));

            ResultSet rs1 = super.callFunction("checkVehicleSpeedLimits", args1);
            if (rs1.next()) {
                rs1.close();
                ResultSet rs2 = super.callFunction("getMaxVelocityVehicle", args1);
                while (rs2.next()) {
                    String road = rs2.getString("road");
                    String roadP = road.substring(0,1).toUpperCase() + road.substring(1).toLowerCase();
                    double max_speed = rs2.getDouble("speed_limit");
                    mapRoadVelocityLimit.put(roadP, max_speed);
                }
            }

            if ("combustion".equalsIgnoreCase(vMotorization)) {
                VehicleCombustion v = new VehicleCombustion();
                v.setName(vName);
                v.setDescription(vDescription);
                v.setType(vType);
                v.setFuel(vFuel);
                v.setMass(vMass);
                v.setMaxLoad(vLoad);
                v.setVehicleClass(vClass);
                v.setMotorization(vMotorization);
                v.setWheelSize(vWheelsize);
                v.setDragCoefficient(vDragCoefficient);
                v.setFrontalArea(vFrontalArea);
                v.setRCC(vRollingRes);
                v.setMinRPM(vMinRpm);
                v.setMaxRPM(vMaxRpm);
                v.setFinalDriveRatio(vFinalDriveRatio);
                v.setGearbox(vGearbox);
                v.setAccelerator(vAccelerator);
                v.setRoadVelocityLimit(mapRoadVelocityLimit);

                list.addVehicle(v);
            } else if ("electric".equalsIgnoreCase(vMotorization)) {
                VehicleElectric ve = new VehicleElectric();
                ve.setName(vName);
                ve.setDescription(vDescription);
                ve.setType(vType);
                ve.setFuel(vFuel);
                ve.setMass(vMass);
                ve.setMaxLoad(vLoad);
                ve.setVehicleClass(vClass);
                ve.setMotorization(vMotorization);
                ve.setWheelSize(vWheelsize);
                ve.setDragCoefficient(vDragCoefficient);
                ve.setFrontalArea(vFrontalArea);
                ve.setRCC(vRollingRes);
                ve.setMinRPM(vMinRpm);
                ve.setMaxRPM(vMaxRpm);
                ve.setFinalDriveRatio(vFinalDriveRatio);
                ve.setGearbox(vGearbox);
                ve.setAccelerator(vAccelerator);
                ve.setRoadVelocityLimit(mapRoadVelocityLimit);
                ve.setEnergyRegenerationRatio(vEnergyRegenerationRatio);

                list.addVehicle(ve);
            }

        }

        return list;
    }

    public void insert(String pName, Vehicle v) throws SQLException {
        List<SQLArgument> args = new ArrayList<>();

        args.add(new SQLArgument(v.getName(), OracleTypes.VARCHAR));
        ResultSet rs = super.callFunction("getVehicleByName", args);
        if (rs.next()) {
            rs.close();
            return;
        }

        args.clear();
        args.add(new SQLArgument(pName, OracleTypes.VARCHAR));
        args.add(new SQLArgument(v.getName(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(v.getDescription(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(v.getType(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(v.getFuel(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(v.getMotorization(), OracleTypes.VARCHAR));
        args.add(new SQLArgument(Integer.toString(v.getVehicleClass()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getWheelSize()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getMass()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getMaxLoad()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getDragCoefficient()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getFrontalArea()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getRCC()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getMinRpm()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getMaxRpm()), OracleTypes.NUMBER));
        args.add(new SQLArgument(Double.toString(v.getFinalDriveRatio()), OracleTypes.NUMBER));
        if (v instanceof VehicleElectric) {
            args.add(new SQLArgument(Double.toString(((VehicleElectric) v).getEnergyRegenerationRatio()), OracleTypes.NUMBER));
        } else {
            args.add(new SQLArgument("0", OracleTypes.NUMBER));
        }

        super.callProcedure("insertVehicle", args);

        List<SQLArgument> args1 = new ArrayList<>();
        for (Map.Entry<String, Double> entry : v.getMapRoadVelocityLimit().entrySet()) {
            args1.clear();
            args1.add(new SQLArgument(v.getName(), OracleTypes.VARCHAR));
            args1.add(new SQLArgument(entry.getKey(), OracleTypes.VARCHAR));
            args1.add(new SQLArgument(Double.toString(entry.getValue()), OracleTypes.NUMBER));
            super.callProcedure("insertMaxVelocityVehicle", args1);
        }

        GearboxData gd = new GearboxData(connection);
        gd.insert(v.getName(), v.getGearbox());

        AcceleratorData ad = new AcceleratorData(connection);
        ad.insert(v.getName(), v.getAccelerator());

    }

}
