package be.kdg.hifresh.businessLayer.domain.util;

import lombok.Getter;

/**
 * Represents a currency amount.
 * This class implements the Comparable interface to allow for comparison between different instances of Munt.
 */
@SuppressWarnings("unused")
public class Munt implements Comparable<Munt> {

    /**
     * The unit of the currency (e.g., Euro, Dollar).
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final String EENHEID;
    /**
     * The amount of money.
     */
    @Getter
    private final double BEDRAG;

    /**
     * Constructor for Munt.
     *
     * @param bedrag  The amount of money.
     * @param eenheid The unit of the currency.
     */
    Munt(double bedrag, String eenheid) {
        this.BEDRAG = bedrag;
        this.EENHEID = eenheid;
    }

    /**
     * Compares this Munt to another Munt.
     * The comparison is based on the amount of money.
     *
     * @param o The Munt to be compared.
     * @return A negative integer, zero, or a positive integer as this Munt is less than, equal to, or greater than the specified Munt.
     */
    @Override
    public int compareTo(Munt o) {
        return Double.compare(this.BEDRAG, o.BEDRAG);
    }
}