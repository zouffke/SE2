package be.kdg.hifresh.businessLayer.recepten;

import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.util.Eenheid;

/**
 * A factory class for creating various objects related to recipes.
 */
public final class ReceptenFactory {
    //region constructors
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ReceptenFactory() {
    }
    //endregion

    //region create methods
    /**
     * Creates a new BereidingsStap object.
     *
     * @param id The id of the preparation step.
     * @param naam The name of the preparation step.
     * @param beschrijving The description of the preparation step.
     * @return A new BereidingsStap object.
     */
    public static BereidingsStap createBereidingsStap(int id, String naam, String beschrijving) {
        return new BereidingsStap(naam, beschrijving, id);
    }

    /**
     * Creates a new Ingredient object.
     *
     * @param id The id of the ingredient.
     * @param product The product associated with the ingredient.
     * @param hoeveelheid The quantity of the ingredient.
     * @return A new Ingredient object.
     */
    public static Ingredient createIngredient(int id, Product product, double hoeveelheid, Eenheid eenheid) {
        return new Ingredient(id, product, hoeveelheid, eenheid);
    }

    /**
     * Creates a new Recept object.
     *
     * @param id The id of the recipe.
     * @param naam The name of the recipe.
     * @param beschrijving The description of the recipe.
     * @return A new Recept object.
     */
    public static Recept createRecept(int id, String naam, String beschrijving) {
        return new Recept(naam, id, beschrijving);
    }
    //endregion
}