package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.domain.aankoop.Contract;
import be.kdg.hifresh.domain.recepten.Ingredient;
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

    public Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        double bedrag = 0;
        double hoeveelheid = 0;
        double totalBedrag = 0;

        for (Ingredient ingredient : ingredients) {
            for (Contract contract : ingredient.getProduct().getContracten()) {
                for (PrijsAfspraak prijsAfspraak : contract.getGeldendePrijsAfspraken(date)) {
                    bedrag += prijsAfspraak.getPrijs().getBedrag() * prijsAfspraak.getMaxHoeveelheid();
                    hoeveelheid += prijsAfspraak.getMaxHoeveelheid();
                }
            }
            bedrag /= hoeveelheid;

            totalBedrag += ingredient.getHoeveelheid() * bedrag;
            bedrag = 0;
            hoeveelheid = 0;
        }

        return UtilFactory.createMunt(totalBedrag, "Euro");
    }
}