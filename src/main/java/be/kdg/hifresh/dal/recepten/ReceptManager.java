package be.kdg.hifresh.dal.recepten;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.recepten.catalogs.IngredientCataloog;
import be.kdg.hifresh.dal.recepten.catalogs.ReceptCataloog;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.recepten.Ingredient;
import be.kdg.hifresh.domain.recepten.Recept;
import be.kdg.hifresh.domain.util.Munt;
import be.kdg.hifresh.domain.util.UtilFactory;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
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

    public List<Product> getAllProducts(int receptId) throws InvocationTargetException, IllegalAccessException {
        return super.getObjFromCatalogById(receptId, receptCataloog)
                .getIngredienten()
                .stream()
                .map(Ingredient::getProduct).toList();
    }

    public Munt getPriceForRecept(Map<Integer, Munt> prices, int receptId) throws InvocationTargetException, IllegalAccessException {
        double bedrag = 0;

        for (Ingredient ingr : super.getObjFromCatalogById(receptId, receptCataloog).getIngredienten()){
            Munt munt = prices.get(ingr.getProduct().getId());

            if (munt != null){
                bedrag += munt.getBedrag() * ingr.getHoeveelheid();
            }
        }

        return UtilFactory.createMunt(bedrag, "Euro");
    }
}