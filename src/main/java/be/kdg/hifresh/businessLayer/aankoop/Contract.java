package be.kdg.hifresh.businessLayer.aankoop;

import be.kdg.hifresh.businessLayer.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.util.PrijsAfspraak;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a contract between HiFresh and a supplier for the delivery of products.
 */
public class Contract {
    //region vars

    /**
     * The supplier associated with this contract.
     */
    private final Leverancier leverancier;
    private final DistributieCentrum distributieCentrum;

    /**
     * The product associated with this contract.
     */
    @Getter
    private final Product product;

    /**
     * The list of clauses associated with this contract.
     */
    @Getter
    private List<Clausule> clausules;
    private final int id;
    //endregion

    //region constructors

    /**
     * Constructor for Contract.
     *
     * @param product The product associated with the contract.
     */
    Contract(int id, Product product, Leverancier leverancier, DistributieCentrum distributieCentrum) {
        this.product = product;
        product.addContract(this);
        this.clausules = new ArrayList<>();
        this.leverancier = leverancier;
        leverancier.addContract(this);
        this.distributieCentrum = distributieCentrum;
        this.id = id;
    }
    //endregion

    /**
     * Adds a clause to the list of clauses associated with this contract.
     *
     * @param clausule The clause to be added.
     * @return true if the clause was added successfully, false otherwise.
     */
    public boolean addClausule(Clausule clausule) {
        return this.clausules.add(clausule);
    }

    /**
     * Retrieves the price agreements that are valid on a given date.
     *
     * @param date The date for which to retrieve the price agreements.
     * @return A list of price agreements that are valid on the given date.
     */
    public List<PrijsAfspraak> getGeldendePrijsAfspraken(LocalDate date) {
        List<PrijsAfspraak> prijsAfspraken = new ArrayList<>();

        for (Clausule clausule : this.clausules) {
            if (clausule.isActive(date)) {
                prijsAfspraken.add(clausule.getPrijsAfspraak());
            }
        }

        return prijsAfspraken;
    }
}