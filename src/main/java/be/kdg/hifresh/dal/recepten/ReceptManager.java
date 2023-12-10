package be.kdg.hifresh.dal.recepten;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.recepten.catalogs.IngredientCataloog;
import be.kdg.hifresh.dal.recepten.catalogs.ReceptCataloog;
import be.kdg.hifresh.domain.recepten.Ingredient;
import be.kdg.hifresh.domain.recepten.Recept;
import be.kdg.hifresh.domain.util.Munt;
import be.kdg.hifresh.domain.util.UtilFactory;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A manager class for handling recipes.
 * Extends the Manager class.
 * This class is responsible for managing the catalogs of recipes and ingredients, and the contract manager.
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
     * Initializes the recipe catalog, ingredient catalog, and contract manager.
     *
     * @author Dandois Luca
     */
    public ReceptManager() {
        this.receptCataloog = new ReceptCataloog();
        this.ingredientCataloog = new IngredientCataloog();
    }
    //endregion

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