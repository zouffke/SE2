package be.kdg.hifresh.domain.aankoop;

import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.UtilFactory;

import java.time.LocalDateTime;

/**
 * Startup die ingredienten en recepten aan huis levert voor een week lekker zelf koken.
 */
public class ContractManager {

    private final ContractCataloog contractCataloog;
    private final DistributieCentraCataloog dcCataloog;

    public ContractManager() {
        this.contractCataloog = new ContractCataloog();
        this.dcCataloog = new DistributieCentraCataloog();
    }

    public boolean addContractToCatalog(Contract contract) {
        return this.contractCataloog.addContract(contract);
    }

    public Contract createNewContract(Product product) {
        return new Contract(product);
    }

    public static Product createNewProduct(String naam, int prodId) {
        return new Product(naam, prodId);
    }

    public Product getProductFromCatalog(int prodId) {
        return this.contractCataloog.getProduct(prodId);
    }

    public boolean addCentrumToCatalog(int id, String name) {
        return this.dcCataloog.addCentrum(this.dcCataloog.createNewCentrum(id, name));
    }

    public boolean addClausuleToContract(int prodId, Clausule clausule) {
        return this.contractCataloog.addClausuleToContract(prodId, clausule);
    }

    public Clausule createNewClausule(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) {
        return new Clausule(
                id,
                this.contractCataloog.getContractByProdId(prodId),
                UtilFactory.createPeriod(start, end),
                hoeveelheid,
                eenheid,
                UtilFactory.createPrijsAfspraak(UtilFactory.createMunt(bedrag, "Euro"), hoeveelheid)
        );
    }
}