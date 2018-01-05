package lapr.project.utils;

public class DatabaseConnection {

    public SQLConnection getDatabase() {
        return new SQLConnection("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_G19", "conan");
    }

    public SQLConnection loginDatabase(String username, String password, String url) {
        if (url.equalsIgnoreCase("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl") && username.equalsIgnoreCase("LAPR3_G19") && password.equalsIgnoreCase("conan")) {
            return new SQLConnection(username, password, url);
        }else{
            throw new IllegalArgumentException("Wrong username, password or url inserted!");
        }
    }

}
