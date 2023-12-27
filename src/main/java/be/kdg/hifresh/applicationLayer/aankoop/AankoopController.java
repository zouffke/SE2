package be.kdg.hifresh.applicationLayer.aankoop;

import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.businessLayer.domain.aankoop.AankoopFactory;
import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.domain.util.UtilFactory;
import be.kdg.hifresh.businessLayer.services.aankoop.AankoopManager;
import be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions.IProductSuggestionsStrat;
import be.kdg.hifresh.businessLayer.services.pubSub.MessageBroker;
import be.kdg.hifresh.persistenceLayer.aankoop.ContractCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.DistributieCentraCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.ProductCataloog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

/**
 * This class is responsible for managing contracts.
 * It provides methods to add products, get products, add centrum to catalog, add clausule, get average purchase price and get product suggestions.
 */
@Controller
public final class AankoopController {

    //region vars

    /**
     * AankoopManager instance for managing contracts
     */
    private final AankoopManager MANAGER;

    /**
     * GebruikerController instance for managing users
     */
    private final GebruikerController GEBRUIKER_CONTROLLER;

    /**
     * MessageBroker instance for managing messages
     */
    private final MessageBroker MESSAGE_BROKER;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
     * @param manager AankoopManager instance
     * @param gebruikerController GebruikerController instance
     * @param messageBroker MessageBroker instance
     */
    @Autowired
    public AankoopController(AankoopManager manager,
                             GebruikerController gebruikerController,
                             MessageBroker messageBroker) {
        this.MANAGER = manager;
        this.GEBRUIKER_CONTROLLER = gebruikerController;
        this.MESSAGE_BROKER = messageBroker;
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
    public boolean addProduct(int prodId, String name) {
        return MANAGER.add(
                AankoopFactory.createProduct(prodId, name),
                MANAGER.getCatalog(ProductCataloog.class));
    }

    /**
     * Retrieves a product from the contract catalog by its ID
     *
     * @param prodId Product ID
     * @return Product object
     */
    public Product getProduct(int prodId) {
        return MANAGER.getById(
                prodId,
                MANAGER.getCatalog(ProductCataloog.class));
    }

    /**
     * Adds a distribution center to the catalog
     *
     * @param id   Distribution center ID
     * @param name Distribution center name
     * @return true if the distribution center was added successfully, false otherwise
     */
    public boolean addCentrum(int id, String name) {
        return MANAGER.add(
                AankoopFactory.createDistributieCentrum(
                        id,
                        name
                ),
                MANAGER.getCatalog(DistributieCentraCataloog.class)
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
    public boolean addClausule(int id, int contractId, LocalDate start, LocalDate end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Contract contract = MANAGER.getById(
                contractId,
                MANAGER.getCatalog(ContractCataloog.class)
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

    /**
     * Adds a contract to the catalog
     *
     * @param id Contract ID
     * @param productId Product ID
     * @param leverancierId Supplier ID
     * @param distributieCentrumId Distribution center ID
     * @return true if the contract was added successfully, false otherwise
     */
    public boolean addContract(int id, int productId, int leverancierId, int distributieCentrumId) {
        return MANAGER.add(
                AankoopFactory.createContract(
                        id,
                        MANAGER.getById(productId, MANAGER.getCatalog(ProductCataloog.class)),
                        GEBRUIKER_CONTROLLER.getLeverancier(leverancierId),
                        MANAGER.getById(distributieCentrumId, MANAGER.getCatalog(DistributieCentraCataloog.class)),
                        MESSAGE_BROKER
                ),
                MANAGER.getCatalog(ContractCataloog.class)
        );
    }

    //endregion

    /**
     * Calculates the average purchase price
     *
     * @param date Date
     * @return Munt object representing the average purchase price
     */
    public Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        return MANAGER.getGemiddeldeAankoopPrijs(ingredients, date);
    }

    /**
     * Provides product suggestions
     *
     * @param date Date
     * @return List of product suggestions
     */
    public List<Product> getProductSuggesties(IProductSuggestionsStrat productSuggestionsStrat, LocalDate date) {
        return MANAGER.getProductSuggesties(productSuggestionsStrat, date);
    }

    /**
     * Retrieves a list of products by their name
     *
     * @param name Product name
     * @return List of products
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public List<Product> getProductsByName(String name) throws InvocationTargetException, IllegalAccessException {
        return MANAGER.getTByName(name, Product.class);
    }

    /**
     * Retrieves a list of active products
     *
     * @param date Date
     * @return List of active products
     */
    public List<Product> getActiveProducts(LocalDate date) {
        return MANAGER.getActiveProducts(date);
    }

    /**
     * Sorts a list of products based on their average price
     *
     * @param list List of products
     * @param date Date
     * @return Sorted list of products
     */
    public List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return MANAGER.sortOnAvgPrice(list, date);
    }

    /**
     * Clears all catalogs
     */
    public void clearCatalogs() {
        this.MANAGER.clearCatalogs();
    }
}