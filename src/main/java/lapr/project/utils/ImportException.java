package lapr.project.utils;

public class ImportException extends Exception {

    private static final long serialVersionUID = 1;
    private static final String ERROR_MSG = "There was an error importing";

    public ImportException(Exception ex) {
        super(ex.getMessage(), ex.getCause());
        setStackTrace(ex.getStackTrace());
    }

    public ImportException() {
        super(ERROR_MSG);
    }

}
