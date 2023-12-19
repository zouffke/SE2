package be.kdg.hifresh.businessLayer.domain.aankoop;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.domain.util.PrijsAfspraak;
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
    private final Leverancier LEVERANCIER;
    private final DistributieCentrum DISTRIBUTIE_CENTRUM;

    /**
     * The product associated with this contract.
     */
    @Getter
    private final Product PRODUCT;

    /**
     * The list of clauses associated with this contract.
     */
    @Getter
    private final List<Clausule> CLAUSULES;
    @Getter
    private final int ID;
    //endregion

    //region constructors

    /**
     * Constructor for Contract.
     *
     * @param product The product associated with the contract.
     */
    Contract(int id, Product product, Leverancier leverancier, DistributieCentrum distributieCentrum) {
        this.PRODUCT = product;
        product.addContract(this);
        this.CLAUSULES = new ArrayList<>();
        this.LEVERANCIER = leverancier;
        leverancier.addContract(this);
        this.DISTRIBUTIE_CENTRUM = distributieCentrum;
        this.ID = id;
    }
    //endregion

    /**
     * Adds a clause to the list of clauses associated with this contract.
     *
     * @param clausule The clause to be added.
     * @return true if the clause was added successfully, false otherwise.
     */
    public boolean addClausule(Clausule clausule) {
        return this.CLAUSULES.add(clausule);
    }

    /**
     * Retrieves the price agreements that are valid on a given date.
     *
     * @param date The date for which to retrieve the price agreements.
     * @return A list of price agreements that are valid on the given date.
     */
    public List<PrijsAfspraak> getGeldendePrijsAfspraken(LocalDate date) {
        List<PrijsAfspraak> prijsAfspraken = new ArrayList<>();

        for (Clausule clausule : this.CLAUSULES) {
            if (clausule.isActive(date)) {
                prijsAfspraken.add(clausule.getPRIJSAFSPRAAK());
            }
        }

        return prijsAfspraken;
    }
}