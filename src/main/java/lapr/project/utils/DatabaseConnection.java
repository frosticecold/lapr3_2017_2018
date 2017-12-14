package lapr.project.utils;

public class DatabaseConnection {

    public SQLConnection getDatabase() {
        return new SQLConnection("vsrvbd1.dei.isep.ipp.pt", "LAPR3_G19", "conan");
    }  

}
