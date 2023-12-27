package be.kdg.hifresh.applicationLayer.aankoop;

import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
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


    private final AankoopManager MANAGER;
    private final GebruikerController GEBRUIKER_CONTROLLER;
    private final MessageBroker MESSAGE_BROKER;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
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
                MANAGER.getPRODUCT_CATALOG());
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
                MANAGER.getPRODUCT_CATALOG());
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
                MANAGER.getDC_CATALOG()
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
                MANAGER.getCONTRACT_CATALOG()
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

    public boolean addContract(int id, int productId, int leverancierId, int distributieCentrumId) {
        return MANAGER.add(
                AankoopFactory.createContract(
                        id,
                        MANAGER.getById(productId, MANAGER.getPRODUCT_CATALOG()),
                        GEBRUIKER_CONTROLLER.getLeverancier(leverancierId),
                        MANAGER.getById(distributieCentrumId, MANAGER.getDC_CATALOG()),
                        MESSAGE_BROKER
                ),
                MANAGER.getCONTRACT_CATALOG()
        );
    }

    //endregion

    /**
     * Calculates the average purchase price
     *
     * @param date        Date
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

    public List<Product> getProductsByName(String name) throws InvocationTargetException, IllegalAccessException {
        return MANAGER.getProductsByName(name);
    }

    public List<Product> getActiveProducts(LocalDate date) {
        return MANAGER.getActiveProducts(date);
    }

    public List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return MANAGER.sortOnAvgPrice(list, date);
    }

    public void clearCatalogs(){
        this.MANAGER.clearCatalogs();
    }
}