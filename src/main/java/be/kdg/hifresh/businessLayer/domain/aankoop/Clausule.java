package be.kdg.hifresh.businessLayer.domain.aankoop;

import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.domain.util.Periode;
import be.kdg.hifresh.businessLayer.domain.util.PrijsAfspraak;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a clause in a contract.
 */
@Getter
public class Clausule {
    //region vars

    /**
     * The contract associated with this clause.
     */
    private final Contract CONTRACT;

    /**
     * The list of deliveries associated with this clause.
     */
    private final List<Levering> LEVERINGEN;
    /**
     * The period of this clause.
     */
    private final Periode PERIODE;
    /**
     * The unit of this clause.
     */
    private final Eenheid EENHEID;
    /**
     * The price agreement of this clause.
     */
    private final PrijsAfspraak PRIJSAFSPRAAK;
    /**
     * The id of this clause.
     */
    private final int ID;
    /**
     * The quantity of this clause.
     */
    private final double HOEVEELHEID;
    /**
     * The list of purchase orders associated with this clause.
     */
    private List<InkoopOrder> inkoopOrders;
    //endregion

    //region constructors

    /**
     * Constructor for Clausule.
     *
     * @param id            The id of the clause.
     * @param contract      The contract associated with the clause.
     * @param periode       The period of the clause.
     * @param hoeveelheid   The quantity of the clause.
     * @param eenheid       The unit of the clause.
     * @param prijsAfspraak The price agreement of the clause.
     */
    Clausule(int id, Contract contract, Periode periode, double hoeveelheid, Eenheid eenheid, PrijsAfspraak prijsAfspraak) {
        this.ID = id;
        this.CONTRACT = contract;
        this.LEVERINGEN = new ArrayList<>();
        this.PERIODE = periode;
        this.HOEVEELHEID = hoeveelheid;
        this.EENHEID = eenheid;
        this.PRIJSAFSPRAAK = prijsAfspraak;
    }
    //endregion

    /**
     * Checks if the clause is active on a given date.
     *
     * @param date The date to check.
     * @return true if the clause is active on the given date, false otherwise.
     */
    public boolean isActive(LocalDate date) {
        return PERIODE.isIn(date);
    }
}