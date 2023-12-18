package be.kdg.hifresh.applicationLayer.recepten;

import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.recepten.BereidingsStap;
import be.kdg.hifresh.businessLayer.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.recepten.Recept;
import be.kdg.hifresh.businessLayer.recepten.ReceptenFactory;
import be.kdg.hifresh.businessLayer.util.Eenheid;
import be.kdg.hifresh.persistenceLayer.recepten.ReceptManager;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * This class is responsible for managing recipes.
 * It provides methods to add recipes, add sub-recipes, add ingredients, and add preparation steps to recipes.
 */
public final class ReceptController {

    //region vars

    /**
     * ReceptManager instance
     */
    @Setter
    private static ReceptManager manager;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
     */
    private ReceptController() {
    }

    //endregion

    //region setup functions

    /**
     * Retrieves all ingredients of a recipe by its ID
     *
     * @param receptId Recipe ID
     * @return List of ingredients
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static List<Ingredient> getAllIngredients(int receptId) throws InvocationTargetException, IllegalAccessException {
        return manager.getAllIngredients(receptId);
    }

    /**
     * Adds a recipe to the catalog
     *
     * @param id           Recipe ID
     * @param name         Recipe name
     * @param beschrijving Recipe description
     * @return true if the recipe was added successfully, false otherwise
     */
    public static boolean addRecept(int id, String name, String beschrijving) {
        return manager.add(
                ReceptenFactory.createRecept(id, name, beschrijving),
                manager.getReceptCataloog()
        );
    }

    /**
     * Adds a sub-recipe to a recipe
     *
     * @param subReceptId Sub-recipe ID
     * @param receptId    Recipe ID
     * @return true if the sub-recipe was added successfully, false otherwise
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static boolean addSubreceptToRecept(int subReceptId, int receptId) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(
                receptId,
                manager.getReceptCataloog()
        ).addSubrecept(
                manager.getById(
                        subReceptId,
                        manager.getReceptCataloog())
        );
    }

    /**
     * Adds an ingredient to a recipe
     *
     * @param ingrId   Ingredient ID
     * @param product  Product object
     * @param receptId Recipe ID
     * @param amt      Amount
     * @return true if the ingredient was added successfully, false otherwise
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static boolean addIngredientToRecept(int ingrId, Product product, int receptId, double amt, Eenheid eenheid) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(
                        receptId,
                        manager.getReceptCataloog())
                .addIngredient(
                        ReceptenFactory.createIngredient(
                                ingrId,
                                product,
                                amt,
                                eenheid
                        )
                );
    }

    /**
     * Adds a preparation step to a recipe
     *
     * @param receptId  Recipe ID
     * @param stapId    Step ID
     * @param stapName  Step name
     * @param stapBesch Step description
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) throws InvocationTargetException, IllegalAccessException {
        addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch, manager.getById(receptId, manager.getReceptCataloog()).getNextVolgnummer());
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch, int volnummer) throws InvocationTargetException, IllegalAccessException {
        Recept recept = manager.getById(
                receptId,
                manager.getReceptCataloog());

        recept.addBereidingsStap(
                ReceptenFactory.createBereidingsStap(
                        stapId,
                        stapName,
                        stapBesch
                ),
                volnummer
        );
    }

    public static void addIngredientToBereidingstap(int receptId, int volgNummer, List<Integer> ingredientIds) throws InvocationTargetException, IllegalAccessException {
        BereidingsStap bereidingsStap = manager.getById(receptId, manager.getReceptCataloog()).getBereidingStap(volgNummer);

        for (Integer id : ingredientIds) {
            bereidingsStap.addIngredient(id);
        }
    }
    //endregion

    public static Recept getRecept(int receptId) throws InvocationTargetException, IllegalAccessException {
        return manager.getById(receptId, manager.getReceptCataloog());
    }
}