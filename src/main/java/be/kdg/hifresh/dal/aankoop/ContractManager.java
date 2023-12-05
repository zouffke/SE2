package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.dal.Catalog;
import be.kdg.hifresh.dal.IManager;
import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.domain.aankoop.AankoopFactory;
import be.kdg.hifresh.domain.aankoop.Clausule;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.UtilFactory;

import java.time.LocalDateTime;

/**
 * Startup die ingredienten en recepten aan huis levert voor een week lekker zelf koken.
 */
public class ContractManager implements IManager {

    //region vars
    private final ContractCataloog contractCataloog;
    private final DistributieCentraCataloog dcCataloog;
    //endregion

    //region constructors
    public ContractManager() {
        this.contractCataloog = AankoopFactory.createContractCataloog();
        this.dcCataloog = AankoopFactory.createDistributieCentraCataloog();
    }
    //endregion

    //region Interface overrides
    @Override
    public <T> boolean addTtoCatalog(T object, Catalog<T> catalog) {
        return catalog.addObjToCatalog(object);
    }

    @Override
    public <T> T getObjFromCatalog(int index, Catalog<T> catalog) {
        return catalog.getObjFromCatalog(index);
    }
    //endregion

    //region ownFunctions
    public
    //endregion
    public Product getProductFromCatalog(int prodId) {
        return this.contractCataloog.getProduct(prodId);
    }

    public boolean addCentrumToCatalog(int id, String name) {
        return this.dcCataloog.addCentrum(AankoopFactory.createDistributieCentrum(id, name));
    }

    public boolean addClausuleToContract(int prodId, Clausule clausule) {
        return this.contractCataloog.addClausuleToContract(prodId, clausule);
    }

    public Clausule createNewClausule(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) {
        return AankoopFactory.createClausule(
                id,
                this.contractCataloog.getContractByProdId(prodId),
                UtilFactory.createPeriod(start, end),
                hoeveelheid,
                eenheid,
                UtilFactory.createPrijsAfspraak(UtilFactory.createMunt(bedrag, "Euro"), hoeveelheid)
        );
    }
}