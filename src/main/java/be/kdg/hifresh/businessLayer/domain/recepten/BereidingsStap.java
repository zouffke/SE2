package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a step in the preparation of a recipe.
 * It extends the Bereiding class and contains a list of ingredients needed for this step.
 * It also contains a sequence number to indicate the order of the steps in the recipe.
 */
@Getter
public class BereidingsStap extends Bereiding {
    //region vars

    /**
     * List of ingredients needed for this preparation step.
     */
    private final List<Ingredient> INGREDIENTS;

    /**
     * Sequence number to indicate the order of the steps in the recipe.
     */
    @Setter
    private int volgNummer;
    //endregion

    /**
     * Constructor for creating a new preparation step.
     *
     * @param name         The name of the preparation step.
     * @param beschrijving The description of the preparation step.
     * @param id           The id of the preparation step.
     * @param volgNummer   The sequence number of the preparation step.
     */
    BereidingsStap(String name, String beschrijving, int id, int volgNummer) {
        super(id, name, beschrijving);
        this.INGREDIENTS = new ArrayList<>();
        this.volgNummer = volgNummer;
    }

    /**
     * Adds an ingredient to the list of ingredients needed for this preparation step.
     *
     * @param ingredient The ingredient to be added.
     */
    void addIngredient(Ingredient ingredient) {
        this.INGREDIENTS.add(ingredient);
    }

}