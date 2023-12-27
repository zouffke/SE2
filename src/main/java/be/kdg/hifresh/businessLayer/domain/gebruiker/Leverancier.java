package be.kdg.hifresh.businessLayer.domain.gebruiker;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a supplier in the system.
 * It extends the Role class and contains a list of contracts associated with the supplier.
 */
public class Leverancier extends Rol {
    /**
     * List of contracts associated with the supplier.
     */
    private final List<Contract> CONTRACTS;

    /**
     * Unique identifier for the supplier.
     */
    @Getter
    private final int ID;

    /**
     * Name of the supplier.
     */
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final String NAME;

    /**
     * Reputation of the supplier.
     */
    @SuppressWarnings("unused")
    private int reputatie;

    /**
     * Constructor for creating a new supplier.
     *
     * @param id   the id of the new supplier
     * @param name the name of the new supplier
     */
    Leverancier(int id, String name) {
        this.ID = id;
        this.NAME = name;
        this.CONTRACTS = new ArrayList<>();
    }

    /**
     * Adds a contract to the supplier's list of contracts.
     *
     * @param contract the contract to be added
     */
    public void addContract(Contract contract) {
        this.CONTRACTS.add(contract);
    }
}