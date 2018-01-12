package lapr.project.model;

public final class Constants {

    private Constants() {
        // Throw an exception if this ever *is* called
        throw new AssertionError("Instantiating utility class.");
    }

    public static final float GRAVITY = 9.80665f;
    public static final float FUEL_GASOLINE = 44.4f;
    public static final float FUEL_DIESEL = 48f;
    public static final String HTML_END_OF_LINE = "</li>\n";
    /**
     * // refered in
     * http://www.world-nuclear.org/information-library/facts-and-figures/heat-values-of-various-fuels.aspx
     */
    public static final double GASOLINE_ENERGY_PER_LITRE = 32;
    /**
     * // refered in
     * http://www.world-nuclear.org/information-library/facts-and-figures/heat-values-of-various-fuels.aspx
     */
    public static final double DIESEL_ENERGY_PER_LITRE = 39;

    /**
     * Density of Gasoline in Kg/l
     * <p>
     * https://en.wikipedia.org/wiki/Gasoline#Density
     */
    public static final double DENSITY_GASOLINE = 0.755;

    /**
     * Density of Diesel in Kg/l
     * <p>
     * https://en.wikipedia.org/wiki/Diesel_fuel#Fuel_value_and_price
     *
     */
    public static final double DENSITY_DIESEL = 0.832;
}
