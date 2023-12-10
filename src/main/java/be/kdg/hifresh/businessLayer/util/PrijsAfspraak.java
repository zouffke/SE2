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
    private Munt prijs;

    /**
     * The maximum quantity of the product for which this price agreement applies.
     */
    private double maxHoeveelheid;

    /**
     * Constructor for PrijsAfspraak.
     *
     * @param prijs The price of the product.
     * @param maxHoeveelheid The maximum quantity of the product for which this price agreement applies.
     */
    PrijsAfspraak(Munt prijs, double maxHoeveelheid){
        this.prijs = prijs;
        this.maxHoeveelheid = maxHoeveelheid;
    }
}