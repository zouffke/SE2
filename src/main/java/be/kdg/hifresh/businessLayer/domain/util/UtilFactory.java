package be.kdg.hifresh.businessLayer.domain.util;

import java.time.LocalDate;

/**
 * Factory class for creating instances of utility classes.
 * This class cannot be instantiated.
 */
public class UtilFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private UtilFactory() {
    }

    /**
     * Creates a new instance of Periode.
     *
     * @param start The start date of the period.
     * @param end   The end date of the period.
     * @return A new instance of Periode.
     */
    public static Periode createPeriod(LocalDate start, LocalDate end) {
        return new Periode(start, end);
    }

    /**
     * Creates a new instance of PrijsAfspraak.
     *
     * @param prijs          The price of the product.
     * @param maxHoeveelheid The maximum quantity of the product for which this price agreement applies.
     * @return A new instance of PrijsAfspraak.
     */
    public static PrijsAfspraak createPrijsAfspraak(Munt prijs, double maxHoeveelheid) {
        return new PrijsAfspraak(prijs, maxHoeveelheid);
    }

    /**
     * Creates a new instance of Munt.
     *
     * @param bedrag  The amount of money.
     * @param eenheid The unit of the currency.
     * @return A new instance of Munt.
     */
    public static Munt createMunt(double bedrag, String eenheid) {
        return new Munt(bedrag, eenheid);
    }
}