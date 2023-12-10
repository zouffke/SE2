package be.kdg.hifresh.businessLayer.aankoop;

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
    @Getter
    private final List<Contract> contracten;

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
        this.contracten = new ArrayList<>();
    }

    //endregion

    /**
     * Adds a contract to the list of contracts associated with this product.
     *
     * @param contract The contract to be added.
     */
    public void addContract(Contract contract){
        this.contracten.add(contract);
    }
}