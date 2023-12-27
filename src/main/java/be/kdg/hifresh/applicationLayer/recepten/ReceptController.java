package be.kdg.hifresh.applicationLayer.recepten;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.businessLayer.domain.recepten.ReceptenFactory;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.businessLayer.services.recepten.ReceptManager;
import be.kdg.hifresh.persistenceLayer.recepten.IngredientCataloog;
import be.kdg.hifresh.persistenceLayer.recepten.ReceptCataloog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is responsible for managing recipes.
 * It provides methods to add recipes, add sub-recipes, add ingredients, and add preparation steps to recipes.
 */
@Controller
public final class ReceptController {

    //region vars

    /**
     * Manager instance for managing recipes
     */
    private final Manager MANAGER;

    /**
     * AankoopController instance for managing purchases
     */
    private final AankoopController AANKOOP_CONTROLLER;

    //endregion

    //region constructors

    /**
     * Private constructor to prevent instantiation
     * @param manager Manager instance
     * @param aankoopController AankoopController instance
     */
    @Autowired
    public ReceptController(ReceptManager manager,
                            AankoopController aankoopController) {
        this.MANAGER = manager;
        this.AANKOOP_CONTROLLER = aankoopController;
    }

    //endregion

    //region setup functions

    /**
     * Retrieves all ingredients of a recipe by its ID
     *
     * @param receptId Recipe ID
     * @return List of ingredients
     */
    public List<Ingredient> getAllIngredients(int receptId) {
        return MANAGER.getAllT(receptId, Ingredient.class);
    }

    /**
     * Adds a recipe to the catalog
     *
     * @param id           Recipe ID
     * @param name         Recipe name
     * @param beschrijving Recipe description
     * @return true if the recipe was added successfully, false otherwise
     */
    public boolean addRecept(int id, String name, String beschrijving) {
        return MANAGER.add(
                ReceptenFactory.createRecept(id, name, beschrijving),
                MANAGER.getCatalog(ReceptCataloog.class)
        );
    }

    /**
     * Adds a sub-recipe to a recipe
     *
     * @param subReceptId Sub-recipe ID
     * @param receptId    Recipe ID
     * @return true if the sub-recipe was added successfully, false otherwise
     */
    public boolean addSubreceptToRecept(int subReceptId, int receptId, int stap) {
        Recept recept = MANAGER.getById(receptId, MANAGER.getCatalog(ReceptCataloog.class));
        Recept subRecept = MANAGER.getById(subReceptId, MANAGER.getCatalog(ReceptCataloog.class));

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
    public void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) {
        addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch, MANAGER.getById(receptId, MANAGER.getCatalog(ReceptCataloog.class)).getNextVolgnummer());
    }

    /**
     * Adds a preparation step to a recipe at a specific sequence number
     *
     * @param receptId  Recipe ID
     * @param stapId    Step ID
     * @param stapName  Step name
     * @param stapBesch Step description
     * @param volgnummer Sequence number
     */
    public void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch, int volgnummer) {
        Recept recept = MANAGER.getById(
                receptId,
                MANAGER.getCatalog(ReceptCataloog.class));

        recept.addBereidingsStap(
                ReceptenFactory.createBereidingsStap(
                        stapId,
                        stapName,
                        stapBesch,
                        volgnummer
                )
        );
    }

    /**
     * Adds ingredients to a preparation step of a recipe
     *
     * @param receptId Recipe ID
     * @param volgNummer Sequence number
     * @param ingredientIds List of ingredient IDs
     */
    public void addIngredientToBereidingstap(int receptId, int volgNummer, List<Integer> ingredientIds) {
        Recept recept = MANAGER.getById(receptId, MANAGER.getCatalog(ReceptCataloog.class));

        for (Integer id : ingredientIds) {
            recept.addIngredient(MANAGER.getById(id, MANAGER.getCatalog(IngredientCataloog.class)), volgNummer);
        }
    }
    //endregion

    /**
     * Retrieves a recipe from the catalog by its ID
     *
     * @param receptId Recipe ID
     * @return Recipe object
     */
    public Recept getRecept(int receptId) {
        return MANAGER.getById(receptId, MANAGER.getCatalog(ReceptCataloog.class));
    }

    /**
     * Adds an ingredient to the catalog
     *
     * @param ingrId Ingredient ID
     * @param prodId Product ID
     * @param hoeveelheid Quantity
     * @param eenheid Unit
     * @return true if the ingredient was added successfully, false otherwise
     */
    public boolean addIngredient(int ingrId, int prodId, double hoeveelheid, Eenheid eenheid) {
        return MANAGER.add(
                ReceptenFactory.createIngredient(
                        ingrId,
                        AANKOOP_CONTROLLER.getProduct(prodId),
                        hoeveelheid,
                        eenheid
                ),
                MANAGER.getCatalog(IngredientCataloog.class)
        );
    }

    /**
     * Calculates the average purchase price
     *
     * @param date Date
     * @return Munt object representing the average purchase price
     */
    public Munt getGemiddeldeAankoopPrijs(int receptId, LocalDate date) {
        List<Ingredient> ingredients = getAllIngredients(receptId);
        return AANKOOP_CONTROLLER.getGemiddeldeAankoopPrijs(ingredients, date);
    }

    /**
     * Clears all catalogs
     */
    public void clearCatalogs() {
        this.MANAGER.clearCatalogs();
    }
}