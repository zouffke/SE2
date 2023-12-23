package be.kdg.hifresh.businessLayer.domain.aankoop;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.domain.util.Periode;
import be.kdg.hifresh.businessLayer.domain.util.PrijsAfspraak;
import be.kdg.hifresh.businessLayer.services.pubSub.MessageBroker;

/**
 * A factory class for creating various objects related to purchases.
 *
 * @author Dandois Luca
 */
public final class AankoopFactory {
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
     * @param id            The id of the clause.
     * @param contract      The contract associated with the clause.
     * @param periode       The period of the clause.
     * @param hoeveelheid   The quantity of the clause.
     * @param eenheid       The unit of the clause.
     * @param prijsAfspraak The price agreement of the clause.
     * @return A new Clausule object.
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
     * @author Dandois Luca
     */
    public static Contract createContract(int id, Product product, Leverancier leverancier, DistributieCentrum distributieCentrum, MessageBroker messageBroker){
        return new Contract(id, product, leverancier, distributieCentrum, messageBroker);
    }

    /**
     * Creates a new DistributieCentrum object.
     *
     * @param id   The id of the distribution center.
     * @param name The name of the distribution center.
     * @return A new DistributieCentrum object.
     * @author Dandois Luca
     */
    public static DistributieCentrum createDistributieCentrum(int id, String name) {
        return new DistributieCentrum(id, name);
    }

    /**
     * Creates a new Product object.
     *
     * @param id   The id of the product.
     * @param name The name of the product.
     * @return A new Product object.
     * @author Dandois Luca
     */
    public static Product createProduct(int id, String name) {
        return new Product(name, id);
    }
    //endregion
}