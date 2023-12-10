package be.kdg.hifresh.businessLayer.util;

import lombok.Getter;

/**
 * Represents a currency amount.
 * This class implements the Comparable interface to allow for comparison between different instances of Munt.
 */
public class Munt implements Comparable<Munt>{

    /**
     * The amount of money.
     */
    @Getter
    private double bedrag;

    /**
     * The unit of the currency (e.g., Euro, Dollar).
     */
    private String eenheid;

    /**
     * Constructor for Munt.
     *
     * @param bedrag The amount of money.
     * @param eenheid The unit of the currency.
     */
    Munt(double bedrag, String eenheid) {
        this.bedrag = bedrag;
        this.eenheid = eenheid;
    }

    /**
     * Adds a specified amount to the current amount of money.
     *
     * @param bedrag The amount to be added.
     */
    public void addBedrag(double bedrag) {
        this.bedrag += bedrag;
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
        return Double.compare(this.bedrag, o.bedrag);
    }
}