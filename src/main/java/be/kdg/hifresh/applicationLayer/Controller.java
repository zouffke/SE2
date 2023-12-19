package be.kdg.hifresh.applicationLayer;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.services.aankoop.AankoopManager;
import be.kdg.hifresh.businessLayer.services.aankoop.IProductSuggestionsStrat;
import be.kdg.hifresh.businessLayer.services.gebruiker.GebruikerManager;
import be.kdg.hifresh.businessLayer.services.recepten.ReceptManager;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

/**
 * This is a controller class that provides static methods to manage contracts and recipes.
 * It is a part of the application layer in the architecture.
 */
public final class Controller {

    //region today section

    // * Variable set in Controller so the 2 feature files can access the date given without giving errors
    /**
     * The current date.
     */
    @Getter
    private static LocalDate today;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Controller() {
    }

    //endregion

    //region constructors

    /**
     * Sets the current date.
     *
     * @param today The current date.
     */
    public static void setToday(LocalDate today) {
        Controller.today = today;
    }

    //endregion

    /**
     * Sets the managers for ContractController and ReceptController.
     *
     * @param aankoopManager The manager for contracts.
     * @param receptManager  The manager for recipes.
     */
    public static void setManagers(AankoopManager aankoopManager, ReceptManager receptManager, GebruikerManager gebruikerManager) {
        AankoopController.setManager(aankoopManager);
        ReceptController.setManager(receptManager);
        GebruikerController.setManager(gebruikerManager);
    }

    //region controller functions setup

    /**
     * Adds a recipe to the catalog.
     *
     * @param id           The id of the recipe.
     * @param name         The name of the recipe.
     * @param beschrijving The description of the recipe.
     * @return True if the recipe was added successfully, false otherwise.
     */
    public static boolean addRecept(int id, String name, String beschrijving) {
        return ReceptController.addRecept(id, name, beschrijving);
    }

    /**
     * Adds a sub-recipe to a recipe.
     *
     * @param subReceptId The id of the sub-recipe.
     * @param receptId    The id of the recipe.
     * @return True if the sub-recipe was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addSubreceptToRecept(int subReceptId, int receptId, int stap) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addSubreceptToRecept(subReceptId, receptId, stap);
    }

    /**
     * Adds a product.
     *
     * @param prodId The id of the product.
     * @param name   The name of the product.
     * @return True if the product was added successfully, false otherwise.
     */
    public static boolean addProduct(int prodId, String name) {
        return AankoopController.addProduct(prodId, name);
    }

    /**
     * Adds an ingredient to a recipe.
     *
     * @param ingrId   The id of the ingredient.
     * @param prodId   The id of the product.
     * @param receptId The id of the recipe.
     * @param amt      The amount of the ingredient.
     * @return True if the ingredient was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addIngredientToRecept(int ingrId, int prodId, int receptId, double amt, Eenheid eenheid) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addIngredientToRecept(ingrId, AankoopController.getProduct(prodId), receptId, amt, eenheid);
    }

    /**
     * Adds a preparation step to a recipe.
     *
     * @param receptId  The id of the recipe.
     * @param stapId    The id of the step.
     * @param stapName  The name of the step.
     * @param stapBesch The description of the step.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) throws InvocationTargetException, IllegalAccessException {
        ReceptController.addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch);
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch, int volgnummer) throws InvocationTargetException, IllegalAccessException {
        ReceptController.addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch, volgnummer);
    }

    /**
     * Adds a center to the catalog.
     *
     * @param id   The id of the center.
     * @param name The name of the center.
     * @return True if the center was added successfully, false otherwise.
     */
    public static boolean addCentrum(int id, String name) {
        return AankoopController.addCentrum(id, name);
    }

    /**
     * Adds a clause.
     *
     * @param id          The id of the clause.
     * @param contractId  The id of the contract.
     * @param start       The start date of the clause.
     * @param end         The end date of the clause.
     * @param hoeveelheid The quantity of the product.
     * @param eenheid     The unit of the product.
     * @param bedrag      The amount of the clause.
     * @return True if the clause was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addClausule(int id, int contractId, LocalDate start, LocalDate end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException {
        return AankoopController.addClausule(id, contractId, start, end, hoeveelheid, eenheid, bedrag);
    }

    public static boolean addLeverancier(int id, String name) {
        return GebruikerController.addLeverancier(id, name);
    }

    //endregion

    /**
     * Gets the average purchase price for a recipe.
     *
     * @param receptId The id of the recipe.
     * @param date     The date of the purchase.
     * @return The average purchase price.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static Munt getGemiddeldeAankoopPrijs(int receptId, LocalDate date) throws InvocationTargetException, IllegalAccessException {
        return AankoopController.getGemiddeldeAankoopPrijs(
                ReceptController.getAllIngredients(receptId), date);
    }

    /**
     * Provides product suggestions.
     *
     * @param date The date for which to provide product suggestions.
     * @return A list of product suggestions.
     */
    public static List<Product> getProductSuggesties(IProductSuggestionsStrat productSuggestionsStrat, LocalDate date) {
        return AankoopController.getProductSuggesties(productSuggestionsStrat, date);
    }

    public static void addIngredientToBereidingstap(int receptId, int volgNummer, List<Integer> ingredientIds) throws InvocationTargetException, IllegalAccessException {
        ReceptController.addIngredientToBereidingstap(receptId, volgNummer, ingredientIds);
    }

    public static boolean addContract(int id, int productId, int leverancierId, int distributieCentrumId) throws InvocationTargetException, IllegalAccessException {
        return AankoopController.addContract(
                id,
                productId,
                GebruikerController.getLeverancier(leverancierId),
                distributieCentrumId
        );
    }

    public static Recept getRecept(int receptId) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.getRecept(receptId);
    }

    public static List<Product> getProductsByName(String name) throws InvocationTargetException, IllegalAccessException {
        return AankoopController.getProductsByName(name);
    }

    public static List<Product> getActiveProducts(LocalDate date) {
        return AankoopController.getActiveProducts(date);
    }

    public static List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return AankoopController.sortOnAvgPrice(list, date);
    }
}