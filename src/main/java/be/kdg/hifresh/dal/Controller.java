package be.kdg.hifresh.dal;

import be.kdg.hifresh.dal.aankoop.ContractController;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.recepten.ReceptController;
import be.kdg.hifresh.dal.recepten.ReceptManager;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.Munt;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * This is a controller class that provides static methods to manage contracts and recipes.
 */
public final class Controller {

    @Getter
    private static LocalDate today;

    public static void setToday(LocalDate today) {
        Controller.today = today;
    }

    //region constructors

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Controller() {
    }

    //endregion

    /**
     * Sets the managers for ContractController and ReceptController.
     *
     * @param contractManager The manager for contracts.
     * @param receptManager   The manager for recipes.
     */
    public static void setManagers(ContractManager contractManager, ReceptManager receptManager) {
        ContractController.setManager(contractManager);
        ReceptController.setManager(receptManager);
    }

    //region controller functions setup

    /**
     * Adds a recipe to the catalog.
     *
     * @param id            The id of the recipe.
     * @param name          The name of the recipe.
     * @param beschrijving  The description of the recipe.
     * @return              True if the recipe was added successfully, false otherwise.
     */
    public static boolean addReceptToCatalog(int id, String name, String beschrijving) {
        return ReceptController.addReceptToCatalog(id, name, beschrijving);
    }

    /**
     * Adds a sub-recipe to a recipe.
     *
     * @param subReceptId   The id of the sub-recipe.
     * @param receptId      The id of the recipe.
     * @return              True if the sub-recipe was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addSubreceptToRecept(int subReceptId, int receptId) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addSubreceptToRecept(subReceptId, receptId);
    }

    /**
     * Adds a product.
     *
     * @param prodId    The id of the product.
     * @param name      The name of the product.
     * @return          True if the product was added successfully, false otherwise.
     */
    public static boolean addProduct(int prodId, String name) {
        return ContractController.addProduct(prodId, name);
    }

    /**
     * Adds an ingredient to a recipe.
     *
     * @param ingrId    The id of the ingredient.
     * @param prodId    The id of the product.
     * @param receptId  The id of the recipe.
     * @param amt       The amount of the ingredient.
     * @return          True if the ingredient was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addIngredientToRecept(int ingrId, int prodId, int receptId, double amt) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addIngredientToRecept(ingrId, ContractController.getProductFromCatalog(prodId), receptId, amt);
    }

    /**
     * Adds a preparation step to a recipe.
     *
     * @param receptId  The id of the recipe.
     * @param stapId    The id of the step.
     * @param stapName  The name of the step.
     * @param stapBesch The description of the step.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) throws InvocationTargetException, IllegalAccessException {
        ReceptController.addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch);
    }

    /**
     * Adds a center to the catalog.
     *
     * @param id    The id of the center.
     * @param name  The name of the center.
     * @return      True if the center was added successfully, false otherwise.
     */
    public static boolean addCentrumToCatalog(int id, String name) {
        return ContractController.addCentrumToCatalog(id, name);
    }

    /**
     * Adds a clause.
     *
     * @param id            The id of the clause.
     * @param prodId        The id of the product.
     * @param start         The start date of the clause.
     * @param end           The end date of the clause.
     * @param hoeveelheid   The quantity of the product.
     * @param eenheid       The unit of the product.
     * @param bedrag        The amount of the clause.
     * @return              True if the clause was added successfully, false otherwise.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static boolean addClausule(int id, int prodId, LocalDate start, LocalDate end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException {
        return ContractController.addClausule(id, prodId, start, end, hoeveelheid, eenheid, bedrag);
    }

    //endregion

    /**
     * Gets the average purchase price for a recipe.
     *
     * @param receptId  The id of the recipe.
     * @param date      The date of the purchase.
     * @return          The average purchase price.
     * @throws InvocationTargetException if the called method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public static Munt getGemiddeldeAankoopPrijs(int receptId, LocalDate date) throws InvocationTargetException, IllegalAccessException {
        return ContractController.getGemiddeldeAankoopPrijs(
                        ReceptController.getAllIngredients(receptId), date);
    }

    public static List<Product> getProductSuggesties(LocalDate date){
        return ContractController.getProductSuggesties(date);
    }
}