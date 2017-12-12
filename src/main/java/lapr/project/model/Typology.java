package lapr.project.model;

public enum Typology {
    REGULAR_ROAD("REGULAR_ROAD"),
    URBAN_ROAD("URBAN_ROAD"),
    EXPRESS_ROAD("EXPRESS_ROAD"),
    CONTROLLED_ACCESS_HIGHWAY("CONTROLLED_ACCESS_HIGHWAY");

    private String m_name;

    /**
     *
     * @param name
     */
    Typology(String name) {
        m_name = name;
    }

}
