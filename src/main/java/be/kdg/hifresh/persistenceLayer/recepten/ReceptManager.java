package be.kdg.hifresh.persistenceLayer.recepten;

import be.kdg.hifresh.persistenceLayer.Manager;
import be.kdg.hifresh.persistenceLayer.recepten.catalogs.IngredientCataloog;
import be.kdg.hifresh.persistenceLayer.recepten.catalogs.ReceptCataloog;
import be.kdg.hifresh.businessLayer.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.recepten.Recept;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * A manager class for handling recipes.
 * Extends the Manager class.
 * This class is responsible for managing the catalogs of recipes and ingredients, and the contract manager.
 * It provides methods to get all ingredients of a recipe.
 *
 * @author Dandois Luca
 */
@Getter
public class ReceptManager extends Manager {
    //region vars

    /**
     * Catalog of recipes.
     */
    private final ReceptCataloog receptCataloog;

    /**
     * Catalog of ingredients.
     */
    private final IngredientCataloog ingredientCataloog;

    //endregion

    //region constructors

    /**
     * Constructor for ReceptManager.
     * Initializes the recipe catalog and ingredient catalog.
     */
    public ReceptManager() {
        this.receptCataloog = new ReceptCataloog();
        this.ingredientCataloog = new IngredientCataloog();
    }
    //endregion

    /**
     * Retrieves all ingredients of a recipe by its ID.
     * If the recipe has sub-recipes, it retrieves the ingredients of the sub-recipes as well.
     *
     * @param receptId Recipe ID
     * @return List of ingredients
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public List<Ingredient> getAllIngredients(int receptId) throws InvocationTargetException, IllegalAccessException {
        Recept recept = super.getObjFromCatalogById(receptId, receptCataloog);

        List<Ingredient> ingredients = new ArrayList<>();

        if (recept.getSubrecepten() != null && !recept.getSubrecepten().isEmpty()) {
            for (Recept subRecept : recept.getSubrecepten()) {
                ingredients.addAll(getAllIngredients(subRecept.getId()));
            }
        }

        ingredients.addAll(super.getObjFromCatalogById(receptId, receptCataloog).getIngredienten());

        return ingredients;
    }
}