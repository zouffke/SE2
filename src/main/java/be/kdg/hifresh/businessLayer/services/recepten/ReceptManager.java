package be.kdg.hifresh.businessLayer.services.recepten;

import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.persistenceLayer.recepten.IngredientCataloog;
import be.kdg.hifresh.persistenceLayer.recepten.ReceptCataloog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is responsible for managing recipes.
 * It extends the Manager class and provides methods to retrieve all ingredients of a recipe.
 */
@Service
public class ReceptManager extends Manager {
    //region constructors

    /**
     * Constructor for ReceptManager.
     * Initializes the recipe catalog and ingredient catalog.
     *
     * @param receptCataloog Catalog of recipes
     * @param ingredientCataloog Catalog of ingredients
     */
    @Autowired
    public ReceptManager(ReceptCataloog receptCataloog
            , IngredientCataloog ingredientCataloog) {
        super();
        super.addCatalog(receptCataloog);
        super.addCatalog(ingredientCataloog);
    }

    //endregion

    /**
     * Retrieves all ingredients of a recipe by its ID.
     * If the recipe has sub-recipes, it retrieves the ingredients of the sub-recipes as well.
     *
     * @param receptId Recipe ID
     * @param object Class of the object to be retrieved
     * @return List of ingredients
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllT(int receptId, Class<T> object) {
        if (object == Ingredient.class) {
            return (List<T>) super.getById(receptId,
                            super.getCatalog(ReceptCataloog.class))
                    .getIngredients();
        }
        return null;
    }
}