package lapr.project.utils;

public class ImportException extends Exception {

    private static final long serialVersionUID = 1;

    public ImportException(Exception ex) {
        super(ex.getMessage(), ex.getCause());
        setStackTrace(ex.getStackTrace());
    }

}
