package lapr.project.database;

public class SQLArgument {

    private String value;
    private int type;

    protected SQLArgument(String value, int type) {
        this.value = value;
        this.type = type;
    }

    protected String getValue() {
        return this.value;
    }

    protected int getType() {
        return this.type;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
