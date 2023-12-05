package be.kdg.hifresh.dal.recepten;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.recepten.catalogs.IngredientCataloog;
import be.kdg.hifresh.dal.recepten.catalogs.ReceptCataloog;
import lombok.Getter;

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
}