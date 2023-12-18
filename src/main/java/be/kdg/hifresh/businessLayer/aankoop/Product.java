package be.kdg.hifresh.businessLayer.aankoop;

import lombok.Getter;

import java.util.*;

/**
 * Represents a product that is delivered by a supplier and is needed for preparing a meal.
 */
@Getter
public class Product {

    //region vars

    /**
     * The list of contracts associated with this product.
     */
    private final List<Contract> contracten;

    /**
     * The name of this product.
     */
    private String name;

    /**
     * The id of this product.
     */
    private final int id;

    //endregion

    //region constructors

    /**
     * Constructor for Product.
     *
     * @param name The name of the product.
     * @param id The id of the product.
     */
    Product(String name, int id){
        this.name = name;
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