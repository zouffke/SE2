package be.kdg.hifresh.businessLayer.domain.recepten;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import lombok.Getter;

/**
 * Represents an ingredient needed for preparing a dish.
 */
@Getter
public class Ingredient {
    //region vars

    /**
     * The product associated with this ingredient.
     */
    private final Product PRODUCT;

    /**
     * The quantity of this ingredient.
     */
    @Getter
    private final double HOEVEELHEID;

    /**
     * The unit of this ingredient.
     */
    private final Eenheid EENHEID;

    /**
     * The id of this ingredient.
     */
    private final int ID;

    private final int RECEPT_ID;
    //endregion
    //region constructors

    /**
     * Constructor for Ingredient.
     *
     * @param id          The id of the ingredient.
     * @param product     The product associated with the ingredient.
     * @param hoeveelheid The quantity of the ingredient.
     */
    Ingredient(int id, Product product, double hoeveelheid, Eenheid eenheid, int receptId) {
        this.ID = id;
        this.PRODUCT = product;
        this.HOEVEELHEID = hoeveelheid;
        this.EENHEID = eenheid;
        this.RECEPT_ID = receptId;
    }
    //endregion
}