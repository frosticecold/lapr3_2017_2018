package lapr.project.database;

/**
 *
 * @author pc asus
 */
public class SQLArgument {

    private String value;
    private int type;

    /**
     *
     * @param value
     * @param type
     */
    protected SQLArgument(String value, int type) {
        this.value = value;
        this.type = type;
    }

    /**
     *
     * @return
     */
    protected String getValue() {
        return this.value;
    }

    /**
     *
     * @return
     */
    protected int getType() {
        return this.type;
    }

    /**
     *
     * @param value
     */
    protected void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @param type
     */
    protected void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
