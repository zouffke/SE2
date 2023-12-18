package be.kdg.hifresh.applicationLayer.aankoop;

import be.kdg.hifresh.businessLayer.aankoop.AankoopFactory;
import be.kdg.hifresh.businessLayer.aankoop.Contract;
import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.util.Eenheid;
import be.kdg.hifresh.businessLayer.util.Munt;
import be.kdg.hifresh.businessLayer.util.UtilFactory;
import be.kdg.hifresh.persistenceLayer.aankoop.AankoopManager;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

/**
 * This class is responsible for managing contracts.
 * It provides methods to add products, get products, add centrum to catalog, add clausule, get average purchase price and get product suggestions.
 */
public final class AankoopController {

    //region vars

    /**
     * ContractManager instance
     */
    @Setter
    private static AankoopManager manager;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
     */
    private AankoopController() {
    }

    //endregion

    //region setup functions

    /**
     * Adds a product to the contract catalog
     *
     * @param prodId Product ID
     * @param name   Product name
     * @return true if the product was added successfully, false otherwise
     */
    public static boolean addProduct(int prodId, String name) {
        return manager.add(
                AankoopFactory.createProduct(prodId, name),
                manager.getPRODUCT_CATALOG());
    }

    /**
     * Retrieves a product from the contract catalog by its ID
     *
     * @param prodId Product ID
     * @return Product object
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static Product getProduct(int prodId) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(
                prodId,
                manager.getPRODUCT_CATALOG());
    }

    /**
     * Adds a distribution center to the catalog
     *
     * @param id   Distribution center ID
     * @param name Distribution center name
     * @return true if the distribution center was added successfully, false otherwise
     */
    public static boolean addCentrum(int id, String name) {
        return manager.add(
                AankoopFactory.createDistributieCentrum(
                        id,
                        name
                ),
                manager.getDC_CATALOG()
        );
    }

    /**
     * Adds a clause to a contract
     *
     * @param id          Clause ID
     * @param contractId  contract ID
     * @param start       Start date of the clause
     * @param end         End date of the clause
     * @param hoeveelheid Quantity
     * @param eenheid     Unit
     * @param bedrag      Amount
     * @return true if the clause was added successfully, false otherwise
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static boolean addClausule(int id, int contractId, LocalDate start, LocalDate end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException {
        Contract contract = manager.getById(
                contractId,
                manager.getCONTRACT_CATALOG()
        );

        return contract.addClausule(
                AankoopFactory.createClausule(
                        id,
                        contract,
                        UtilFactory.createPeriod(start, end),
                        hoeveelheid,
                        eenheid,
                        UtilFactory.createPrijsAfspraak(
                                UtilFactory.createMunt(bedrag, "Euro"),
                                hoeveelheid
                        )
                )
        );
    }

    public static boolean addContract(int id, int productId, Leverancier leverancier, int distributieCentrumId) throws InvocationTargetException, IllegalAccessException {
        return manager.add(
                AankoopFactory.createContract(
                        id,
                        manager.getById(productId, manager.getPRODUCT_CATALOG()),
                        leverancier,
                        manager.getById(distributieCentrumId, manager.getDC_CATALOG())
                ),
                manager.getCONTRACT_CATALOG()
        );
    }

    //endregion

    /**
     * Calculates the average purchase price
     *
     * @param ingredients List of ingredients
     * @param date        Date
     * @return Munt object representing the average purchase price
     */
    public static Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        return manager.getGemiddeldeAankoopPrijs(ingredients, date);
    }

    /**
     * Provides product suggestions
     *
     * @param date Date
     * @return List of product suggestions
     */
    public static List<Product> getProductSuggesties(LocalDate date) {
        return manager.getProductSuggesties(date);
    }

    public static List<Product> getProductsByName(String name) throws InvocationTargetException, IllegalAccessException {
        return manager.getProductsByName(name);
    }

    public static List<Product> getActiveProducts(LocalDate date) {
        return manager.getActiveProducts(date);
    }

    public static List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return manager.sortOnAvgPrice(list, date);
    }
}