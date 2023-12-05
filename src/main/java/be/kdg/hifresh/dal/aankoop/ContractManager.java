package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.domain.aankoop.Contract;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.util.Munt;
import be.kdg.hifresh.domain.util.PrijsAfspraak;
import be.kdg.hifresh.domain.util.UtilFactory;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * A manager class for handling contracts.
 * Extends the Manager class.
 *
 * @author Dandois Luca
 */
@Getter
public class ContractManager extends Manager {

    //region vars
    /**
     * Catalog of contracts.
     */
    private final ContractCataloog contractCataloog;

    /**
     * Catalog of distribution centers.
     */
    private final DistributieCentraCataloog dcCataloog;
    //endregion

    //region constructors

    /**
     * Constructor for ContractManager.
     * Initializes the contract catalog and distribution center catalog.
     *
     * @author Dandois Luca
     */
    public ContractManager() {
        this.contractCataloog = new ContractCataloog();
        this.dcCataloog = new DistributieCentraCataloog();
    }
    //endregion

    public Munt getGemiddeldeAankoopPrijs(List<Product> products, LocalDate date) {
        Munt munt = UtilFactory.createMunt(0, "Euro");
        for (Product product : products) {
            for (Contract contract : product.getContracten()) {
                for (PrijsAfspraak prijsAfspraak : contract.getGeldendePrijsAfspraken(date))
                    munt.addBedrag(prijsAfspraak.getPrijs().getBedrag() / prijsAfspraak.getMaxHoeveelheid());
            }
        }
        return munt;
    }
}