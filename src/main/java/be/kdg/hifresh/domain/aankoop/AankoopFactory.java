package be.kdg.hifresh.domain.aankoop;

import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.Periode;
import be.kdg.hifresh.domain.util.PrijsAfspraak;

/**
 * A factory class for creating various objects related to purchases.
 *
 * @author Dandois Luca
 */
public class AankoopFactory {

    //region vars
    private static DistributieCentraCataloog distributieCentraCataloog;
    private static ContractCataloog contractCataloog;
    //endregion

    //region constructors
    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @author Dandois Luca
     */
    private AankoopFactory() {
    }
    //endregion

    //region createMethods
    /**
     * Creates a new Clausule object.
     *
     * @param id The id of the clause.
     * @param contract The contract associated with the clause.
     * @param periode The period of the clause.
     * @param hoeveelheid The quantity of the clause.
     * @param eenheid The unit of the clause.
     * @param prijsAfspraak The price agreement of the clause.
     * @return A new Clausule object.
     *
     * @author Dandois Luca
     */
    public static Clausule createClausule(int id, Contract contract, Periode periode, double hoeveelheid, Eenheid eenheid, PrijsAfspraak prijsAfspraak) {
        return new Clausule(id, contract, periode, hoeveelheid, eenheid, prijsAfspraak);
    }

    /**
     * Creates a new Contract object.
     *
     * @param product The product associated with the contract.
     * @return A new Contract object.
     *
     * @author Dandois Luca
     */
    public static Contract createContract(Product product) {
        return new Contract(product);
    }

    /**
     * Creates a new ContractCataloog object.
     *
     * @return A new or existing ContractCataloog object.
     *
     * @author Dandois Luca
     */
    public static ContractCataloog createContractCataloog() {
        if (contractCataloog == null) {
            contractCataloog = new ContractCataloog();
        }

        return contractCataloog;
    }

    /**
     * Creates a new ContractManager object.
     *
     * @return A new ContractManager object.
     *
     * @author Dandois Luca
     */
    public static ContractManager createContractManager() {
        return new ContractManager();
    }

    /**
     * Creates a new DistributieCentraCataloog object.
     *
     * @return A new or existing DistributieCentraCataloog object.
     *
     * @author Dandois Luca
     */
    public static DistributieCentraCataloog createDistributieCentraCataloog() {
        if (distributieCentraCataloog == null) {
            distributieCentraCataloog = new DistributieCentraCataloog();
        }

        return distributieCentraCataloog;
    }

    /**
     * Creates a new DistributieCentrum object.
     *
     * @param id The id of the distribution center.
     * @param name The name of the distribution center.
     * @return A new DistributieCentrum object.
     *
     * @author Dandois Luca
     */
    public static DistributieCentrum createDistributieCentrum(int id, String name) {
        return new DistributieCentrum(id, name);
    }

    /**
     * Creates a new InkoopOrder object.
     *
     * @return A new InkoopOrder object.
     *
     * @author Dandois Luca
     */
    public static InkoopOrder createInkoopOrder() {
        return new InkoopOrder();
    }

    /**
     * Creates a new Levering object.
     *
     * @return A new Levering object.
     *
     * @author Dandois Luca
     */
    public static Levering createLevering() {
        return new Levering();
    }

    /**
     * Creates a new Product object.
     *
     * @param id The id of the product.
     * @param naam The name of the product.
     * @return A new Product object.
     *
     * @author Dandois Luca
     */
    public static Product createProduct(int id, String naam) {
        return new Product(naam, id);
    }
    //endregion
}