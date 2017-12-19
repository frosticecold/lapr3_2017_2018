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

public class VehicleData extends DataAccess<Vehicle>{
    
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
        while(rs.next()) {
            String v_name = rs.getString("v_name");
            String v_description = rs.getString("v_description");
            String v_type = rs.getString("v_type");
            String v_fuel = rs.getString("v_fuel");
            int v_class = rs.getInt("v_class");
            String v_motorization = rs.getString("v_motorization");
            double v_wheelsize = rs.getDouble("v_wheelSize");
            double v_drag_coefficient = rs.getDouble("v_dragCoefficient");
            double v_frontalArea = rs.getDouble("v_frontalArea");
            double v_rollingRes = rs.getDouble("v_rollingResistanceCoef");
            double v_minRpm = rs.getDouble("v_minRpm");
            double v_maxRpm = rs.getDouble("v_maxRpm");
            double v_finalDriveRatio = rs.getDouble("v_finalDriveRatio");
            double v_energyRegenerationRatio = rs.getDouble("v_energyRegenerationRatio");
            
            GearboxData g = new GearboxData(connection);
            Gearbox v_gearbox = g.get(v_name);
            
            AcceleratorData a = new AcceleratorData(connection);
            Accelerator v_accelerator = a.get(v_name);
            
            switch(v_motorization) {
                case "combustion":
                    VehicleCombustion v = new VehicleCombustion();
                    v.setName(v_name);
                    v.setDescription(v_description);
                    v.setType(v_type);
                    v.setFuel(v_fuel);
                    v.setVehicleClass(v_class);
                    v.setMotorization(v_motorization);
                    v.setWheelSize(v_wheelsize);
                    v.setDragCoefficient(v_drag_coefficient);
                    v.setFrontalArea(v_frontalArea);
                    v.setRcc(v_rollingRes);
                    v.setMinRpm(v_minRpm);
                    v.setMaxRpm(v_maxRpm);
                    v.setFinalDriveRatio(v_finalDriveRatio);
                    v.setGearbox(v_gearbox);
                    v.setAccelerator(v_accelerator);
                    
                    list.addVehicle(v);
                    break;
                    
                case "electric":
                    VehicleElectric ve = new VehicleElectric();
                    ve.setName(v_name);
                    ve.setDescription(v_description);
                    ve.setType(v_type);
                    ve.setFuel(v_fuel);
                    ve.setVehicleClass(v_class);
                    ve.setMotorization(v_motorization);
                    ve.setWheelSize(v_wheelsize);
                    ve.setDragCoefficient(v_drag_coefficient);
                    ve.setFrontalArea(v_frontalArea);
                    ve.setRcc(v_rollingRes);
                    ve.setMinRpm(v_minRpm);
                    ve.setMaxRpm(v_maxRpm);
                    ve.setFinalDriveRatio(v_finalDriveRatio);
                    ve.setGearbox(v_gearbox);
                    ve.setAccelerator(v_accelerator);
                    ve.setEnergyRegenerationRatio(v_energyRegenerationRatio);
                    
                    list.addVehicle(ve);
                    break;
            }            
        }
        
        return list;
    }
    
    

}
