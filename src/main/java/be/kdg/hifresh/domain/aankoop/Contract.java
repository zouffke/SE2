package be.kdg.hifresh.domain.aankoop;

import be.kdg.hifresh.domain.gebruiker.*;
import lombok.Getter;

import java.util.*;

/**
 * Represents a contract between HiFresh and a supplier for the delivery of products.
 */
public class Contract {
    //region vars

    /**
     * The supplier associated with this contract.
     */
    private Leverancier leverancier;

    /**
     * The product associated with this contract.
     */
    @Getter
    private Product product;

    /**
     * The list of clauses associated with this contract.
     */
    private List<Clausule> clausules;
    //endregion

    //region constructors
    /**
     * Constructor for Contract.
     *
     * @param product The product associated with the contract.
     */
    Contract(Product product){
        this.product = product;
    }
    //endregion

    /**
     * Adds a clause to the list of clauses associated with this contract.
     *
     * @param clausule The clause to be added.
     * @return true if the clause was added successfully, false otherwise.
     */
    public boolean addClausule(Clausule clausule){
        if (this.clausules == null){
            this.clausules = new ArrayList<>();
        }

        return this.clausules.add(clausule);
    }

}