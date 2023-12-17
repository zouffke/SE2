package be.kdg.hifresh.businessLayer.recepten;

import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.util.Eenheid;
import lombok.Getter;

/**
 * Represents an ingredient needed for preparing a dish.
 */
public class Ingredient {
    //region vars

    /**
     * The product associated with this ingredient.
     */
    @Getter
    private final Product product;

    /**
     * The quantity of this ingredient.
     */
    @Getter
    private double hoeveelheid;

    /**
     * The unit of this ingredient.
     */
    private Eenheid eenheid;

    /**
     * The id of this ingredient.
     */
    @Getter
    private final int id;
    //endregion
    //region constructors
    /**
     * Constructor for Ingredient.
     *
     * @param id The id of the ingredient.
     * @param product The product associated with the ingredient.
     * @param hoeveelheid The quantity of the ingredient.
     */
    Ingredient(int id, Product product, double hoeveelheid, Eenheid eenheid) {
        this.id = id;
        this.product = product;
        this.hoeveelheid = hoeveelheid;
        this.eenheid = eenheid;
    }
    //endregion
}