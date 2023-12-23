package be.kdg.hifresh.applicationLayer.recepten;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.businessLayer.domain.recepten.ReceptenFactory;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.services.recepten.ReceptManager;
import lombok.Setter;

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
     */
    public static List<Ingredient> getAllIngredients(int receptId) {
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
     */
    public static boolean addSubreceptToRecept(int subReceptId, int receptId, int stap) {
        Recept recept = manager.getById(receptId, manager.getReceptCataloog());
        Recept subRecept = manager.getById(subReceptId, manager.getReceptCataloog());

        return recept.addSubrecept(subRecept, stap);
    }

    /**
     * Adds a preparation step to a recipe
     *
     * @param receptId  Recipe ID
     * @param stapId    Step ID
     * @param stapName  Step name
     * @param stapBesch Step description
     */
    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) {
        addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch, manager.getById(receptId, manager.getReceptCataloog()).getNextVolgnummer());
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch, int volgnummer) {
        Recept recept = manager.getById(
                receptId,
                manager.getReceptCataloog());

        recept.addBereidingsStap(
                ReceptenFactory.createBereidingsStap(
                        stapId,
                        stapName,
                        stapBesch,
                        volgnummer
                )
        );
    }

    public static void addIngredientToBereidingstap(int receptId, int volgNummer, List<Integer> ingredientIds) {
        Recept recept = manager.getById(receptId, manager.getReceptCataloog());

        for (Integer id : ingredientIds) {
            recept.addIngredient(manager.getById(id, manager.getINGREDIENT_CATALOG()), volgNummer);
        }
    }
    //endregion

    public static Recept getRecept(int receptId) {
        return manager.getById(receptId, manager.getReceptCataloog());
    }

    public static boolean addIngredient(int ingrId, Product product, double hoeveelheid, Eenheid eenheid) {
        return manager.add(
                ReceptenFactory.createIngredient(
                        ingrId,
                        product,
                        hoeveelheid,
                        eenheid
                ),
                manager.getINGREDIENT_CATALOG()
        );
    }
}