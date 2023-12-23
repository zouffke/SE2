package be.kdg.hifresh.businessLayer.util;

import lombok.Getter;

/**
 * Represents a price agreement for a certain quantity of a product.
 */
@Getter
public class PrijsAfspraak {

    /**
     * The price of the product.
     */
    private final Munt PRIJS;

    /**
     * The maximum quantity of the product for which this price agreement applies.
     */
    private final double MAX_HOEVEELHEID;

    /**
     * Constructor for PrijsAfspraak.
     *
     * @param prijs          The price of the product.
     * @param maxHoeveelheid The maximum quantity of the product for which this price agreement applies.
     */
    PrijsAfspraak(Munt prijs, double maxHoeveelheid) {
        this.PRIJS = prijs;
        this.MAX_HOEVEELHEID = maxHoeveelheid;
    }
}