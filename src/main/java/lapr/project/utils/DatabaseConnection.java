package lapr.project.utils;

public class DatabaseConnection {

    public SQLConnection getDatabase() {
        return new SQLConnection("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G19", "conan");
    }

}
