package be.kdg.hifresh.businessLayer.aankoop;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product that is delivered by a supplier and is needed for preparing a meal.
 */
@Getter
public class Product {

    //region vars

    /**
     * The list of contracts associated with this product.
     */
    private final List<Contract> CONTRACTEN;

    /**
     * The name of this product.
     */
    private final String NAME;

    /**
     * The id of this product.
     */
    private final int ID;

    //endregion

    //region constructors

    /**
     * Constructor for Product.
     *
     * @param name The name of the product.
     * @param id   The id of the product.
     */
    Product(String name, int id) {
        this.NAME = name;
        this.ID = id;
        this.CONTRACTEN = new ArrayList<>();
    }

    //endregion

    /**
     * Adds a contract to the list of contracts associated with this product.
     *
     * @param contract The contract to be added.
     */
    public void addContract(Contract contract) {
        this.CONTRACTEN.add(contract);
    }
}