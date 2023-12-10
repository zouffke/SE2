package be.kdg.hifresh.businessLayer.aankoop;

import java.time.LocalDate;
import java.util.*;
import be.kdg.hifresh.businessLayer.util.*;
import lombok.Getter;

/**
 * Represents a clause in a contract.
 */
@Getter
public class Clausule {
    //region vars

    /**
     * The contract associated with this clause.
     */
    private Contract contract;

    /**
     * The list of deliveries associated with this clause.
     */
    private List<Levering> leveringen;

    /**
     * The list of purchase orders associated with this clause.
     */
    private List<InkoopOrder> inkoopOrders;

    /**
     * The period of this clause.
     */
    private Periode periode;

    /**
     * The unit of this clause.
     */
    private Eenheid eenheid;

    /**
     * The price agreement of this clause.
     */
    private PrijsAfspraak prijsAfspraak;

    /**
     * The id of this clause.
     */
    private final int id;

    /**
     * The quantity of this clause.
     */
    private double hoeveelheid;
    //endregion

    //region contstructors
    /**
     * Constructor for Clausule.
     *
     * @param id The id of the clause.
     * @param contract The contract associated with the clause.
     * @param periode The period of the clause.
     * @param hoeveelheid The quantity of the clause.
     * @param eenheid The unit of the clause.
     * @param prijsAfspraak The price agreement of the clause.
     */
    Clausule(int id, Contract contract, Periode periode, double hoeveelheid, Eenheid eenheid, PrijsAfspraak prijsAfspraak){
        this.id = id;
        this.contract = contract;
        this.leveringen = new ArrayList<>();
        this.periode = periode;
        this.hoeveelheid = hoeveelheid;
        this.eenheid = eenheid;
        this.prijsAfspraak = prijsAfspraak;
    }
    //endregion

    /**
     * Adds a delivery to the list of deliveries associated with this clause.
     *
     * @param levering The delivery to be added.
     * @return true if the delivery was added successfully, false otherwise.
     */
    boolean addLevering(Levering levering){
        return this.leveringen.add(levering);
    }

    public boolean isActive(LocalDate date){
        return periode.isIn(date);
    }
}