package be.kdg.hifresh.businessLayer.services.recepten;

import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.persistenceLayer.recepten.IngredientCataloog;
import be.kdg.hifresh.persistenceLayer.recepten.ReceptCataloog;
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
    private final Catalog<Recept> receptCataloog;

    /**
     * Catalog of ingredients.
     */
    private final Catalog<Ingredient> INGREDIENT_CATALOG;

    //endregion

    //region constructors

    /**
     * Constructor for ReceptManager.
     * Initializes the recipe catalog and ingredient catalog.
     */
    public ReceptManager() {
        this.receptCataloog = new ReceptCataloog();
        this.INGREDIENT_CATALOG = new IngredientCataloog();
    }
    //endregion

    /**
     * Retrieves all ingredients of a recipe by its ID.
     * If the recipe has sub-recipes, it retrieves the ingredients of the sub-recipes as well.
     *
     * @param receptId Recipe ID
     * @return List of ingredients
     * @throws InvocationTargetException if the underlying method throws an exception
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible
     */
    public List<Ingredient> getAllIngredients(int receptId) throws InvocationTargetException, IllegalAccessException {
        Recept recept = super.getById(receptId, receptCataloog);
        List<Ingredient> ingredients = new ArrayList<>();
        List<Recept> subRecepts = recept.getSubrecepts();

        if (!subRecepts.isEmpty()){
            for (Recept subRecept : subRecepts){
                ingredients.addAll(this.getAllIngredients(subRecept.getID()));
            }
        }

        ingredients.addAll(recept.getIngredients());
        return ingredients;
    }
}