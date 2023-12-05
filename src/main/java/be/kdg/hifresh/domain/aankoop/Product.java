package be.kdg.hifresh.domain.aankoop;

import lombok.Getter;

import java.util.*;

/**
 * Represents a product that is delivered by a supplier and is needed for preparing a meal.
 */
public class Product {

    //region vars
    /**
     * The list of contracts associated with this product.
     */
    private List<Contract> contracten;

    /**
     * The name of this product.
     */
    private String naam;

    /**
     * The id of this product.
     */
    @Getter
    private final int id;

    //endregion
    //region constructors
    /**
     * Constructor for Product.
     *
     * @param naam The name of the product.
     * @param id The id of the product.
     */
    Product(String naam, int id){
        this.naam = naam;
        this.id = id;
    }
    //endregion
}