package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import lombok.Getter;

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
}